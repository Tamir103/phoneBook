package RunClasses;

import utils.*;

public class Main extends setApp {
    public static void main(String[] args){

        fun.printMenu();
        int menuInput = fun.enterMenuChoice();
        if (menuInput == 1) {
            Contact contact = fun.createContact();
            fun.addContact(contact);
            for (Contact c: contactsList) {
                System.out.println(c.getName());
                System.out.println(c.getPhoneNumber());
            }
        }


    }
}
