package org.example.modelmapperspringbootstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "modelmapper")
public class ModelMapperProperties {

    private String matchingStrategy;
    private boolean enableFieldMatching;
    private String fieldAccessLevel;

    // Getters and setters
    public String getMatchingStrategy() {
        return matchingStrategy;
    }

    public void setMatchingStrategy(String matchingStrategy) {
        this.matchingStrategy = matchingStrategy;
    }

    public boolean isEnableFieldMatching() {
        return enableFieldMatching;
    }

    public void setEnableFieldMatching(boolean enableFieldMatching) {
        this.enableFieldMatching = enableFieldMatching;
    }

    public String getFieldAccessLevel() {
        return fieldAccessLevel;
    }

    public void setFieldAccessLevel(String fieldAccessLevel) {
        this.fieldAccessLevel = fieldAccessLevel;
    }
}
