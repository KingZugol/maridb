package com.werkstuck.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;


@SpringBootApplication
public class MaridbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaridbApplication.class, args);
    }
}
