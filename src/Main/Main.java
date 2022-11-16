package Main;

import PhoneBookApp.myPhoneBook;
import PhoneBookApp.SetApp;
import PhoneBookUtils.Contact;

import java.util.ArrayList;

public class Main {
    static SetApp mSetApp;
    static ArrayList<Contact> validationList;
    public static void main(String[] args) throws InterruptedException {

        mSetApp = SetApp.getInstance();

        validationList = new ArrayList<>();
        boolean exit = false;
        mSetApp.myPhoneBook.printTextsFromMap("welcome");
        while (!exit) {
            mSetApp.fun.printMenu();
            int menuInput = mSetApp.fun.menuChoice();
            menuInput = isActionPossibleOnEmptyList(menuInput);
            switch (menuInput) {
                case 1 -> addContactManager();
                case 2 -> removeContactManager();
                case 3 -> mSetApp.myPhoneBook.printPhoneBook(mSetApp.contactsList);
                case 4 -> findContactManager();
                case 5 -> {
                    mSetApp.contactsList = mSetApp.myPhoneBook.sortByNameAlphabetically(mSetApp.contactsList);
                    mSetApp.myPhoneBook.printPhoneBook(mSetApp.contactsList);
                }
                case 6 -> {
                    mSetApp.contactsList = mSetApp.myPhoneBook.sortByPhoneBigToSmall(mSetApp.contactsList);
                    mSetApp.myPhoneBook.printPhoneBook(mSetApp.contactsList);
                }
                case 7 -> reverseSortingManager();
                case 8 -> removeDuplicatesManager();
                case 9 -> mSetApp.myPhoneBook.exportPhoneBook(mSetApp.contactsList);
                case 10 -> {
                    mSetApp.contactsList = mSetApp.myPhoneBook.importPhoneBook();
                    if (mSetApp.contactsList.size() > 0) {
                        mSetApp.myPhoneBook.printTextsFromMap("actionSuccess");
                    }
                }
                case 11 -> exit = true;
            }
            if (exit) {
                mSetApp.myPhoneBook.printTextsFromMap("exit");
            } else {
                Thread.sleep(2000);
                System.out.println();
                mSetApp.myPhoneBook.printTextsFromMap("moreAction");
            }
        }
    }

    static int isActionPossibleOnEmptyList(int menuInput) {
        if (mSetApp.contactsList.isEmpty() && !(menuInput == 1 || menuInput == 10 || menuInput == 11)) {
            mSetApp.fun.printErrorMessages(10);
            System.err.println("Only adding (1), uploading (10) or exit (11) are possible");
            System.out.println();
            return  0;
        } else {
            return menuInput;
        }
    }
    // 1
    static void addContactManager() {
        Contact contact = mSetApp.myPhoneBook.createContact();
        int listSizeBeforeAdd = mSetApp.contactsList.size();
        mSetApp.contactsList = mSetApp.myPhoneBook.addContact(contact, mSetApp.contactsList);
        if (mSetApp.contactsList.size() == (listSizeBeforeAdd + 1)) {
            mSetApp.myPhoneBook.printTextsFromMap("actionSuccess");
        }
    }
    // 2
    static void removeContactManager() {
            int listSize = mSetApp.contactsList.size();
            for (int i = 0; i < 3; i++) {
                mSetApp.myPhoneBook.printTextsFromMap("removeContact");
                String nameOrPhone = mSetApp.scan.nextLine();
                validationList = mSetApp.myPhoneBook.findContact(mSetApp.contactsList, nameOrPhone);
                if (!validationList.isEmpty()) {
                    int yORn = mSetApp.validation.enterAndValidateYorN("removeAll");
                    if (yORn == 1) {
                        mSetApp.contactsList = mSetApp.myPhoneBook.removeContact(mSetApp.contactsList, nameOrPhone, true);
                        if (mSetApp.fun.isListsSizesEquals(listSize, mSetApp.contactsList.size()) && i != 2) {
                            mSetApp.fun.printErrorMessages(8);
                        } else {
                            mSetApp.myPhoneBook.printTextsFromMap("actionSuccess");
                            break;
                        }
                    } else if (yORn == 0) {
                        mSetApp.contactsList = mSetApp.myPhoneBook.removeContact(mSetApp.contactsList, nameOrPhone, false);
                        if (mSetApp.fun.isListsSizesEquals(listSize, mSetApp.contactsList.size())) {
                            mSetApp.fun.printErrorMessages(8);
                        } else {
                            mSetApp.myPhoneBook.printTextsFromMap("actionSuccess");
                            break;
                        }
                    } else if (yORn == 2 && i == 2) {
                        mSetApp.fun.printErrorMessages(8);
                    } else {
                        i = 3;
                    }
                } else if (i == 2) {
                    mSetApp.fun.printErrorMessages(7);
                } else {
                    mSetApp.fun.printErrorMessages(8);
                }
            }
            validationList.removeAll(validationList);
    }

    // 4
    static void findContactManager() {
        for (int i = 0; i < 3; i++) {
            mSetApp.myPhoneBook.printTextsFromMap("enterContactName");
            String name = mSetApp.scan.nextLine();
            if (mSetApp.validation.isOnlyEnglishLetters(name)) {
                ArrayList<Contact> contactsFound = mSetApp.myPhoneBook.findContact(mSetApp.contactsList, name);
                if (!contactsFound.isEmpty()) {
                    mSetApp.myPhoneBook.printPhoneBook(contactsFound);
                    break;
                } else {
                    mSetApp.fun.printErrorMessages(8);
                    mSetApp.fun.printErrorMessages(mSetApp.fun.calculateMessageIndex(i, true, true, true));
                }
            } else {
                mSetApp.fun.printErrorMessages(mSetApp.fun.calculateMessageIndex(i, true, true, true));
            }
        }
    }

    // 7
    static void reverseSortingManager() {
        System.out.println("BEFORE SORTING: ");
        mSetApp.myPhoneBook.printPhoneBook(mSetApp.contactsList);
        mSetApp.contactsList = mSetApp.myPhoneBook.sortByNameReverse(mSetApp.contactsList);
        System.out.println("AFTER SORTING: ");
        mSetApp.myPhoneBook.printPhoneBook(mSetApp.contactsList);
    }

    // 8
    static void removeDuplicatesManager() {
        ArrayList<Contact> duplicatesList = myPhoneBook.findDuplicates(mSetApp.contactsList);
        mSetApp.contactsList = mSetApp.myPhoneBook.removeDuplicates(mSetApp.contactsList);
        if (!duplicatesList.isEmpty()) {
            System.out.println("DUPLICATES REMOVED: ");
            mSetApp.myPhoneBook.printPhoneBook(duplicatesList);
        } else {
            System.out.println("No Duplicates Found");
        }
    }
}

