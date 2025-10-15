package br.com.reinoeducacao.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)       // evita mapear campo errado
                .setFieldMatchingEnabled(true)                        // permite mapear campos sem getters/setters
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setPropertyCondition(Conditions.isNotNull());        // ignora nulls

        return modelMapper;
    }
}

