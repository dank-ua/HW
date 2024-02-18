package org.example.hw10;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRiderTest {
    // public static final String VAR_1 = "\\d{3}-\\d{3}-\\d{4}";
    // public static final String VAR_2 ="\\(\\d{3}\\) \\d{3}-\\d{4}";
    public static void fileFinder() {

        try (FileReader reader = new FileReader("file.txt")) {
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                String str = new String(buf);

                String var12 = "(\\d{3}-\\d{3}-\\d{4})|(\\(\\d{3}\\) \\d{3}-\\d{4})";
                Pattern var = Pattern.compile(var12);
                Matcher mat = var.matcher(str);

                while (mat.find()) {
                    System.out.println(mat.group());
                }


            }
        } catch (IOException d) {
            System.out.println(d.getMessage());

        }
    }
}
