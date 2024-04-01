package constants;

import java.io.File;

public class CounterAppArgs {
    final File inputFile;
    final boolean BYTES;
    final boolean LINES;
    final boolean WORDS;
    final boolean CHARS;

    public CounterAppArgs(boolean bytes, boolean lines, boolean words, boolean characters) {
        this.inputFile = null;
        this.BYTES = bytes;
        this.LINES = lines;
        this.WORDS = words;
        this.CHARS = characters;
    }

    public CounterAppArgs(File inputFile, boolean bytes, boolean lines, boolean words, boolean characters) {
        this.inputFile = inputFile;
        this.BYTES = bytes;
        this.LINES = lines;
        this.WORDS = words;
        this.CHARS = characters;
    }

    public File getInputFile() {
        return inputFile;
    }

    public boolean getBytesInputArg() {
        return BYTES;
    }

    public boolean getLinesInputArg() {
        return LINES;
    }

    public boolean getWordsInputArg() {
        return WORDS;
    }

    public boolean getCharactersInputArg() {
        return CHARS;
    }
}
