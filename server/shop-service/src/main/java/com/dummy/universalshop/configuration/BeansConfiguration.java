package com.dummy.universalshop.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;

/**
 * Created by Mateusz on 14.05.2017.
 */
@Configuration
public class BeansConfiguration {

    @Bean
    @Primary
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("username")
        ));
        return cacheManager;
    }
}
