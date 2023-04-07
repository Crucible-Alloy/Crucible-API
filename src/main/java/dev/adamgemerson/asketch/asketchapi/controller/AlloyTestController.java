package dev.adamgemerson.asketch.asketchapi.controller;
import dev.adamgemerson.asketch.asketchapi.repository.AlloyTestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
        return repository.runTest(body.get("path"), body.get("command"), Integer.parseInt(body.get("maximum"))).toString();
    }
}
