package dev.adamgemerson.asketch.asketchapi.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class PIDRepository {
    public PIDRepository() {

    }

    public long getPID() {
        long pid = ProcessHandle.current().pid();
        System.out.println(pid);
        return pid;
    }
}
