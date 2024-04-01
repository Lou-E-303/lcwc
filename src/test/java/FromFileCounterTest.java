import constants.CountingStrategy;
import counter.FromFileCounter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FromFileCounterTest {
    @ParameterizedTest
    @EnumSource(CountingStrategy.class)
    void ifTextFileIsEmptyThenReturnZeroForAnyCountingStrategy(CountingStrategy strategy) {
        File file = new File("src/test/resources/emptyTest.txt");
        FromFileCounter counter = new FromFileCounter(file);

        long count = counter.count(strategy);

        assertEquals(0, count);
    }

    @Test
    void ifStrategyIsBytesThenReturnCorrectByteCount() {
        File file = new File("src/test/resources/theArtOfWar.txt");
        FromFileCounter counter = new FromFileCounter(file);

        long byteCount = counter.count(CountingStrategy.BYTES);

        assertEquals(342190, byteCount);
    }

    @Test
    void ifStrategyIsLinesThenReturnCorrectLineCount() {
        File file = new File("src/test/resources/theArtOfWar.txt");
        FromFileCounter counter = new FromFileCounter(file);

        long lineCount = counter.count(CountingStrategy.LINES);

        assertEquals(7145, lineCount);
    }

    @Test
    void ifStrategyIsWordsThenReturnCorrectWordCount() {
        File file = new File("src/test/resources/theArtOfWar.txt");
        FromFileCounter counter = new FromFileCounter(file);

        long wordCount = counter.count(CountingStrategy.WORDS);

        assertEquals(58164, wordCount);
    }

    @Test
    void ifStrategyIsCharactersThenReturnCorrectCharacterCount() {
        File file = new File("src/test/resources/theArtOfWar.txt");
        FromFileCounter counter = new FromFileCounter(file);

        long characterCount = counter.count(CountingStrategy.CHARS);

        assertEquals(339292, characterCount);
    }

    @Test
    void ifTextFileContainsMultiByteCharactersThenReturnCorrectCharacterCount() {
        File file = new File("src/test/resources/multiByte.txt");
        FromFileCounter counter = new FromFileCounter(file);

        long characterCount = counter.count(CountingStrategy.CHARS);

        assertEquals(4, characterCount);
    }
}
