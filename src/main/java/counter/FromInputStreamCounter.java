package counter;

import constants.CountingStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FromInputStreamCounter implements Counter {
    InputStream inputStream;

    public FromInputStreamCounter(InputStream inputStream) {
        this.inputStream = inputStream;
        inputStream.mark(Integer.MAX_VALUE);
    }

    @Override
    public long count(CountingStrategy countingStrategy) throws IOException {
        long count = 0;

        switch(countingStrategy) {
            case BYTES:
                count = countBytes();
                break;
            case LINES:
                count = countLines();
                break;
            case WORDS:
                count = countWords();
                break;
            case CHARS:
                count = countChars();
                break;
        }

        inputStream.reset();
        return count;
    }

    private long countBytes() {
        long count = 0;

        while (true) {
            try {
                if (inputStream.read() == -1) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            count++;
        }
        return count;
    }

    private long countLines() {
        long count = 0;
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            scanner.nextLine();
            count++;
        }

        return count;
    }

    private long countWords() {
        long count = 0;
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> wordsInLine = new ArrayList<>(Arrays.asList(line.split("[ \\r\\n\\f\\t\\v]")));
            wordsInLine.removeAll(List.of(""));
            count += wordsInLine.size();
        }

        return count;
    }

    private long countChars() {
        long count = 0;

        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            while ((reader.read()) != -1) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}
