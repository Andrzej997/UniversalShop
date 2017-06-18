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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@Configuration
public class EurekaOptionalConfig {

    @Value("${server.ssl.trust-store}")
    private Resource trustStoreResource;

    @Value("${server.ssl.trust-store-password}")
    private String trustStorePassword;

    @Autowired
    private EurekaClientConfig config;

    @Bean
    public ClientConfig clientConfig() throws KeyStoreException, IOException, CertificateException,
            NoSuchAlgorithmException, KeyManagementException {
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
        return clientConfig;
    }

    @Bean
    @Autowired
    public DiscoveryClient.DiscoveryClientOptionalArgs getTrustStoredEurekaClient(ClientConfig clientConfig)
            throws KeyStoreException, IOException, CertificateException,
            NoSuchAlgorithmException, KeyManagementException, UnrecoverableKeyException {


        DiscoveryClient.DiscoveryClientOptionalArgs clientOptionalArgs = new DiscoveryClient.DiscoveryClientOptionalArgs();
        clientOptionalArgs.setEurekaJerseyClient(new EurekaJerseyClientImpl(
                config.getEurekaServerConnectTimeoutSeconds() * 1000,
                config.getEurekaServerReadTimeoutSeconds() * 1000,
                config.getEurekaConnectionIdleTimeoutSeconds() * 1000,
                clientConfig));
        return clientOptionalArgs;
    }
}
