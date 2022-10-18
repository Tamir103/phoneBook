package PhoneBookApp;

import PhoneBookUtils.Contact;
import PhoneBookUtils.PhoneBookBlueprint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static PhoneBookApp.setApp.texts;
import static PhoneBookApp.PhoneBookAppMethods.*;

public class myPhoneBook extends PhoneBookBlueprint {

    @Override
    public Map<Integer, String> generatePhoneBookMenu() {
        Map<Integer, String> menu = new HashMap<>();
        menu.put(1, "Add contact");
        menu.put(2, "Delete contact");
        menu.put(3, "Print all contacts");
        menu.put(4, "Search and print contact");
        menu.put(5, "Sort by name alphabetically: A-Z and print");
        menu.put(6, "Sort by phone - largest to smallest number");
        menu.put(7, "Sort by name alphabetically: Z-A and print before and after");
        menu.put(8, "Remove duplicates and print them");
        menu.put(9, "Save phone book to a file");
        menu.put(10, "Upload phone book from a file");
        menu.put(11, "Exit");

        return menu;
    }

    @Override
    public ArrayList<Contact> generateContactsList() {
        return null;
    }

    /**
     * Create contacts object with name and phone number
     * In this phone book number of name characters is limited to 20
     *
     * @return Contact object with valid fields filled
     */
    @Override
    public Contact createContact() {
        boolean success = false;
        Contact contact = new Contact();
        System.out.println(texts.enterName);
        for (int i = 0; i < 3; i++) {
            String name = enterString();
            try {
                contact.setName(name, 20);
                success = true;
                break;
            } catch (IllegalArgumentException iae) {
                printErrorMessages(calculateMessageIndex(i, true, true));
            } catch (ArithmeticException ae) {
                printErrorMessages(calculateMessageIndex(i, false, true));
            }
        }

        if (success) {
            System.out.println(texts.enterPhone);
            for (int i = 0; i < 3; i++) {
                String phone = enterString();
                try {
                    contact.setPhoneNumber(phone);
                    return contact;
                } catch (NumberFormatException nfe) {
                    printErrorMessages(calculateMessageIndex(i, false, false));
                } catch (IllegalArgumentException iae) {
                    printErrorMessages(calculateMessageIndex(i, true, false));
                }
            }
        }
        printErrorMessages(7);
        return null;
    }

    @Override
    public ArrayList<Contact> addContact(Contact contact, ArrayList<Contact> listOfContacts) {
        listOfContacts.add(listOfContacts.size(), contact);
        return listOfContacts;

    }

    @Override
    public ArrayList<Contact> removeContact(Contact contact) {

        return null;
    }

    @Override
    public void printPhoneBook(ArrayList<Contact> listOfContacts) {
        for (Contact contact : listOfContacts) {
            System.out.println("Contact Name: " + contact.getName());
            System.out.println("Contact Phone Number: " + contact.getPhoneNumber());
            System.out.println("---------------------------");
        }

    }

    @Override
    public Contact findContact(String contactName) {
        return null;
    }

    @Override
    public void exportPhoneBook(ArrayList<Contact> listOfContacts) {

    }

    @Override
    public ArrayList<Contact> importPhoneBook() {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    /**
     * Printing error text according to message index
     *
     * @param messageIndex Index of message to print
     *                     1 - Generic invalid input
     *                     2 - Name length restriction
     *                     3 - Phone format and length restriction
     *                     4 - Last input chance
     *                     5 - Name length + last chance
     *                     6 - Phone format + last chance
     *                     7 - Max tries
     */
    public static void printErrorMessages(int messageIndex) {  //TODO maybe move phoneBookAppMethods
        switch (messageIndex) {
            case 1 -> System.err.println(texts.invalidInputWarn);
            case 2 -> System.err.println(texts.nameLengthWarn);
            case 3 -> System.err.println(texts.phoneFormat);
            case 4 -> System.err.println(texts.lastInvalidInputWarn);
            case 5 -> System.err.println(texts.nameLengthWarn + " - " + texts.lastInvalidInputWarn);
            case 6 -> System.err.println(texts.phoneFormat + " - " + texts.lastInvalidInputWarn);
            case 7 -> System.err.println(texts.inputErrMsg);
        }
    }

    public static int calculateMessageIndex(int i, boolean isLengthValid, boolean isNameField) {
        if (i == 0) {
            if (isNameField) {
                if (isLengthValid) {
                    //  System.err.println(texts.invalidInputWarn);
                    return 1;
                } else {
                    //  System.err.println(texts.nameLengthWarn);
                    return 2;
                }
            } else {
                if (isLengthValid) {
                    //  System.err.println(texts.invalidInputWarn);
                    return 1;
                } else {
                    //  System.err.println(texts.phoneFormat);
                    return 3;
                }
            }
        } else if (i == 1) {
            if (isNameField) {
                if (isLengthValid) {
                    //  System.err.println(texts.lastInvalidInputWarn);
                    return 4;
                } else {
                    //  System.err.println(texts.nameLengthWarn + " - " + texts.lastInvalidInputWarn);
                    return 5;
                }
            } else {
                if (isLengthValid) {
                    //  System.err.println(texts.lastInvalidInputWarn);
                    return 4;
                } else {
                    //  System.err.println(texts.phoneFormat + " - " + texts.lastInvalidInputWarn);
                    return 6;
                }
            }
        } else {
            return 1;
        }
    }
}
