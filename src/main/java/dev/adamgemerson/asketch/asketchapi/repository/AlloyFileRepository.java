package dev.adamgemerson.asketch.asketchapi.repository;

import dev.adamgemerson.asketch.asketchapi.models.*;
import edu.mit.csail.sdg.alloy4.Pair;
import edu.mit.csail.sdg.ast.*;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Solution;
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.*;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.alloy4.ErrorWarning;
import edu.mit.csail.sdg.ast.Sig.Field;
import edu.mit.csail.sdg.parser.CompModule;
import edu.mit.csail.sdg.parser.CompUtil;

@Component
@Repository
public class AlloyFileRepository {

    List<AlloyFile> files = new ArrayList<>();

    A4Reporter reporter = new A4Reporter() {
        @Override
        public void warning(ErrorWarning msg) {
            System.out.println(msg.toString().trim());
            System.out.flush();
        }
    };

    public AlloyFileRepository() {

    }

    public List<AlloyFile> findAll() {
        return files;
    }


    public AlloyFile findByID(String id) {
        return null;
    }


    public AlloyFile create(ArrayList<String> filePath, String operatingSystem ) {
        String path;
        System.out.println(filePath);
        if (Objects.equals(operatingSystem, "win32")) {
            // Windows filepath delimited with '\'
            path = String.join("\\", filePath);
        } else {
            // Mac and linux file paths delimited with '/'
            path = String.join("/", filePath);
        }
        System.out.println(path);
        CompModule world = CompUtil.parseEverything_fromFile(this.reporter, null, path);
        ArrayList<Atom> atoms = new ArrayList<>();
        ArrayList<AlloyFunction> functions = new ArrayList<>();

        for (Sig sig : world.getAllReachableSigs()) {
            if (!sig.builtin) {

                // Get the relation object for each atom signature
                ArrayList<AlloyRelation> relations = new ArrayList<>();
                if (!sig.getFields().isEmpty()) {
                    for (Field relation : sig.getFields()) {
                        AlloyRelation relationObject = new AlloyRelation(relation.label, relation.decl().expr.toString(), relation.type().toString());
                        relations.add(relationObject);
                    }
                }

                // If signature is abstract, find the subnodes that inherit from it.
                List subnodes;
                if (sig.isAbstract != null) {
                    subnodes = sig.getSubnodes();
                    System.out.println(subnodes);
                } else {
                    subnodes = null;
                }

                ArrayList<String> parents = new ArrayList<>();
                ArrayList<String> children = new ArrayList<>();
                for (Sig checkSig : world.getAllReachableSigs()) {

                    boolean sigEquivalence = !(checkSig.label.equals("univ")) && !(checkSig.label.equals(sig.label)) && !(checkSig.label.equals("none"));
                    // Check for parent nodes
                    if (sig.isSameOrDescendentOf(checkSig)) {
                        if (sigEquivalence) {
                            parents.add(checkSig.label);
                        }
                    }
                    // Check for children nodes
                    if (checkSig.isSameOrDescendentOf(sig)) {
                        if (sigEquivalence) {
                            children.add(checkSig.label);
                        }
                    }
                }


                Atom atom = new Atom(sig.label, sig.isEnum, sig.isLone, sig.isOne, sig.isSome, sig.isAbstract, relations, parents, children);
                atoms.add(atom);
            }
        }

        // Find the predicates and add them to our model.
        for (Func func : world.getAllFunc()) {
            if (func.isPred) {
                ArrayList<AlloyParameter> parameters = new ArrayList<>();
                for (Decl decl : func.decls) {
                    AlloyParameter parameter = new AlloyParameter(decl.get().toString(), decl.expr.toString());
                    parameters.add(parameter);
                }

                AlloyFunction newFunction = new AlloyFunction(func.toString(), parameters);
                functions.add(newFunction);
            }
        }

        // Get the assertions. Assertions take no parameters, so pass empty array
        for ( Pair<java.lang.String,Expr> assertion : world.getAllAssertions()) {
            AlloyFunction newFunction = new AlloyFunction(assertion.a, new ArrayList<>());
            functions.add(newFunction);
        }

        AlloyFile model = new AlloyFile(path, path, atoms, functions);
        files.add(model);
        return model;
    }


    public void update(AlloyFile file, String id) {

    }
}
