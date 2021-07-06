package MainTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) throws FileNotFoundException {
        String file = Util.readFile("src\\main\\resources\\part1.txt");
        System.out.println(file);
        System.out.println(convert1(file));


    }

    public static String convert1(String input) {
        StringBuilder result = new StringBuilder("GO\n");
        Pattern pattern1 = Pattern.compile("\\n.+?;");
        Pattern pattern2 = Pattern.compile("\\n.+;*+\\r");

        Matcher matcher1 = pattern1.matcher(input);
        Matcher matcher2 = pattern2.matcher(input);
        while (matcher2.find()) {
//            result.append(input, matcher1.start(), matcher1.end() - 1);
            result.append(input, matcher2.start()+1, matcher2.end() - 1).append("\n");
        }
        return result.toString();
    }
}

class Util {

    private static final String ENCODING = "Cp1251";

    public static String readFile(String path) {
        String res = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            res = new String(bytes, ENCODING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
