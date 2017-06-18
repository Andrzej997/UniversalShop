package com.dummy.universalshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import javax.net.ssl.HttpsURLConnection;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigServerApplication.class);

    static {
        LOG.warn("Will now disable hostname check in SSL, only to be used during development");
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
