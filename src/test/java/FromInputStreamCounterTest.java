import constants.CountingStrategy;
import counter.FromInputStreamCounter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FromInputStreamCounterTest {
    FromInputStreamCounter counter;

    @ParameterizedTest
    @EnumSource(CountingStrategy.class)
    void ifInputStreamIsEmptyThenReturnZeroForAnyCountingStrategy(CountingStrategy strategy) throws IOException {
        setInput("");

        long count = counter.count(strategy);

        assertEquals(0, count);
    }

    @Test
    void ifStrategyIsBytesThenReturnCorrectByteCount() throws IOException {
        setInput("abcd\nabcd");

        long byteCount = counter.count(CountingStrategy.BYTES);

        assertEquals(9, byteCount);
    }

    @Test
    void ifStrategyIsLinesThenReturnCorrectLineCount() throws IOException {
        setInput("abcd\nabcd\nabcd");

        long lineCount = counter.count(CountingStrategy.LINES);

        assertEquals(3, lineCount);
    }

    @Test
    void ifStrategyIsWordsThenReturnCorrectWordCount() throws IOException {
        setInput("a few\n words\n with some line breaks   \n randomly inserted\n");

        long wordCount = counter.count(CountingStrategy.WORDS);

        assertEquals(9, wordCount);
    }

    @Test
    void ifStrategyIsCharacterThenReturnCorrectCharacterCount() throws IOException {
        setInput("abcd\nabcd");

        long characterCount = counter.count(CountingStrategy.CHARS);

        assertEquals(9, characterCount);
    }

    @Test
    void ifInputContainsMultiByteCharactersThenReturnCorrectByteCount() throws IOException {
        setInput("ÀÁÂÃ");

        long byteCount = counter.count(CountingStrategy.BYTES);

        assertEquals(8, byteCount);
    }

    @Test
    void ifInputContainsMultiByteCharactersThenReturnCorrectCharacterCount() throws IOException {
        setInput("ÀÁÂÃ");

        long characterCount = counter.count(CountingStrategy.CHARS);

        assertEquals(4, characterCount);
    }

    private void setInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        counter = new FromInputStreamCounter(inputStream);
    }
}
