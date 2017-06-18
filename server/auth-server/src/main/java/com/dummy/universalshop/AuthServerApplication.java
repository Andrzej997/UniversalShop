package com.dummy.universalshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.net.ssl.HttpsURLConnection;

@SpringBootApplication
@EnableResourceServer
public class AuthServerApplication {

    private static final Logger LOG = LoggerFactory.getLogger(AuthServerApplication.class);

    static {
        LOG.warn("Will now disable hostname check in SSL, only to be used during development");
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

}
