package com.dummy.universalshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.net.ssl.HttpsURLConnection;

@SpringBootApplication
@Controller
@EnableHystrixDashboard
public class HystrixDashboardMonitorApplication extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(HystrixDashboardMonitorApplication.class);

    static {
        LOG.warn("Will now disable hostname check in SSL, only to be used during development");
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixDashboardMonitorApplication.class).web(true).run(args);
    }

    @RequestMapping("/")
    public String home() {
        return "forward:/hystrix";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HystrixDashboardMonitorApplication.class).web(true);
    }
}
