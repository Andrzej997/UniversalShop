package com.dummy.universalshop.configuration;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.EurekaIdentityHeaderFilter;
import com.netflix.discovery.converters.wrappers.CodecWrappers;
import com.netflix.discovery.provider.DiscoveryJerseyProvider;
import com.netflix.discovery.shared.MonitoredConnectionManager;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl;
import com.netflix.discovery.shared.transport.jersey.SSLSocketFactoryAdapter;
import com.netflix.eureka.EurekaServerConfig;
import com.netflix.eureka.EurekaServerIdentity;
import com.netflix.eureka.cluster.DynamicGZIPContentEncodingFilter;
import com.netflix.eureka.cluster.HttpReplicationClient;
import com.netflix.eureka.cluster.PeerEurekaNode;
import com.netflix.eureka.cluster.PeerEurekaNodes;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import com.netflix.eureka.resources.ServerCodecs;
import com.netflix.eureka.transport.JerseyReplicationClient;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.apache4.ApacheHttpClient4;
import com.sun.jersey.client.apache4.config.ApacheHttpClient4Config;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.*;
import java.security.cert.CertificateException;

@Configuration
public class EurekaOptionalConfig implements InitializingBean {

    @Value("${server.ssl.trust-store}")
    private Resource trustStoreResource;

    @Value("${server.ssl.key-store}")
    private Resource keyStoreResource;

    @Value("${server.ssl.trust-store-password}")
    private String trustStorePassword;

    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("javax.net.ssl.keyStore", keyStoreResource.getFile().getCanonicalPath());
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
        System.setProperty("javax.net.ssl.trustStore", trustStoreResource.getFile().getCanonicalPath());
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
    }

    @Autowired
    private EurekaClientConfig config;

    @Bean
    public DiscoveryClient.DiscoveryClientOptionalArgs getTrustStoredEurekaClient()
            throws KeyStoreException, IOException, CertificateException,
            NoSuchAlgorithmException, KeyManagementException, UnrecoverableKeyException {
        final KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        trustStore.load(trustStoreResource.getInputStream(), trustStorePassword.toCharArray());

        SSLConnectionSocketFactory systemSocketFactory = new SSLConnectionSocketFactory(
                SSLContexts
                        .custom()
                        .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                        .build(),
                new NoopHostnameVerifier());

        SchemeRegistry sslSchemeRegistry = new SchemeRegistry();
        Scheme schema = new Scheme("https", 443, new SSLSocketFactoryAdapter(systemSocketFactory));
        sslSchemeRegistry.register(schema);
        String name = "Custom-Discovery-Client";
        MonitoredConnectionManager connectionManager = new MonitoredConnectionManager(name, sslSchemeRegistry);
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getProperties().put(ApacheHttpClient4Config.PROPERTY_CONNECTION_MANAGER, connectionManager);

        DiscoveryJerseyProvider discoveryJerseyProvider = new DiscoveryJerseyProvider(
                CodecWrappers.getEncoder(config.getEncoderName()),
                CodecWrappers.resolveDecoder(config.getDecoderName(), config.getClientDataAccept()));

        clientConfig.getSingletons().add(discoveryJerseyProvider);

        DiscoveryClient.DiscoveryClientOptionalArgs clientOptionalArgs = new DiscoveryClient.DiscoveryClientOptionalArgs();
        clientOptionalArgs.setEurekaJerseyClient(new EurekaJerseyClientImpl(
                config.getEurekaServerConnectTimeoutSeconds() * 1000,
                config.getEurekaServerReadTimeoutSeconds() * 1000,
                config.getEurekaConnectionIdleTimeoutSeconds() * 1000,
                clientConfig));
        return clientOptionalArgs;
    }


    @Bean
    @Inject
    @Primary
    public PeerEurekaNodes peerEurekaNodes(PeerAwareInstanceRegistry registry, EurekaServerConfig serverConfig, EurekaClientConfig eurekaClientConfig, ServerCodecs serverCodecs, @Named("eurekaApplicationInfoManager") ApplicationInfoManager applicationInfoManager) {
        return new PeerEurekaNodesCustom(registry, serverConfig, eurekaClientConfig, serverCodecs, applicationInfoManager);
    }

    public class PeerEurekaNodesCustom extends PeerEurekaNodes {

        PeerEurekaNodesCustom(PeerAwareInstanceRegistry registry, EurekaServerConfig serverConfig, EurekaClientConfig clientConfig, ServerCodecs serverCodecs, ApplicationInfoManager applicationInfoManager) {
            super(registry, serverConfig, clientConfig, serverCodecs, applicationInfoManager);
        }

        @Override
        protected PeerEurekaNode createPeerEurekaNode(String peerEurekaNodeUrl) {
            HttpReplicationClient replicationClient = JerseyReplicationClientCustom.createReplicationClientCustom(serverConfig, serverCodecs, peerEurekaNodeUrl, trustStoreResource, trustStorePassword);
            String targetHost = hostFromUrl(peerEurekaNodeUrl);
            if (targetHost == null) {
                targetHost = "host";
            }
            return new PeerEurekaNode(registry, targetHost, peerEurekaNodeUrl, replicationClient, serverConfig);
        }
    }

    static class JerseyReplicationClientCustom extends JerseyReplicationClient {

        private static final Logger logger = LoggerFactory.getLogger(JerseyReplicationClientCustom.class);

        JerseyReplicationClientCustom(EurekaJerseyClient jerseyClient, String serviceUrl) {
            super(jerseyClient, serviceUrl);
        }

        static JerseyReplicationClient createReplicationClientCustom(EurekaServerConfig config, ServerCodecs serverCodecs, String serviceUrl, Resource trustStoreResource, String trustStorePassword) {
            String name = JerseyReplicationClient.class.getSimpleName() + ": " + serviceUrl + "apps/: ";

            EurekaJerseyClient jerseyClient;
            try {
                String hostname;
                try {
                    hostname = new URL(serviceUrl).getHost();
                } catch (MalformedURLException e) {
                    hostname = serviceUrl;
                }

                String jerseyClientName = "Discovery-PeerNodeClient-" + hostname;
                EurekaJerseyClientImpl.EurekaJerseyClientBuilder clientBuilder = new EurekaJerseyClientImpl.EurekaJerseyClientBuilder()
                        .withClientName(jerseyClientName)
                        .withUserAgent("Java-EurekaClient-Replication")
                        .withEncoderWrapper(serverCodecs.getFullJsonCodec())
                        .withDecoderWrapper(serverCodecs.getFullJsonCodec())
                        .withConnectionTimeout(config.getPeerNodeConnectTimeoutMs())
                        .withReadTimeout(config.getPeerNodeReadTimeoutMs())
                        .withMaxConnectionsPerHost(config.getPeerNodeTotalConnectionsPerHost())
                        .withMaxTotalConnections(config.getPeerNodeTotalConnections())
                        .withTrustStoreFile(trustStoreResource.getFile().getPath(), trustStorePassword)
                        .withConnectionIdleTimeout(config.getPeerNodeConnectionIdleTimeoutSeconds());

                if (serviceUrl.startsWith("https://") &&
                        "true".equals(System.getProperty("com.netflix.eureka.shouldSSLConnectionsUseSystemSocketFactory"))) {
                    clientBuilder.withSystemSSLConfiguration();
                }
                jerseyClient = clientBuilder.build();
            } catch (Throwable e) {
                throw new RuntimeException("Cannot Create new Replica Node :" + name, e);
            }

            String ip = null;
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                logger.warn("Cannot find localhost ip", e);
            }

            ApacheHttpClient4 jerseyApacheClient = jerseyClient.getClient();
            jerseyApacheClient.addFilter(new DynamicGZIPContentEncodingFilter(config));

            EurekaServerIdentity identity = new EurekaServerIdentity(ip);
            jerseyApacheClient.addFilter(new EurekaIdentityHeaderFilter(identity));

            return new JerseyReplicationClientCustom(jerseyClient, serviceUrl);
        }
    }
}
