package Main;

import PhoneBookApp.myPhoneBook;
import PhoneBookApp.SetApp;
import PhoneBookUtils.Contact;

import java.util.ArrayList;

public class Main {
    //TODO when the need of testList is over, delete it an generateTestList, and restore and test contactsList
    static ArrayList<Contact> generateTestList() {
        ArrayList<Contact> list = new ArrayList<>();
        Contact c1 = new Contact();
        Contact c2 = new Contact();
        Contact c3 = new Contact();
        Contact c4 = new Contact();
        Contact c5 = new Contact();
//        Contact c6 = new Contact();
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
//        c6.setName("avi");

        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);
//        list.add(c6);
        return list;
    }

    public static void main(String[] args) throws InterruptedException {

       SetApp mSetApp = SetApp.getInstance();

        ArrayList<Contact> testList = generateTestList();
        ArrayList<Contact> validationList = new ArrayList<>();
        boolean exit = false;
        System.out.println(myPhoneBook.textsMap.get("welcome"));
        while (!exit) {
            mSetApp.fun.printMenu();
            //String menuChoice = mSetApp.scan.nextLine();
            int menuInput = mSetApp.fun.menuChoice(/*menuChoice*/); //TODO validate menu numbers only
            switch (menuInput) {
                case 1:
                    Contact contact = mSetApp.myPhoneBook.createContact();
                    int listSizeBeforeAdd = mSetApp.contactsList.size();
                    mSetApp.contactsList = mSetApp.myPhoneBook.addContact(contact, mSetApp.contactsList);
                    int listSizeAfterAdd = mSetApp.contactsList.size();
                    if (mSetApp.fun.isListsSizesEquals(listSizeBeforeAdd, listSizeAfterAdd) || contact == null) {
                        exit = true;
                    }
                    break;
                case 2:  // TODO organize and maybe export to some methods, test this it's not right
                    int listSize = /*contactsList.size();*/ testList.size();
                    for (int i = 0; i < 3; i++) {
                        System.out.println(mSetApp.myPhoneBook.textsMap.get("removeContact"));
                        String nameOrPhone = mSetApp.scan.nextLine();
                        validationList = mSetApp.myPhoneBook.findContact(testList, nameOrPhone); //TODO error messages order
                        if (!validationList.isEmpty()) {
                            int yORn = mSetApp.validation.enterAndValidateYorN("removeAll");
                            if (yORn == 1) {
                                //   contactsList = myPhoneBook.removeContact(contactsList, nameOrPhone, true);
                                testList = mSetApp.myPhoneBook.removeContact(testList, nameOrPhone, true);
                                if (mSetApp.fun.isListsSizesEquals(listSize, testList.size())) {
                                    mSetApp.fun.printErrorMessages(8);
                                } else {
                                    System.out.println(mSetApp.myPhoneBook.textsMap.get("actionSuccess"));
                                    break;
                                }
                            } else if (yORn == 0) {
                                //   contactsList = myPhoneBook.removeContact(contactsList,nameOrPhone, false);
                                testList = mSetApp.myPhoneBook.removeContact(testList, nameOrPhone, false);
                                if (mSetApp.fun.isListsSizesEquals(listSize, testList.size())){
                                    mSetApp.fun.printErrorMessages(8);
                                } else {
                                    System.out.println(mSetApp.myPhoneBook.textsMap.get("actionSuccess"));
                                    break;
                                }
                            } else if (yORn == 2 && i == 2) {
                                mSetApp.fun.printErrorMessages(8);
                            } else {
                                i = 3;
                            }
                        } else {
                            mSetApp.fun.printErrorMessages(8);
                        }
                    }
                    validationList.removeAll(validationList); // TODO test this
                    break;
                case 3:
                    mSetApp.myPhoneBook.printPhoneBook(mSetApp.contactsList /*testList*/);
                    break;
                case 4:
                    for (int i = 0; i < 3; i++) {
                        System.out.println(mSetApp.myPhoneBook.textsMap.get("enterContactName"));
                        String name = mSetApp.scan.nextLine();
                        if (mSetApp.validation.isOnlyEnglishLetters(name)) {
                            ArrayList<Contact> contactsFound = mSetApp.myPhoneBook.findContact(testList, name);
                          //  ArrayList<Contact> contactsFound = myPhoneBook.findContact(contactsList, name);
                            if (!contactsFound.isEmpty()) {
                                mSetApp.myPhoneBook.printPhoneBook(contactsFound);
                                break;
                            } else {
                                mSetApp.fun.printErrorMessages(8);
                            }
                        } else {
                            mSetApp.fun.printErrorMessages(mSetApp.fun.calculateMessageIndex(i, true, true));
                        }
                    }
                    break;
                case 5:
                    testList /*contactsList*/ = mSetApp.myPhoneBook.sortByNameAlphabetically(testList /*contactsList*/);
                    mSetApp.myPhoneBook.printPhoneBook(testList /*contactsList*/);
                    break;
                case 6:
                    testList /*contactsList*/ = mSetApp.myPhoneBook.sortByPhoneBigToSmall(testList /*contactsList*/);
                    mSetApp.myPhoneBook.printPhoneBook(testList /*contactsList*/);
                    break;
                case 7:
                    System.out.println("BEFORE SORTING: ");
                    mSetApp.myPhoneBook.printPhoneBook(testList /*contactsList*/);
                    testList /*contactsList*/ = mSetApp.myPhoneBook.sortByNameReverse(testList /*contactsList*/);
                    System.out.println("AFTER SORTING: ");
                    mSetApp.myPhoneBook.printPhoneBook(testList /*contactsList*/);
                    break;
                case 8:
                    ArrayList<Contact> duplicatesList = myPhoneBook.findDuplicates(testList);
                    testList = mSetApp.myPhoneBook.removeDuplicates(testList);
                    if (!duplicatesList.isEmpty()) {
                        System.out.println("DUPLICATES REMOVED: ");
                        mSetApp.myPhoneBook.printPhoneBook(duplicatesList);
                    } else {
                        System.out.println("No Duplicates Found");
                    }
                    break;
                case 9:
                    mSetApp.myPhoneBook.exportPhoneBook(testList);
                    break;
                case 10:
                    mSetApp.contactsList = mSetApp.myPhoneBook.importPhoneBook();
                    break;
                case 11:
                    exit = true;
                    break;
            }
            if (exit) {
                System.out.println(mSetApp.myPhoneBook.textsMap.get("exit"));
            } else {
                Thread.sleep(2000);
                System.out.println(mSetApp.myPhoneBook.textsMap.get("moreAction"));
            }
        }
    }
}
