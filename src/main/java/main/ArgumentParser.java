package main;

import constants.CounterAppArgs;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArgumentParser {

    private ArgumentParser() {}
    public static CounterAppArgs parse(String[] args) {
        if (args.length == 0) {
            return new CounterAppArgs(true, true, true, true);
        }

        String lastArg = args[args.length - 1];
        File inputFile = new File(lastArg);

        var separatedArgs = Arrays.asList(args);

        AtomicBoolean bytes = new AtomicBoolean(false);
        AtomicBoolean lines = new AtomicBoolean(false);
        AtomicBoolean words = new AtomicBoolean(false);
        AtomicBoolean chars = new AtomicBoolean(false);

        separatedArgs.stream()
                .filter(arg -> arg.startsWith("-"))
                .forEach(arg -> {
                    switch (arg) {
                        case "-c":
                            bytes.set(true);
                            break;
                        case "-l":
                            lines.set(true);
                            break;
                        case "-w":
                            words.set(true);
                            break;
                        case "-m":
                            chars.set(true);
                            break;
                        default:
                            throw new IllegalArgumentException("Error: Unsupported argument '" + arg + "'. " +
                                    "Expected one or more of: -c, -l, -w, -m, followed by a single input file.");
                    }
                });


        if (inputFile.exists()) {
            if (args.length == 1) {
                return new CounterAppArgs(inputFile, true, true, true, true);
            }
            return new CounterAppArgs(inputFile, bytes.get(), lines.get(), words.get(), chars.get());
        } else {
            return new CounterAppArgs(bytes.get(), lines.get(), words.get(), chars.get());
        }
    }
}
