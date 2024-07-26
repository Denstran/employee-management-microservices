package org.example.modelmapperspringbootstarter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(ModelMapperProperties.class)
public class ModelMapperAutoConfiguration {

    private final ModelMapperProperties properties;

    public ModelMapperAutoConfiguration(ModelMapperProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "modelmapper",
            name = {"matching-strategy", "enable-field-matching", "field-access-level"})
    public ModelMapper modelMapper() throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        MatchingStrategy matchingStrategy = switch (properties.getMatchingStrategy()) {
            case "STRICT" -> MatchingStrategies.STRICT;
            case "STANDARD" -> MatchingStrategies.STANDARD;
            case "LOOSE" -> MatchingStrategies.LOOSE;
            default -> throw new Exception("Matching strategy not found for: " + properties.getMatchingStrategy());
        };

        modelMapper.getConfiguration()
                .setMatchingStrategy(matchingStrategy)
                .setFieldMatchingEnabled(properties.isEnableFieldMatching())
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel
                        .valueOf(properties.getFieldAccessLevel()));

        return modelMapper;
    }
}
