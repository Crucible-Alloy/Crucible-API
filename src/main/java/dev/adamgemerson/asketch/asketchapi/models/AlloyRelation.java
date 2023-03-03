package dev.adamgemerson.asketch.asketchapi.models;

import edu.mit.csail.sdg.alloy4.Pos;
import edu.mit.csail.sdg.ast.Decl;
import edu.mit.csail.sdg.ast.Expr;
import edu.mit.csail.sdg.ast.Type;

import java.util.ArrayList;

public record AlloyRelation(String label, String multiplicity, String type) { }
