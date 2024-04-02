package counter;

import constants.CountingStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FromFileCounter implements Counter {
    File inputFile;
    public FromFileCounter(File inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public long count(CountingStrategy countingStrategy) {
        long count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            switch(countingStrategy) {
                case BYTES:
                    count = countBytes();
                    break;
                case LINES:
                    count = countLines(reader);
                    break;
                case WORDS:
                    count = countWords(reader);
                    break;
                case CHARS:
                    count = countChars(reader);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    private long countBytes() {
        return inputFile.length();
    }

    private long countLines(BufferedReader reader) throws IOException {
        long count = 0;

        while ((reader.readLine()) != null) {
            count++;
        }

        return count;
    }

    private long countWords(BufferedReader reader) throws IOException {
        long count = 0;

        String line;

        while ((line = reader.readLine()) != null) {
            List<String> wordsInLine = new ArrayList<>(Arrays.asList(line.split("[ \\r\\n\\f\\t\\v]")));
            wordsInLine.removeAll(List.of(""));
            count += wordsInLine.size();
        }

        return count;
    }

    private long countChars(BufferedReader reader) throws IOException {
        long count = 0;

        while ((reader.read()) > 0)
        {
            count++;
        }
        return count;
    }
}
