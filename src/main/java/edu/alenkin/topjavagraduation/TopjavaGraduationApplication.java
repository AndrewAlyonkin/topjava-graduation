package edu.alenkin.topjavagraduation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TopjavaGraduationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopjavaGraduationApplication.class, args);
    }
}
