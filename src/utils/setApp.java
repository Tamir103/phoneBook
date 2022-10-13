package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class setApp {

    public static final validationMethods validate = new validationMethods();
    public static final phoneBookFunctions fun = new phoneBookFunctions();
    public static final appTexts texts = new appTexts();
    Scanner scan = new Scanner(System.in);
    final Map<Integer, String> menu = generateMenu();
    public static ArrayList<Contact> contactsList = new ArrayList<>();

    private Map<Integer, String> generateMenu() {
        Map<Integer, String> menu = new HashMap<>();
        menu.put(1, "Add contact");
        menu.put(2, "Delete contact");
        menu.put(3, "Print all contacts");
        menu.put(4, "Search and print contact");
        menu.put(5, "Sort by name alphabetically: A-Z and print");
        menu.put(6, "Sort by phone - largest to smallest number");
        menu.put(7, "Sort by name alphabetically: Z-A and print before and after");
        menu.put(8, "Remove duplicates and print them");
        menu.put(9, "Save phone book to a file");
        menu.put(10, "Upload phone book from a file");
        menu.put(11, "Exit");

        return menu;
    }
}
