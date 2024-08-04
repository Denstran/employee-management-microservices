package org.example.kafkatopicsmanager.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic companyBranchPaymentLogTopic() {
        return new NewTopic("companyBranchPaymentLog", 5, (short) 1);
    }

    @Bean
    public NewTopic departmentInfoPaymentLog() {
        return new NewTopic("departmentInfoPaymentLog", 5, (short) 1);
    }

    @Bean
    public NewTopic position() {
        return new NewTopic("position", 5, (short) 1);
    }

    @Bean
    public NewTopic employee() {
        return new NewTopic("employee", 5, (short) 1);
    }

    @Bean
    public NewTopic email() {
        return new NewTopic("email", 5, (short) 1);
    }

}
