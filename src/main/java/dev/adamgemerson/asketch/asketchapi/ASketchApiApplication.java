package dev.adamgemerson.asketch.asketchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class ASketchApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ASketchApiApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "10121"));
        app.run(args);
    }
}
