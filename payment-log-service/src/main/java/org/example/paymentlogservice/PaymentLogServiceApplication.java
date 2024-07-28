package org.example.paymentlogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PaymentLogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentLogServiceApplication.class, args);
    }

}
