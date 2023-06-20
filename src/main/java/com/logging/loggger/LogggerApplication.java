package com.logging.loggger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class LogggerApplication implements CommandLineRunner {

    @Autowired
    private Log log;

    public static void main(String[] args) {
        SpringApplication.run(LogggerApplication.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("Testando log info", "tid", UUID.randomUUID().toString(), "name", "Pedro", "lastName", "Gabriel", "email", "pedro@gmail.com", "id", 107);
        log.info("Testando log info", "tid", UUID.randomUUID().toString(), "name", "Maria", "lastName", "Silva", "email", "maria@gmail.com", "id", 170);
        log.info("Testando log info", "tid", UUID.randomUUID().toString(), "name", "Junior", "lastName", "Dos Santos", "email", "junior@gmail.com", "id", 1027);
        log.info("Testando log info", "tid", UUID.randomUUID().toString(), "name", "Joana", "lastName", "Maria", "email", "joana@gmail.com", "id", 1270);
        log.error("Testando log error", "tid", "tid", UUID.randomUUID().toString(), "error", "Problema ao salvar no banco", "stack", "Error stacktrace");
    }
}
