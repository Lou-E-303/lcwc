package main;

import constants.CounterAppArgs;
import constants.CountingStrategy;
import counter.Counter;
import counter.FromFileCounter;
import counter.FromInputStreamCounter;

import java.io.File;
import java.io.IOException;

public class CounterApp {

    private CounterApp() {}
    public static String generateOutput(CounterAppArgs counterAppArgs) throws IOException {
        File inputFile = counterAppArgs.getInputFile();
        String output = "";
        Counter counter;

        if (inputFile == null) {
            counter = new FromInputStreamCounter(System.in);
        } else {
            output += inputFile.getName() + " ";
            counter = new FromFileCounter(inputFile);
        }

        if (counterAppArgs.getBytesInputArg()) {
            long count = counter.count(CountingStrategy.BYTES);

            output += count + " ";
        }

        if (counterAppArgs.getLinesInputArg()) {
            long count = counter.count(CountingStrategy.LINES);
            output += count + " ";
        }

        if (counterAppArgs.getWordsInputArg()) {
            long count = counter.count(CountingStrategy.WORDS);
            output += count + " ";
        }

        if (counterAppArgs.getCharactersInputArg()) {
            long count = counter.count(CountingStrategy.CHARS);
            output += count;
        }

        return output.trim();
    }
}
