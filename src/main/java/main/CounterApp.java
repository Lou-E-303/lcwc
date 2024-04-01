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
        String output = "";
        File inputFile = counterAppArgs.getInputFile();
        Counter counter;

        if (inputFile == null) {
            counter = new FromInputStreamCounter(System.in);
        } else {
            counter = new FromFileCounter(inputFile);
        }

        if (counterAppArgs.getBytesInputArg()) {
            long count = counter.count(CountingStrategy.BYTES);

            output += "\nBYTES: " + count + "\n";
        }

        if (counterAppArgs.getLinesInputArg()) {
            long count = counter.count(CountingStrategy.LINES);
            output += "LINES: " + count + "\n";
        }

        if (counterAppArgs.getWordsInputArg()) {
            long count = counter.count(CountingStrategy.WORDS);
            output += "WORDS: " + count + "\n";
        }

        if (counterAppArgs.getCharactersInputArg()) {
            long count = counter.count(CountingStrategy.CHARS);
            output += "CHARS: " + count + "\n";
        }

        return output;
    }
}
