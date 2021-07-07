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
    public static final String ARROW = " ==> ";

    public static void main(String[] args) throws FileNotFoundException {
        String file = Util.readFile("src\\main\\resources\\part1.txt");
        System.out.println(file);
//        System.out.println(convert1(file));
//        System.out.println(convert2(file));
        System.out.println(convert3(file));


    }

    public static String convert1(String input) {
        StringBuilder result = new StringBuilder("GO\n");
        Pattern pattern1 = Pattern.compile("\n[a-z]+");
        Pattern pattern2 = Pattern.compile(";\\w+@.+(com)");

        Matcher matcher1 = pattern1.matcher(input);
        Matcher matcher2 = pattern2.matcher(input);
        while (matcher1.find() & matcher2.find()) {
            result.append(input, matcher1.start() + 1, matcher1.end());
            result.append(": ").append(input, matcher2.start() + 1, matcher2.end()).append("\n");
        }
        return result.toString();
    }

    public static String convert2(String input) {
        StringBuilder result = new StringBuilder("GO\n");
        Pattern pattern1 = Pattern.compile("\\s[A-Z]\\w+");
        Pattern pattern4 = Pattern.compile("[A-Z]\\w+\\s+[A-Z]");
        Pattern pattern2 = Pattern.compile("\\w+@.+(.com)");
        Pattern pattern3 = Pattern.compile("[Ee]mail");

        Matcher matcher1 = pattern1.matcher(input);
        Matcher matcher2 = pattern2.matcher(input);
        Matcher matcher3 = pattern3.matcher(input);
        Matcher matcher4 = pattern4.matcher(input);
        while (matcher1.find() & matcher2.find() & matcher3.find(1) & matcher4.find()) {
            result.append(input, matcher1.start(), matcher1.end()).append(" ");
            result.append(input, matcher4.start(), matcher4.end() - 1).append("(");
            result.append(input.toLowerCase(), matcher3.start(), matcher3.end()).append(": ");
            result.append(input, matcher2.start(), matcher2.end()).append(")\n");
        }
        return result.toString();
    }

    public static String convert3(String input) {
        StringBuilder result = new StringBuilder();
        String domensearch = "((?<=@).+)";
        String domennamenegative=new String();
        Pattern patterndomen = Pattern.compile(domensearch);
        Matcher matcher = patterndomen.matcher(input);

        while (matcher.find()) {
            StringBuilder domennname = new StringBuilder();
            domennname.append(input, matcher.start(), matcher.end());
            Pattern patternname = Pattern.compile("\\w+(?=@" + domennname.toString()+")");
            Matcher matcher1 = patternname.matcher(input);
            result.append(domennname).append(ARROW);
            domennamenegative+="(?!"+domennname+")";
            while (matcher1.find()) {
                result.append(input, matcher1.start(), matcher1.end()).append(" ");
            }
            result.append("\n");
            domensearch = "(?<=@)" + domennamenegative + ".+";
            patterndomen=Pattern.compile(domensearch);
            matcher=patterndomen.matcher(input);
        }


        return result.toString();
    }

    public static String convert4(String input) {
        StringBuilder result = new StringBuilder("GO\n");
        StringBuilder result1 = new StringBuilder("mail.com" + ARROW);
        StringBuilder result2 = new StringBuilder("google.com" + ARROW);
        Pattern pattern1 = Pattern.compile("[A-Za-z]+@mail[.]com");
        Pattern pattern2 = Pattern.compile("[A-Za-z]+@google[.]com");

        Matcher matcher1 = pattern1.matcher(input);
        Matcher matcher2 = pattern2.matcher(input);
        while (matcher1.find()) {
            result1.append(" ").append(input, matcher1.start(), matcher1.end() - 9).append(",");
        }
        while (matcher2.find()) {
            result2.append(" ").append(input, matcher2.start(), matcher2.end() - 11).append(",");
        }
        result1.deleteCharAt(result1.length() - 1);
        result2.deleteCharAt(result2.length() - 1);
        return result.append(result1.append("\n").append(result2)).toString();
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
