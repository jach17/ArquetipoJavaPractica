package com.[%= companylower %].[%= namelower %];

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Spring Boot Application class
 * 
 * @author [%= username %]
 *
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
