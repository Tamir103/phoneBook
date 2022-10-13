package utils;

import java.util.ArrayList;
import java.util.Map;

public class phoneBookFunctions extends setApp {

    public void printMenu() {
        System.out.println(texts.welcome);
        for (Map.Entry<Integer, String> entry : menu.entrySet()) {
            if (entry.getKey() < 10) {
                System.out.print(entry.getKey());
                printMenuDots(8);
                System.out.println(entry.getValue());
            } else if (entry.getKey() < 100) {
                System.out.print(entry.getKey());
                printMenuDots(7);
                System.out.println(entry.getValue());
            }
        }
        System.out.println();
        System.out.println(texts.menuInstructions);
    }

    public void printMenuDots(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(".");
        }
    }

    public String enterString() {
        return scan.nextLine();
    }

    /**
     * Getting user choice from menu and validate it
     *
     * @return int input or 0 if input invalid 3 times
     */
    public int enterMenuChoice() {
        for (int i = 0; i < 3; i++) {
            String input = enterString();
            if (validate.isNumbersOnly(input)) {
                int inputInt = Integer.parseInt(input);
                if (validate.isItemNumValid(inputInt, menu)) {
                    return inputInt;
                }
            }
            printInvalidInputWarn(i);
        }
        System.err.println(texts.inputErrMsg);
        return 0;
    }

    public void printInvalidInputWarn(int i) {
        if (i == 0) {
            System.err.println(texts.invalidInputWarn);
        } else if (i == 1) {
            System.err.println(texts.lastInvalidInputWarn);
        }
    }

    public Contact createContact() {
        boolean success = false;
        Contact contact = new Contact();
        for (int i = 0; i < 3; i++) {
            System.out.println(texts.enterName);
            String name = enterString();
            try {
                contact.setName(name);
                success = true;
                break;
            } catch (IllegalArgumentException iae) {
               printInvalidInputWarn(i);
            }
        }
        if (success) {
            System.out.println(texts.enterPhone);
            for (int i = 0; i < 3; i++) {
                String phone = enterString();
                try {
                    contact.setPhoneNumber(phone);
                    return contact;
                } catch (IllegalArgumentException iae) {
                    printInvalidInputWarn(i);
                }
            }
        }
        System.err.println(texts.inputErrMsg);
        return null;
    }

    public /*ArrayList<Contact>*/ void addContact(Contact contact) {
        contactsList.add(contactsList.size(), contact);
//        return contactsList;
    }

}
