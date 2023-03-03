package dev.adamgemerson.asketch.asketchapi.controller;

import dev.adamgemerson.asketch.asketchapi.models.AlloyFile;
import dev.adamgemerson.asketch.asketchapi.repository.AlloyFileRepository;
import dev.adamgemerson.asketch.asketchapi.requests.FileRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/files")
public class AlloyFileController {

    private final AlloyFileRepository repository;

    public AlloyFileController(AlloyFileRepository repository) {
        this.repository = repository;
    }

    // GET http://localhost:8080/files
    @GetMapping
    public List<AlloyFile> findAll() {
        return repository.findAll();
    }

    // POST http://localhost:8080/files
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AlloyFile create(@RequestBody FileRequest req) {
        System.out.println(req.getOperatingSystem());
        System.out.println(req.getFilePath());
        return repository.create(req.getFilePath(), req.getOperatingSystem());
    }
}
