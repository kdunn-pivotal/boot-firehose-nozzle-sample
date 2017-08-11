package io.pivotal.cf.nozzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NozzleApplication {
    public static void main(String[] args) {
        SpringApplication.run(NozzleSourceConfiguration.class, args);
    }
}
