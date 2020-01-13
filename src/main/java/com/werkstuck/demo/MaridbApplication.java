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

  //  @Bean(initMethod = "start", destroyMethod = "stop")
  //  public Server inMemoryH2DatabaseServer() throws SQLException {
  //      return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8080");
   // }
}
