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
                printInvalidInputWarn(i, true, true);
            } catch (ArithmeticException ae) {
                printInvalidInputWarn(i, false, true);
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
                    printInvalidInputWarn(i, true, false);
                } catch (IllegalArgumentException iae) {
                    printInvalidInputWarn(i, false, false);
                }
            }
        }
        System.err.println(texts.inputErrMsg);
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

    @Override // TODO better GUI
    public void printPhoneBook(ArrayList<Contact> listOfContacts) {
        for (Contact contact: listOfContacts) {
            System.out.println("Contact Name: " + contact.getName());
            System.out.println("Contact Phone Number: " + contact.getPhoneNumber());
            System.out.println("-------------------------");
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
     * Printing error text according to field of Contact object
     * @param i Index of loop
     * @param isLengthValid true if number of characters in field are as determined
     * @param isNameField true if errors in name, false if errors in phone
     */
    public static void printInvalidInputWarn(int i, boolean isLengthValid, boolean isNameField) {
        if (i == 0) {
            if (isNameField) {
                if (isLengthValid) {
                    System.err.println(texts.invalidInputWarn);
                } else {
                    System.err.println(texts.nameLengthWarn);
                }
            } else {
                if (isLengthValid) {
                    System.err.println(texts.invalidInputWarn);
                } else {
                    System.err.println(texts.phoneFormat);
                }
            }
        } else if (i == 1) {
            if (isNameField) {
                if (isLengthValid) {
                    System.err.println(texts.lastInvalidInputWarn);
                } else {
                    System.err.println(texts.nameLengthWarn + " - " + texts.lastInvalidInputWarn);
                }
            } else {
                if (isLengthValid) {
                    System.err.println(texts.lastInvalidInputWarn);
                } else {
                    System.err.println(texts.phoneFormat + " - " + texts.lastInvalidInputWarn);
                }
            }
        }
    }
}
