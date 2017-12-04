package com.dummy.universalshop.configuration;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.converters.wrappers.CodecWrappers;
import com.netflix.discovery.provider.DiscoveryJerseyProvider;
import com.netflix.discovery.shared.MonitoredConnectionManager;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl;
import com.netflix.discovery.shared.transport.jersey.SSLSocketFactoryAdapter;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.apache4.config.ApacheHttpClient4Config;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@Configuration
public class EurekaOptionalConfig implements InitializingBean {

    @Value("${server.ssl.trust-store}")
    private Resource trustStoreResource;

    @Value("${server.ssl.trust-store-password}")
    private String trustStorePassword;

    @Value("${server.ssl.key-store}")
    private Resource keyStoreResource;

    @Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    @Autowired
    private EurekaClientConfig config;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("javax.net.ssl.keyStore", keyStoreResource.getFile().getCanonicalPath());
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
        System.setProperty("javax.net.ssl.trustStore", trustStoreResource.getFile().getCanonicalPath());
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
    }

    @Bean
    @Primary
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
}
