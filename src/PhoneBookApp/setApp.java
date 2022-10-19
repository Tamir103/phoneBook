package PhoneBookApp;

import PhoneBookApp.PhoneBookAppMethods;
import PhoneBookApp.myPhoneBook;
import PhoneBookApp.validationMethods;
import PhoneBookUtils.Contact;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class setApp {

    public static Scanner scan = new Scanner(System.in);
    public static PhoneBookApp.myPhoneBook myPhoneBook = new myPhoneBook();
    public static PhoneBookAppMethods fun = new PhoneBookAppMethods();
    public static validationMethods validation = new validationMethods();
    public static final Map<Integer, String> menu = myPhoneBook.generatePhoneBookMenu();
    public static ArrayList<Contact> contactsList = new ArrayList<>();
    //public static final HashMap<String, String> texts = myPhoneBook.


}
