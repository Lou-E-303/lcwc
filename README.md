# Intro

This is a `wc` 'clone' which reads from either a provided text file or standard input by default and can:

- Output the byte count with the command line option `-c`.
- Output the line count with the command line option `-l`.
- Output the word count with the command line option `-w`.
- Output the char count with the command line option `-m`.

If no option is provided then it outputs them all. The output is prefixed with the filename and is thus ordered as follows:

`[FILENAME] [BYTES] [LINES] [WORDS] [CHARS]`

# Run

- Build `.jar` file e.g. in IntelliJ `Build -> Build Artifacts... -> Build`.
- Navigate to `/lcwc_jar` and run `java -jar lcwc.jar [options] [filepath]`.

# Links

Link to challenge [here](https://codingchallenges.fyi/challenges/challenge-wc/) with thanks to John Crickett.