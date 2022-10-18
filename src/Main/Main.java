package Main;

import PhoneBookApp.setApp;
import PhoneBookUtils.*;

import java.util.ArrayList;

public class Main extends setApp {
    static ArrayList<Contact> generateTestList() {
        ArrayList<Contact> list = new ArrayList<>();
        Contact c1 = new Contact();
        Contact c2 = new Contact();
        Contact c3 = new Contact();
        c1.setName("Tuvia the cate");
        c1.setPhoneNumber("0544444444");
        c2.setName("Zen the dog");
        c2.setPhoneNumber("089999999");
        c3.setName("Osho is also a dog");
        c3.setPhoneNumber("0522222222");
        list.add(c1);
        list.add(c2);
        list.add(c3);
        return list;
    }
    public static void main(String[] args) {
        ArrayList<Contact> testList = generateTestList();

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
                    myPhoneBook.printPhoneBook(/*contactsList*/ testList);
                    break;
                case 11:
                    System.out.println(texts.exit);
                    exit = true;
                    break;
            }
        }
    }
}
