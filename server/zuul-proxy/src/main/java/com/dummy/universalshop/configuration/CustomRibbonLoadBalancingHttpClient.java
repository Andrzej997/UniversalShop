package com.dummy.universalshop.configuration;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.IClientConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.cloud.netflix.ribbon.ServerIntrospector;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Properties;

@Configuration
public class CustomRibbonLoadBalancingHttpClient extends RibbonLoadBalancingHttpClient {

    private String trustStoreResource;

    private String trustStorePassword;

    @Deprecated
    public CustomRibbonLoadBalancingHttpClient() {
        super();
    }

    public CustomRibbonLoadBalancingHttpClient(IClientConfig config, ServerIntrospector serverIntrospector) {
        super(config, serverIntrospector);
    }

    public CustomRibbonLoadBalancingHttpClient(HttpClient delegate, IClientConfig config, ServerIntrospector serverIntrospector) {
        super(delegate, config, serverIntrospector);
    }

    @Override
    protected HttpClient createDelegate(IClientConfig config) {
        final KeyStore trustStore;
        SSLConnectionSocketFactory systemSocketFactory = null;
        if (StringUtils.isEmpty(trustStorePassword)) {
            trustStorePassword = getValueFromProperties("trust-store-password");
        }
        if (StringUtils.isEmpty(trustStoreResource)) {
            trustStoreResource = getValueFromProperties("trust-store");
        }
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(getResource(trustStoreResource), trustStorePassword.toCharArray());
            systemSocketFactory = new SSLConnectionSocketFactory(
                    SSLContexts
                            .custom()
                            .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                            .build(),
                    new NoopHostnameVerifier());
        } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return HttpClientBuilder.create()
                .setMaxConnTotal(config.getPropertyAsInteger(CommonClientConfigKey.MaxTotalConnections, 0))
                .setSSLHostnameVerifier((s, sslSession) -> true)
                .setSSLSocketFactory(systemSocketFactory)
                .setMaxConnPerRoute(config.getPropertyAsInteger(CommonClientConfigKey.MaxConnectionsPerHost, 0))
                .disableCookieManagement()
                .useSystemProperties() // for proxy
                .build();
    }

    private InputStream getResource(String resourcePath) {
        if (StringUtils.isEmpty(resourcePath)) {
            return null;
        }
        if (resourcePath.contains("classpath:")) {
            resourcePath = resourcePath.substring(10, resourcePath.length());
        }
        return this.getClass().getClassLoader().getResourceAsStream(resourcePath);
    }

    private String getValueFromProperties(String key) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = getResource("application.yml");
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
