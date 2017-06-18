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
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Configuration
public class EurekaOptionalConfig {

    @Value("${server.ssl.trust-store}")
    private Resource trustStoreResource;

    @Value("${server.ssl.trust-store-password}")
    private String trustStorePassword;

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
    public SSLConnectionSocketFactory sslConnectionSocketFactory()
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException,
            IOException, CertificateException {

        SSLContextBuilder sslcontext = new SSLContextBuilder();
        final KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        trustStore.load(trustStoreResource.getInputStream(), trustStorePassword.toCharArray());
        sslcontext.loadTrustMaterial(trustStore, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslConnectionSocketFactory =
                new SSLConnectionSocketFactory(sslcontext.build(), NoopHostnameVerifier.INSTANCE);
        return sslConnectionSocketFactory;
    }

    @Bean
    @Autowired
    public Registry<ConnectionSocketFactory> registry(SSLConnectionSocketFactory sslConnectionSocketFactory) {
        return RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslConnectionSocketFactory)
                .build();
    }

    @Bean
    @Autowired
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(Registry<ConnectionSocketFactory> registry) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(100);
        return cm;
    }

    @Bean
    public HttpClient httpClient(SSLConnectionSocketFactory sslConnectionSocketFactory,
                                 PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
        return HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setConnectionManager(poolingHttpClientConnectionManager)
                .build();
    }


    public class TrustAllStrategy implements TrustStrategy {

        @Override
        public boolean isTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            return true;
        }
    }
}
