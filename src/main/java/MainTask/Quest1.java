package MainTask;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Quest1 {
    static ArrayList<String> bookOfStrings;

    public static void main(String[] args) {
        Book book = new Book("src\\main\\resources\\Book");
        System.out.println(book);

    }
}
