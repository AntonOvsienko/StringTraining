package MainTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Book {
    ArrayList<String> book;

    public Book(String linkURL) {
        this.book = new ArrayList<>(getTextFromFile(linkURL));
    }

    public static ArrayList<String> getTextFromFile(String linkURL) {
        String setBooks = new String();
        String stringPattern = "\\.";
        Pattern pattern=Pattern.compile(stringPattern);
        try (Scanner scanner = new Scanner(new File(linkURL))) {
            while (scanner.hasNext()) {
                setBooks += scanner.nextLine() + " ";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] bookString = pattern.split(setBooks.trim());
        return new ArrayList<>(Arrays.asList(bookString));
    }

    @Override
    public String toString() {
        String alltext = new String();
        for (int i = 0; i < book.size(); i++) {
            System.out.println(i + 1 + ". " + book.get(i).trim() + ".");
        }
        return alltext;
    }
}
