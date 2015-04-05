package ru.kpfu.itis.group11408.idrisov.encoding;
import java.io.*;

/**
 * Created by Татагромодуль on 31.03.2015.
 */
public class Encoding {
    public static void main(String[] args) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("the-file-name.txt", "windows-1251");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println("The first line");
        writer.println("The second line");
        writer.println("Дискретное преобразование ");
        writer.close();

        File fileDirs = new File("the-file-name.txt");

        BufferedReader in = null;

        try {
            in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(fileDirs)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String str;

        try {
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
