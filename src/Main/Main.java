package Main;

import PhoneBookApp.PhoneBookAppMethods;
import PhoneBookApp.setApp;
import PhoneBookUtils.Contact;

import java.util.ArrayList;

public class Main extends setApp {
    static ArrayList<Contact> generateTestList() {
        ArrayList<Contact> list = new ArrayList<>();
        Contact c1 = new Contact();
        Contact c2 = new Contact();
        Contact c3 = new Contact();
        Contact c4 = new Contact();
        Contact c5 = new Contact();
        c1.setName("Tuvia the cat");
        c1.setPhoneNumber("0544444444");
        c2.setName("Zen the dog");
        c2.setPhoneNumber("089999999");
        c3.setName("Osho is also a dog");
        c3.setPhoneNumber("0522222222");
        c4.setName("tuvia the cat");
        c4.setPhoneNumber("0544444444");
        c5.setName("Tuvia the cat");
        c5.setPhoneNumber("098765432");
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Contact> testList = generateTestList();

        boolean exit = false;
        System.out.println(myPhoneBook.textsMap.get("welcome"));
        while (!exit) {
            fun.printMenu();
            String menuChoice = scan.nextLine();
            int menuInput = fun.menuChoice(menuChoice);
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
                case 2:  // TODO organize and maybe export to some methods
                    int listSize = /*contactsList.size();*/ testList.size();
                    for (int i = 0; i < 3; i++) {
                        System.out.println(myPhoneBook.textsMap.get("removeContact"));
                        String nameOrPhone = scan.nextLine();
                        if (validation.validateYorN(nameOrPhone)) { //TODO replace validation, maybe use findContact to validate
                            System.out.println(myPhoneBook.textsMap.get("removeAll"));
                            String removeAll = scan.nextLine();
                            if (validation.validateYorN(removeAll) && removeAll.equalsIgnoreCase("Y")) {
                                //   contactsList = myPhoneBook.removeContact(contactsList, nameOrPhone, true);
                                testList = myPhoneBook.removeContact(testList, nameOrPhone, true);
                                if (listSize == testList.size()){
                                    fun.printErrorMessages(8);
                                }
                            } else if (validation.validateYorN(removeAll) && removeAll.equalsIgnoreCase("N")) {
                                //   contactsList = myPhoneBook.removeContact(contactsList,nameOrPhone, false);
                                testList = myPhoneBook.removeContact(testList, nameOrPhone, false);
                                if (listSize == testList.size()){
                                    fun.printErrorMessages(8);
                                }
                            } else {
                                fun.printErrorMessages(8);
                            }
                        } else {
                            fun.printErrorMessages(8);
                        }
                    }
                    break;
                case 3:
                    myPhoneBook.printPhoneBook(/*contactsList*/ testList);
                    break;
                case 4:
                    for (int i = 0; i < 3; i++) {
                        System.out.println(myPhoneBook.textsMap.get("enterName"));
                        String name = scan.nextLine();
                        if (validation.isOnlyEnglishLetters(name)) {
                            ArrayList<Contact> contactsFound = myPhoneBook.findContact(testList, name);
                          //  ArrayList<Contact> contactsFound = myPhoneBook.findContact(contactsList, name);
                            if (contactsFound.size() > 0) {
                                myPhoneBook.printPhoneBook(contactsFound);
                                break;
                            } else {
                                fun.printErrorMessages(8);
                            }
                        } else {
                            fun.printErrorMessages(fun.calculateMessageIndex(i, true, true));
                        }
                    }
                    break;
                case 11:
                    exit = true;
                    break;
            }
            if (exit) {
                System.out.println(myPhoneBook.textsMap.get("exit"));
            } else {
                System.out.println(myPhoneBook.textsMap.get("anotherAction"));
            }
        }
    }
}
