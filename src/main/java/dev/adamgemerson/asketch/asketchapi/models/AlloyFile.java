package dev.adamgemerson.asketch.asketchapi.models;

import java.util.ArrayList;

public record AlloyFile(String name, String path, ArrayList<Atom> atoms, ArrayList<AlloyFunction> functions) { }
