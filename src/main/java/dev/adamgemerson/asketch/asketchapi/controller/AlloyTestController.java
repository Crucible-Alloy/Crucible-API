package dev.adamgemerson.asketch.asketchapi.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import dev.adamgemerson.asketch.asketchapi.models.AlloyFile;
import dev.adamgemerson.asketch.asketchapi.repository.AlloyFileRepository;
import dev.adamgemerson.asketch.asketchapi.repository.AlloyTestRepository;
import edu.mit.csail.sdg.translator.A4Solution;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tests")
public class AlloyTestController {

    private final AlloyTestRepository repository;

    public AlloyTestController(AlloyTestRepository repository) {
        this.repository = repository;
    }

    // POST http://localhost:8080/tests
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String run(@RequestBody Map<String, String> body) {
        return repository.runTest(body.get("path"), body.get("command")).toString();
    }
}
