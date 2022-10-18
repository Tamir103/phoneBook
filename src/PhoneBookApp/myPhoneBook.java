package PhoneBookApp;

import PhoneBookUtils.Contact;
import PhoneBookUtils.PhoneBookBlueprint;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class myPhoneBook extends PhoneBookBlueprint {

    public static HashMap<String, String> textsMap = generateTexts();
    //TODO javadoc this
    public static HashMap<String, String> generateTexts(/*String jsonPath*/) {
        try {
            // create Gson instance
            Gson gson = new Gson();
            HashMap map;

            // create a reader
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./Texts.json"));

            // convert JSON file to map
            map = gson.fromJson(bufferedReader, HashMap.class);

            bufferedReader.close();
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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
        System.out.println(textsMap.get("enterName"));
        for (int i = 0; i < 3; i++) {
            String name = PhoneBookAppMethods.enterString();
            try {
                contact.setName(name);
                success = true;
                break;
            } catch (IllegalArgumentException iae) {
                printErrorMessages(calculateMessageIndex(i, true, true));
            } catch (ArithmeticException ae) {
                printErrorMessages(calculateMessageIndex(i, false, true));
            }
        }

        if (success) {
            System.out.println(appTexts.textsMap.get("enterPhone"));
            for (int i = 0; i < 3; i++) {
                String phone = PhoneBookAppMethods.enterString();
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
    public ArrayList<Contact> removeContact(ArrayList<Contact> listOfContacts, String nameOrPhone, boolean removeAll) {
        for (Contact c : listOfContacts) {
            if (removeAll) {
                if (c.getName().equalsIgnoreCase(nameOrPhone) || c.getPhoneNumber().equals(nameOrPhone)) {
                    listOfContacts.remove(c);
                }
            } else {
                if (c.getName().equalsIgnoreCase(nameOrPhone) || c.getPhoneNumber().equalsIgnoreCase(nameOrPhone)){
                    listOfContacts.remove(c);
                    break;
                }
            }
        }
        return listOfContacts;
    }

    @Override
    public void printPhoneBook(ArrayList<Contact> listOfContacts) {
        PhoneBookAppMethods.printFrame(37);
        System.out.println("CONTACTS: ");
        for (Contact contact : listOfContacts) {
            System.out.print("| ");
            System.out.print(contact.getName());
            int dotAmount = 25 - contact.getName().length();
            PhoneBookAppMethods.printMenuDots(dotAmount);
            System.out.print(contact.getPhoneNumber());
            int totalLineLength = 25 + contact.getPhoneNumber().length();
            if (totalLineLength == 35) {
                System.out.print("|");
            } else {
                System.out.print(" |");
            }
            System.out.println();
        }
        PhoneBookAppMethods.printFrame(37);
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
            case 0 -> System.out.println();
            case 1 -> System.err.println(textsMap.get("invalidInputWarn"));
            case 2 -> System.err.println(textsMap.get("nameLengthWarn"));
            case 3 -> System.err.println(textsMap.get("phoneFormat"));
            case 4 -> System.err.println(textsMap.get("lastInvalidInputWarn"));
            case 5 -> System.err.println(textsMap.get("nameLengthWarn") + " - " + textsMap.get("lastInvalidInputWarn"));
            case 6 -> System.err.println(textsMap.get("phoneFormat") + " - " + textsMap.get("lastInvalidInputWarn"));
            case 7 -> System.err.println(textsMap.get("inputErrMsg"));
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
        } else if (i == 2) {
            return 0;
        } else {
            return 1;
        }
    }
}
