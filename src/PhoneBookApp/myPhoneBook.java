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
            String name = setApp.scan.nextLine();
            try {
                contact.setName(name);
                success = true;
                break;
            } catch (IllegalArgumentException iae) {
                PhoneBookAppMethods.printErrorMessages(PhoneBookAppMethods.calculateMessageIndex(i, true, true));
            } catch (ArithmeticException ae) {
                PhoneBookAppMethods.printErrorMessages(PhoneBookAppMethods.calculateMessageIndex(i, false, true));
            }
        }

        if (success) {
            System.out.println(appTexts.textsMap.get("enterPhone"));
            for (int i = 0; i < 3; i++) {
                String phone = setApp.scan.nextLine();
                try {
                    contact.setPhoneNumber(phone);
                    return contact;
                } catch (NumberFormatException nfe) {
                    PhoneBookAppMethods.printErrorMessages(PhoneBookAppMethods.calculateMessageIndex(i, false, false));
                } catch (IllegalArgumentException iae) {
                    PhoneBookAppMethods.printErrorMessages(PhoneBookAppMethods.calculateMessageIndex(i, true, false));
                }
            }
        }
        PhoneBookAppMethods.printErrorMessages(7);
        return null;
    }

    @Override
    public ArrayList<Contact> addContact(Contact contact, ArrayList<Contact> listOfContacts) {
        listOfContacts.add(listOfContacts.size(), contact);
        return listOfContacts;

    }

    static boolean compareContactDetails(Contact c, String nameOrPhone) {
        if (c.getName().equalsIgnoreCase(nameOrPhone) || c.getPhoneNumber().equals(nameOrPhone)) {
            return true;
        }
        return false;
    }
    // TODO use search method
    @Override
    public ArrayList<Contact> removeContact(ArrayList<Contact> listOfContacts, String nameOrPhone, boolean removeAll) {
        ArrayList<Contact> inMethodList = findContact(listOfContacts, nameOrPhone);
        if (!PhoneBookAppMethods.isListEmpty(inMethodList)) {
            if (removeAll) {
                ArrayList<Contact> foundList  = new ArrayList<>();
                Contact[] contactsArr = new Contact[listOfContacts.size()];
                for (int i = 0; i < listOfContacts.size(); i++) {
                    contactsArr[i] = listOfContacts.get(i);
                }

     /*   for (Contact c : listOfContacts) {
            if (removeAll) {
                if (c.getName().equalsIgnoreCase(nameOrPhone) || c.getPhoneNumber().equals(nameOrPhone)) {
                    listOfContacts.remove(c);
                }
            } else {
                if (c.getName().equalsIgnoreCase(nameOrPhone) || c.getPhoneNumber().equalsIgnoreCase(nameOrPhone)) {
                    listOfContacts.remove(c);
                    break;
                }
            }
        } */

                for (int i = 0; i < contactsArr.length; i++) {
                    if (compareContactDetails(contactsArr[i], nameOrPhone)) {
                        contactsArr[i] = null;
                    }
                }
                for (Contact contact : contactsArr) {
                    if (contact != null) {
                        foundList.add(contact);
                    }
                }
                return foundList;
            } else {
                for (Contact c : listOfContacts) {
                    if (compareContactDetails(c, nameOrPhone)) {
                        listOfContacts.remove(c);
                        break;
                    }
                }
                return listOfContacts;
            }
        } else {
            return listOfContacts;
        }
    }
    public void printContact(Contact c) {
        String name = c.getName();
        String phone = c.getPhoneNumber();
        int dotAmount = 25 - name.length();
        System.out.print(name);
        PhoneBookAppMethods.printMenuDots(dotAmount);
        System.out.print(phone);
    }

    @Override
    public void printPhoneBook(ArrayList<Contact> listOfContacts) {
        PhoneBookAppMethods.printFrame(37);
        System.out.println("CONTACTS: ");
        for (Contact contact : listOfContacts) {
            System.out.print("| ");
            printContact(contact);
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

    // TODO if there is time, use contains to find partial names
    @Override
    public ArrayList<Contact> findContact(ArrayList<Contact> listOfContacts, String contactNameOrPhone) {
        ArrayList<Contact> contactsFound = new ArrayList<>();
        for (Contact c : listOfContacts) {
            if (compareContactDetails(c, contactNameOrPhone)) {
                contactsFound.add(c);
            }
        }
        return contactsFound;
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

}
