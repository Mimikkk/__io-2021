package pl.poznan.put.cs.io.errors.storage;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Controls the saving of data from the given input.
 */
public class OutputStorage {
    /**
     * Tries to save input data from a given list number.
     *
     * @param numbers - Given numbers to save.
     * @throws IOException - Whether neither WriterBuffer can be accessed this exception is thrown.
     */
    public void TrySave(@NotNull List<Integer> numbers) throws IOException {
        try (BufferedWriter output = BufferedWriter()) {
            output.write(String.join("\n", NumbersAsStrings(numbers)));
        }
    }

    /**
     * @param outputName - filename of the output data. Can be 'console' for console output.
     */
    public OutputStorage(@NotNull String outputName) {
        OutputName = outputName;
    }

    /**
     * @param numbers - Requested numbers.
     * @return A list of string versions given numbers.
     */
    private static @NotNull List<String> NumbersAsStrings(@NotNull List<Integer> numbers) {
        return numbers.stream().map(Objects::toString).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @return BufferedWriter of console or specified input file.
     * @throws IOException - If the specified file can not be opened this exception is thrown.
     */
    private @NotNull BufferedWriter BufferedWriter() throws IOException {
        Writer writer = ShouldOutputToConsole() ? new OutputStreamWriter(System.out) : new FileWriter(OutputName);
        return new BufferedWriter(writer);
    }

    /**
     * Checks whether output should be written into the console.
     *
     * @return Boolean.
     */
    private boolean ShouldOutputToConsole() {
        return OutputName.equalsIgnoreCase("console");
    }

    private final @NotNull String OutputName;
}
