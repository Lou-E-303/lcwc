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

        if (countingStrategy.equals(CountingStrategy.BYTES)) {
            return inputFile.length();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            if (countingStrategy.equals(CountingStrategy.CHARS)) {
                while ((reader.read()) > 0)
                {
                    count++;
                }
                return count;
            }

            String line;

            while ((line = reader.readLine()) != null) {
                if (countingStrategy.equals(CountingStrategy.LINES)) {
                    count++;
                } else if (countingStrategy.equals(CountingStrategy.WORDS)) {
                    List<String> wordsInLine = new ArrayList<>(Arrays.asList(line.split("[ \\r\\n\\f\\t\\v]")));
                    wordsInLine.removeAll(List.of(""));
                    count += wordsInLine.size();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}
