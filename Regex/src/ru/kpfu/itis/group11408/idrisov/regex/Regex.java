package ru.kpfu.itis.group11408.idrisov.regex;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}");
        File file = new File("test.txt");
        BufferedReader in = new BufferedReader(new FileReader(file));
        int lines = 0;
        int matches = 0;
        PrintWriter writer = null;
        writer = new PrintWriter("out.txt");
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            lines++;
            Matcher matcher = pattern.matcher(line.toUpperCase());
            if (matcher.matches()) {
                writer.println(lines + ": '" + line + "'");
                System.out.println(lines + ": '" + line + "'");
                matches++;
            }
        }
        writer.close();
        if (matches == 0) {
            System.out.println("No matches in " + lines + " lines");
        } else {
            System.out.println("\n" + " Total: " + matches + " e-mails");
        }
    }
}