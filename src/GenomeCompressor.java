/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {
    private static final char A = 0;  // 00
    private static final char C = 1;  // 01
    private static final char T = 2;  // 10
    private static final char G = 3;  // 11

    // Map the DNA to it's number code
    public static char letterToNumber (char letter) {
        switch (letter) {
            case 'A' -> { return A; }
            case 'C' -> { return C; }
            case 'T' -> { return T; }
            case 'G' -> { return G; }
        }
        return 0;
    }

    // Map the number to it's DNA letter code
    public static char numberToLetter (char number) {
        switch (number) {
            case 0 -> { return 'A'; }
            case 1 -> { return 'C'; }
            case 2 -> { return 'T'; }
            case 3 -> { return 'G'; }
        }
        return 0;
    }

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        String sequence = BinaryStdIn.readString();
        int sequenceLength = sequence.length();
        BinaryStdOut.write(sequenceLength);

        // Goes through the entire sequence and writes a 2 bit number out to the file.
        for (int i = 0; i < sequenceLength; i++) {
            char letter = sequence.charAt(i);
            char number = letterToNumber(letter);
            BinaryStdOut.write(number, 2);
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        int sequenceLength = BinaryStdIn.readInt();

        // Goes through the entire sequence and writes an 8 bit letter out to the file.
        for (int i = 0; i < sequenceLength; i++) {
            char number = BinaryStdIn.readChar(2);
            char letter = numberToLetter(number);
            BinaryStdOut.write(letter, 8);
        }
        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}