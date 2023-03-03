package dev.adamgemerson.asketch.asketchapi.models;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AlloyFileTest {

    @Test
    void create_new_record_alloy_file() {

        AlloyFile file = new AlloyFile(
                "Test Name",
                "/",
                new ArrayList<Atom> (),
                new ArrayList<AlloyFunction>()
        );

        assertNotNull(file);
        assertEquals("Test Name", file.name());
        assertTrue(file.getClass().isRecord());
        assertEquals(3, file.getClass().getRecordComponents().length);
    }

}
