package com.dummy.universalshop.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
}
