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

        if (countingStrategy == CountingStrategy.BYTES) {
            while (inputStream.read() != -1) {
                count++;
            }
            inputStream.reset();
        }

        if (countingStrategy == CountingStrategy.LINES) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }
            inputStream.reset();
        }

        if (countingStrategy == CountingStrategy.WORDS) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<String> wordsInLine = new ArrayList<>(Arrays.asList(line.split("[ \\r\\n\\f\\t\\v]")));
                wordsInLine.removeAll(List.of(""));
                count += wordsInLine.size();
            }
            inputStream.reset();
        }

        if (countingStrategy == CountingStrategy.CHARS) {
            try (InputStreamReader reader = new InputStreamReader(inputStream)) {
                while ((reader.read()) != -1) {
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return count;
    }
}
