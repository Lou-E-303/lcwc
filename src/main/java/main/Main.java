package main;

import constants.CounterAppArgs;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CounterAppArgs counterAppArgs = ArgumentParser.parse(args);
        String output = CounterApp.generateOutput(counterAppArgs);

        System.out.println(output);
    }
}