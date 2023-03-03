package dev.adamgemerson.asketch.asketchapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.mit.csail.sdg.alloy4.Pos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public record Atom(String label, Pos isEnum, Pos isLone, Pos isOne, Pos isSome, Pos isAbstract,
                   ArrayList<AlloyRelation> relations, ArrayList<String> parents, ArrayList<String> children) { }
