package pl.poznan.put.cs.io.errors.storage;

import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * Controls the loading of data from the given input.
 */
public class InputStorage {
    public int[][] Matrix;

    /**
     * Tries to load input data to the Matrix from either console or input file.
     * @throws IOException - Whether neither ReaderBuffer can be accessed this exception is thrown.
     */
    public void TryLoad() throws IOException {

        try (BufferedReader input = BufferedReader()) {

            String line = input.readLine();
            if (line != null) Size = Integer.parseInt(line.trim());
            Matrix = new int[Size][Size];

            int x = 0;
            while ((line = input.readLine()) != null && line.length() > 0) {
                Matrix[x++] = LineAsNumbers(line);
            }
        }
    }

    /**
     * @param inputName - filename of the input data. Can be 'console' for console input.
     */
    public InputStorage(@NotNull String inputName) {
        InputName = inputName;
    }

    /**
     * @return BufferedReader of console or specified input file.
     * @throws FileNotFoundException - If specified file is not present this exception is thrown
     */
    private @NotNull BufferedReader BufferedReader() throws FileNotFoundException {
        Reader reader = ShouldReadFromConsole() ? new InputStreamReader(System.in) : new FileReader(InputName);
        return new BufferedReader(reader);
    }

    /**
     * Checks whether input should be read from the console.
     * @return Boolean.
     */
    private boolean ShouldReadFromConsole() {
        return InputName.equalsIgnoreCase("console");
    }

    /**
     * @param line : Line of next read.
     * @return Line parsed to int array.
     */
    private static int[] LineAsNumbers(String line) {
        return line.chars().map(Character::getNumericValue).toArray();
    }

    private final @NotNull String InputName;
    private int Size;
}
