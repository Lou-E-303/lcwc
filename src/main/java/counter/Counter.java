package counter;

import constants.CountingStrategy;

import java.io.IOException;

public interface Counter {
    long count(CountingStrategy countingStrategy) throws IOException;
}
