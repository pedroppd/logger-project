package com.logging.loggger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogggerApplication implements CommandLineRunner {

    @Autowired
    private Log log;

    public static void main(String[] args) {
        SpringApplication.run(LogggerApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        log.info("ee", "nome", "pedro", "valor", 107);
        log.info("rr", "nome", "pedro2", "valor", 170);
        log.info("tttt", "nome", "pedro3", "valor", 107);
        log.info("yyy", "nome", "pedro4", "valor", 170);
        log.error("error", "stack", "123", "email", "teste@gmail.com");
    }
}
