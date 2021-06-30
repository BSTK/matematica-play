package com.caqqi.matematicaplay.operacao.core.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class JacksonConfig {

    @Bean
    public Module hibernateModule() {
        return new Hibernate5Module();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(hibernateModule());
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return new MappingJackson2HttpMessageConverter(mapper);
    }

}
