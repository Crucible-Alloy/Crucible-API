package dev.adamgemerson.asketch.asketchapi.repository;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.alloy4.ErrorWarning;
import edu.mit.csail.sdg.ast.Command;
import edu.mit.csail.sdg.ast.Expr;
import edu.mit.csail.sdg.parser.CompModule;
import edu.mit.csail.sdg.parser.CompUtil;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Solution;
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class AlloyTestRepository {

    A4Reporter reporter = new A4Reporter() {
        @Override
        public void warning(ErrorWarning msg) {
            System.out.println(msg.toString().trim());
            System.out.flush();
        }
    };

    public AlloyTestRepository() {

    }

    public A4Solution runTest(String filePath, String testString, int maximum) {

        //Alloy requires this reporter object - it essentially handles thrown errors when trying to run a model/command

        //This reads in the Alloy model, parses it and then stores it as a CompModule
        //Which basically is an object interpretation of the model that we can work with to do things like iterate over commands in the model and run them
        CompModule world = CompUtil.parseEverything_fromFile(reporter, null, filePath);
        //We can then take a string and turn it into an Alloy expression
        Expr testCase = CompUtil.parseOneExpression_fromString(world, testString);

        //Then build a command object to then execute
        //Parameters:
        //Boolean check - we want false
        //Next 3 are about scope - we will need to probably infer these or ask the user to provide them
        //overall/bitwidth/maxseq - default is 3 for all

        // TODO: Pass the max of a single atom Class as a parameter to all 3 parameters here.
        // If assertion check should be 'true', if predicate check should be false.
        Command testCommand = new Command(false, maximum, maximum, maximum, testCase);

        //These options configure "how" to run a command - what solver to use, what is the max memory the execution can use, etc.
        //We usually just set it up in a very bare-bones format - i.e. just set the SAT solver then use the default for everything else
        A4Options options = new A4Options();
        options.solver = A4Options.SatSolver.SAT4J;

        //Runs the command, stores the result
        A4Solution instance = TranslateAlloyToKodkod.execute_command(reporter, world.getAllReachableSigs(), testCommand, options);

        //Print first instance which should match the test case
        System.out.println(instance);

        // TODO: Is an instance always returned? What is returned on a failing instance?
        try {
            return instance;
        } catch (Exception exception) {
            System.out.println("exception, " + exception);
            return null;
        }
    }

}
