package PhoneBookApp;

import PhoneBookUtils.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class setApp {

    public static Scanner scan = new Scanner(System.in);
    public static myPhoneBook myPhoneBook = new myPhoneBook();
    public static PhoneBookAppMethods fun = new PhoneBookAppMethods();
    public static final Map<Integer, String> menu = myPhoneBook.generatePhoneBookMenu();
    public static ArrayList<Contact> contactsList = new ArrayList<>();
    //public static final HashMap<String, String> texts = myPhoneBook.


}
