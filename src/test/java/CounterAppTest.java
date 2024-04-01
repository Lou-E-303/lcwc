import constants.CounterAppArgs;
import main.CounterApp;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CounterAppTest {
    @Test
    void givenArgsContainingSingleOptionThenOutputContainsCorrespondingCountOnly() throws IOException {
        String inputFileName = "src/test/resources/theArtOfWar.txt";
        File inputFile = new File(inputFileName);

        CounterAppArgs inputFileAndLineOption = new CounterAppArgs(inputFile, false, true, false, false);

        String output = CounterApp.generateOutput(inputFileAndLineOption);

        assertEquals("LINES: 7145\n", output);
    }

    @Test
    void givenArgsContainingAllOptionsThenOutputContainsAll() throws IOException {
        String inputFileName = "src/test/resources/theArtOfWar.txt";
        File inputFile = new File(inputFileName);

        CounterAppArgs inputFileAndAllOptions = new CounterAppArgs(inputFile, true, true, true, true);

        String output = CounterApp.generateOutput(inputFileAndAllOptions);

        assertEquals("\nBYTES: 342190\nLINES: 7145\nWORDS: 58164\nCHARS: 339292\n", output);
    }

    @Test
    void givenArgsContainingNoOptionAndEmptyFileThenReturnEmptyString() throws IOException {
        String inputFileName = "src/test/resources/emptyTest.txt";
        File inputFile = new File(inputFileName);

        CounterAppArgs inputFileAndNoOption = new CounterAppArgs(inputFile, false, false, false, false);

        String output = CounterApp.generateOutput(inputFileAndNoOption);

        assertEquals("", output);
    }

    @Test
    void givenArgsContainingOptionsAndNoInputFileThenOutputContainsCorrectOptions() throws IOException {
        setInput("abcd\nabcd");

        CounterAppArgs noInputFileAndCharacterOption = new CounterAppArgs(false, false, false, true);

        String output = CounterApp.generateOutput(noInputFileAndCharacterOption);

        assertEquals("CHARS: 9\n", output);
    }

    @Test
    void givenArgsContainingNoOptionsAndNoInputFileThenOutputContainsAll() throws IOException {
        setInput("abcd\nabcd");

        CounterAppArgs noInputFileAndCharacterOption = new CounterAppArgs(true, true, true, true);

        String output = CounterApp.generateOutput(noInputFileAndCharacterOption);

        assertEquals("\nBYTES: 9\nLINES: 2\nWORDS: 2\nCHARS: 9\n", output);
    }
    private void setInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
