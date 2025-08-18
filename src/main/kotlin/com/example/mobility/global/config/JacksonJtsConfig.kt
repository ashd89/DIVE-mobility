package com.example.mobility.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonJtsConfig {
    @Bean
    fun jtsModule(): com.fasterxml.jackson.databind.Module =
        org.n52.jackson.datatype.jts.JtsModule()
}