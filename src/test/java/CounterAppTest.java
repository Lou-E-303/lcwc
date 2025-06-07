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

        assertEquals("theArtOfWar.txt 7145", output);
    }

    @Test
    void givenArgsContainingAllOptionsThenOutputContainsAll() throws IOException {
        String inputFileName = "src/test/resources/theArtOfWar.txt";
        File inputFile = new File(inputFileName);

        CounterAppArgs inputFileAndAllOptions = new CounterAppArgs(inputFile, true, true, true, true);

        String output = CounterApp.generateOutput(inputFileAndAllOptions);

        assertEquals("theArtOfWar.txt 342190 7145 58164 339292", output);
    }

    @Test
    void givenArgsContainingNoOptionAndEmptyFileThenReturnEmptyString() throws IOException {
        String inputFileName = "src/test/resources/emptyTest.txt";
        File inputFile = new File(inputFileName);

        CounterAppArgs inputFileAndNoOption = new CounterAppArgs(inputFile, false, false, false, false);

        String output = CounterApp.generateOutput(inputFileAndNoOption);

        assertEquals("emptyTest.txt", output);
    }

    @Test
    void givenArgsContainingOptionsAndNoInputFileThenOutputContainsCorrectOptions() throws IOException {
        setInput();

        CounterAppArgs noInputFileAndCharacterOption = new CounterAppArgs(false, false, false, true);

        String output = CounterApp.generateOutput(noInputFileAndCharacterOption);

        assertEquals("9", output);
    }

    @Test
    void givenArgsContainingNoOptionsAndNoInputFileThenOutputContainsAll() throws IOException {
        setInput();

        CounterAppArgs noInputFileAndCharacterOption = new CounterAppArgs(true, true, true, true);

        String output = CounterApp.generateOutput(noInputFileAndCharacterOption);

        assertEquals("9 2 2 9", output);
    }

    private void setInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("abcd\nabcd".getBytes());
        System.setIn(in);
    }
}
