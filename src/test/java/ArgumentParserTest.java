import constants.CounterAppArgs;
import main.ArgumentParser;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ArgumentParserTest {

    @Test
    void givenInputFileOnlyThenReturnCorrectArgs() {
        String inputFileName = "src/test/resources/theArtOfWar.txt";
        String[] args = {inputFileName};
        File inputFile = new File(inputFileName);

        CounterAppArgs counterAppArgs = ArgumentParser.parse(args);
        CounterAppArgs expectedArgs = new CounterAppArgs(inputFile, true, true, true, true);

        assertTrue(new ReflectionEquals(expectedArgs).matches(counterAppArgs));
    }

    @Test
    void givenInputFileAndUnsupportedInputArgsThenReturnCorrectArgs() {
        String inputFileName = "src/test/resources/theArtOfWar.txt";
        String[] args = {"-z", "-y", "-x", inputFileName};
        File inputFile = new File(inputFileName);

        CounterAppArgs counterAppArgs = ArgumentParser.parse(args);
        CounterAppArgs expectedArgs = new CounterAppArgs(inputFile, false, false, false, false);

        assertTrue(new ReflectionEquals(expectedArgs).matches(counterAppArgs));
    }

    @Test
    void givenInputFileAndSingleInputArgThenReturnCorrectArgs() {
        String inputFileName = "src/test/resources/theArtOfWar.txt";
        String[] args = {"-c", inputFileName};
        File inputFile = new File(inputFileName);

        CounterAppArgs counterAppArgs = ArgumentParser.parse(args);
        CounterAppArgs expectedArgs = new CounterAppArgs(inputFile, true, false, false, false);

        assertTrue(new ReflectionEquals(expectedArgs).matches(counterAppArgs));
    }

    @Test
    void givenInputFileAndMultipleInputArgsThenReturnCorrectArgs() {
        String inputFileName = "src/test/resources/theArtOfWar.txt";
        String[] args = {"-c", "-l", "-w", "-m", inputFileName};
        File inputFile = new File(inputFileName);

        CounterAppArgs counterAppArgs = ArgumentParser.parse(args);
        CounterAppArgs expectedArgs = new CounterAppArgs(inputFile, true, true, true, true);

        assertTrue(new ReflectionEquals(expectedArgs).matches(counterAppArgs));
    }

    @Test
    void givenMultipleInputArgsInDifferentOrderThenReturnCorrectArgs() {
        String inputFileName = "src/test/resources/theArtOfWar.txt";
        String[] args = {"-w", "-l", "-c", "-m", inputFileName};
        File inputFile = new File(inputFileName);

        CounterAppArgs counterAppArgs = ArgumentParser.parse(args);
        CounterAppArgs expectedArgs = new CounterAppArgs(inputFile, true, true, true, true);

        assertTrue(new ReflectionEquals(expectedArgs).matches(counterAppArgs));
    }

    @Test
    void givenNoFilePathGivenThenReturnCorrectArgs() {
        String[] args = {"-w", "-l", "-c"};

        CounterAppArgs counterAppArgs = ArgumentParser.parse(args);
        CounterAppArgs expectedArgs = new CounterAppArgs(true, true, true, false);

        assertTrue(new ReflectionEquals(expectedArgs).matches(counterAppArgs));
    }

    @Test
    void givenNoInputFileOrInputArgsGivenThenReturnCorrectArgs() {
        String[] args = {};

        CounterAppArgs counterAppArgs = ArgumentParser.parse(args);
        CounterAppArgs expectedArgs = new CounterAppArgs(true, true, true, true);

        assertTrue(new ReflectionEquals(expectedArgs).matches(counterAppArgs));
    }
}
