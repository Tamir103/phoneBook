package PhoneBookApp;

import PhoneBookUtils.Contact;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

 public class SetApp {
     private static SetApp mSetApp;
     public Scanner scan;
     public PhoneBookApp.myPhoneBook myPhoneBook;
     public PhoneBookAppMethods fun;
     public validationMethods validation;
     public Map<Integer, String> menu;
     public ArrayList<Contact> contactsList;

     public SetApp() {
         scan = new Scanner(System.in);
         myPhoneBook = new myPhoneBook();
         fun = new PhoneBookAppMethods();
         validation = new validationMethods();
         menu = myPhoneBook.generatePhoneBookMenu();
         contactsList = new ArrayList<>();
         //public final HashMap<String, String> texts = myPhoneBook.
     }

     public static SetApp getInstance() {
         if (mSetApp == null) {
             mSetApp = new SetApp();
             return mSetApp;
         }
         return mSetApp;
     }
}
