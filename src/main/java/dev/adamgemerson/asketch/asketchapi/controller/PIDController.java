package dev.adamgemerson.asketch.asketchapi.controller;

import dev.adamgemerson.asketch.asketchapi.repository.AlloyTestRepository;
import dev.adamgemerson.asketch.asketchapi.repository.PIDRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pid")
public class PIDController {
    private final PIDRepository repository;

    public PIDController(PIDRepository repository) {
        this.repository = repository;
    }

    // GET http://localhost:8080/pid
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping
    public String run() {
        return Long.toString(repository.getPID());
    }
}
