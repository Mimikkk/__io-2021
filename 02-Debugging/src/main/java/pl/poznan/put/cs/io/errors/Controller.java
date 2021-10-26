package pl.poznan.put.cs.io.errors;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import pl.poznan.put.cs.io.errors.processors.BFSProcessor;
import pl.poznan.put.cs.io.errors.storage.InputStorage;
import pl.poznan.put.cs.io.errors.storage.OutputStorage;

/**
 * Controller of the BFS Traversal Process.
 */
public class Controller {
    private final @NotNull InputStorage Input;
    private final @NotNull OutputStorage Output;
    private final @NotNull BFSProcessor BFSProcessor = new BFSProcessor();

    /**
     * @param inputName - filename of given input file, could be 'console' for console input.
     * @param outputName - filename of given output file, could be 'console' for console output.
     */
    public Controller(@NotNull String inputName, @NotNull String outputName) {
        Input = new InputStorage(inputName);
        Output = new OutputStorage(outputName);
    }

    /**
     * Run BFS traversal of the given input and try to save it in specified location.
     */
    public void run() {
        try {
            Input.TryLoad();
        } catch (Exception e) {
            System.err.println("Wrong input (see trace)!");
            e.printStackTrace();
            return;
        }
        List<Integer> result = BFSProcessor.Traverse(Input.Matrix);
        try {
            Output.TrySave(result);
        } catch (Exception e) {
            System.err.println("Wrong output (see trace)!");
            e.printStackTrace();
        }
    }

    /**
     * @param args - input [output]
     */
    public static void main(String[] args) {
        String inputName;
        String outputName = "console";
        if (args.length == 0) {
            System.err.println("Run with parameters: input <output>"
                    + " where input/output are names of files or console for standard in/out"
                    + "\nOperation - first line of the input (+,-,*,/,sort,mean,primals)");
            System.exit(-1);
        }

        inputName = args[0];
        if (args.length > 1) outputName = args[1];

        final Controller controller = new Controller(inputName, outputName);
        controller.run();
    }
}
