package org.example.githubconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class GitHubConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitHubConfigServerApplication.class, args);
    }

}
