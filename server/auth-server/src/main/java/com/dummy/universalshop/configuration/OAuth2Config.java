package com.dummy.universalshop.configuration;

import com.dummy.universalshop.filter.CustomTokenEndpointAuthFilter;
import com.dummy.universalshop.security.UserAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;

/**
 * Created by Mateusz on 17.05.2017.
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    @Lazy
    @Qualifier("userDetailsService")
    private ClientDetailsService clientDetailsService;

    @Autowired
    private UserAuthenticationManager userAuthenticationManager;

    private OAuth2RequestFactory oAuth2RequestFactory;

    private static final String PROP_CLIENTID = "authentication.oauth.clientId";
    private static final String PROP_SECRET = "authentication.oauth.secret";
    private static final String PROP_TOKEN_VALIDITY_SECONDS = "authentication.oauth.tokenValidityInSeconds";

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .addTokenEndpointAuthenticationFilter(
                        new CustomTokenEndpointAuthFilter(userAuthenticationManager, oAuth2RequestFactory));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        oAuth2RequestFactory = endpoints.getOAuth2RequestFactory();
        endpoints
                .authenticationManager(userAuthenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(tokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter tokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "foobar".toCharArray())
                .getKeyPair("test");
        converter.setKeyPair(keyPair);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenConverter());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setAuthenticationManager(userAuthenticationManager);
        tokenServices.setSupportRefreshToken(true);
        return tokenServices;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

}
