# Intro

This is a `wc` 'clone' which reads from either a provided text file or standard input by default and can:

- Output the byte count with the command line option `-c`.
- Output the line count with the command line option `-l`.
- Output the word count with the command line option `-w`.
- Output the char count with the command line option `-m`.

If no option is provided then it outputs them all.

## Differences from `wc`:

- The output is explicit about what each output line is e.g. `BYTES: 342190` etc.
- `lcwc` doesn't output the filename.
- The default option when no args except the input file are provided is to provide *all* information, and omit the filename.
- `lcwc` doesn't bother telling you when you've entered an illegal argument

# Run

- Build `.jar` file e.g. in IntelliJ `Build -> Build Artifacts... -> Build`.
- Navigate to `/lcwc_jar` and run `java -jar lcwc.jar [options] [filepath]`.

# Links

Link to challenge [here](https://codingchallenges.fyi/challenges/challenge-wc/) with thanks to John Crickett.