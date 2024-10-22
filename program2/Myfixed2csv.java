package program2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Myfixed2csv {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(
                    "No arguments entered yet!!\nUsage: myfixed2csv <fixed-width-file> <n-columns> <length1> <length2> …");
            return;
        }

        String filename = args[0];
        String noColumnsStr = args[1];
        try {
            int noColumns = Integer.parseInt(noColumnsStr);
            int[] columnslength = new int[noColumns];

            for (int index = 2; index < args.length; index++) {
                String numStr = args[index];
                try {
                    int numLength = Integer.parseInt(numStr);
                    columnslength[index - 2] = numLength;
                    

                } catch (Exception e) {
                    System.out.println("Error while parsing arguments String to Double Datatype...");
                }
            }
            readFile(filename, noColumns, columnslength);

        } catch (Exception e) {
            System.out.println("Error while parsing arguments String to Double Datatype...");
        }

    }

    public static void readFile(String filename, int col, int[] collen) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                SqrootCalculation sq = new SqrootCalculation(line, col, collen);
                String newline = sq.getOperatedLine();
                String newCsvFilePath = "/home/vishwap/Rebirth/java_assessment/program2/myfixed2csv.csv";
                writeIntoFile(newline, newCsvFilePath);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeIntoFile(String line, String filepath) {
        try {
            Path path = Paths.get(filepath);
            Files.writeString(path, line + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

class SqrootCalculation {

    String oldLine;
    int columns;
    int[] columnLengths;

    SqrootCalculation(String line, int col, int[] collen) {
        this.oldLine = line;
        this.columns = col;
        this.columnLengths = collen;
    }

    public String getOperatedLine() {
        int startLength = 0;
        int index = 0;
        String[] sb = new String[columns];
        for (int col : columnLengths) {
            int endLength = Math.min(startLength + col, oldLine.length());
            sb[index] = oldLine.substring(startLength, endLength).trim();
            startLength += col;
            index++;
        }
        String[] newWords = new String[columns];
        newWords = removeSpacesAndZeroes(sb);
        String line = getNewLinesWithCommas(newWords);
        return line;
    }

    public String[] removeSpacesAndZeroes(String[] sb) {
        String[] str = new String[columns];
        for (int index = 0; index < sb.length; index++) {
            str[index] = getCleanedWord(sb[index]);
        }
        return str;
    }

    public String getCleanedWord(String word) {
        StringBuilder sb = new StringBuilder();
        boolean isLeadingZero = true;
        String trimmedWord = word.trim();
        for (Character ch : trimmedWord.toCharArray()) {
            if (isLeadingZero && ch == '0') {
                continue;
            }
            isLeadingZero = false;
            sb.append(ch);
        }
        return sb.toString();

    }

    public String getNewLinesWithCommas(String[] sb) {
        StringBuilder str = new StringBuilder();
        for (int index = 0; index < sb.length; index++) {
            str.append("\"").append(sb[index]).append("\"");
            if (index < sb.length - 1) {
                str.append(",");
            }
        }
        return str.toString();
    }

}