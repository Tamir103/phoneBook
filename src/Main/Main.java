package Main;

import PhoneBookApp.setApp;
import PhoneBookUtils.*;

public class Main extends setApp {
    public static void main(String[] args) {

        boolean exit = false;
        while (!exit) {
            fun.printMenu();
            int menuInput = fun.enterMenuChoice();
            switch (menuInput) {
                case 1:
                    Contact contact = myPhoneBook.createContact();
                    int listSizeBeforeAdd = contactsList.size();
                    contactsList = myPhoneBook.addContact(contact, contactsList);
                    int listSizeAfterAdd = contactsList.size();
                    if (listSizeBeforeAdd == listSizeAfterAdd || contact == null) {
                        exit = true;
                    }
                    break;
                case 2:
                    break;
                case 3:
                    myPhoneBook.printPhoneBook(contactsList);
                    break;
                case 11:
                    System.out.println(texts.exit);
                    exit = true;
                    break;
            }
        }
    }
}
