package main;

import constants.CounterAppArgs;

import java.io.File;
import java.util.Arrays;

public class ArgumentParser {

    private ArgumentParser() {}
    public static CounterAppArgs parse(String[] args) {
        if (args.length == 0) {
            return new CounterAppArgs(true, true, true, true);
        }

        String lastArg = args[args.length - 1];
        File inputFile = new File(lastArg);

        var separatedArgs = Arrays.asList(args);
        boolean bytes = separatedArgs.contains("-c");
        boolean lines = separatedArgs.contains("-l");
        boolean words = separatedArgs.contains("-w");
        boolean chars = separatedArgs.contains("-m");

        if (inputFile.exists()) {
            if (args.length == 1) {
                return new CounterAppArgs(inputFile, true, true, true, true);
            }
            return new CounterAppArgs(inputFile, bytes, lines, words, chars);
        } else {
            return new CounterAppArgs(bytes, lines, words, chars);
        }
    }
}
