package PhoneBookApp;

import PhoneBookUtils.Contact;
import PhoneBookUtils.PhoneBookBlueprint;
import com.google.gson.Gson;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class myPhoneBook extends PhoneBookBlueprint {

    static SetApp mSetApp = SetApp.getInstance();
    public static HashMap<String, String> textsMap = new HashMap<>();

    myPhoneBook() {
        this.textsMap = generateTexts();
    }

    //TODO javadoc this
    public static HashMap<String, String> generateTexts(/*String jsonPath*/) {
        try {
            // create Gson instance
            Gson gson = new Gson();
            HashMap<String, String> map;

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
        menu.put(5, "Sort by name alphabetically");
        menu.put(6, "Sort by phone");
        menu.put(7, "Sort by name alphabetically - reversed");
        menu.put(8, "Remove duplicates");
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
        System.out.println(textsMap.get("enterContactName"));
        for (int i = 0; i < 3; i++) {
            String name = mSetApp.scan.nextLine();
            try {
                contact.setName(name);
                success = true;
                break;
            } catch (IllegalArgumentException iae) {
                mSetApp.fun.printErrorMessages(mSetApp.fun.calculateMessageIndex(i, true, true));
            } catch (ArithmeticException ae) {
                mSetApp.fun.printErrorMessages(mSetApp.fun.calculateMessageIndex(i, false, true));
            }
        }

        if (success) {
            System.out.println(appTexts.textsMap.get("enterPhone"));
            for (int i = 0; i < 3; i++) {
                String phone = mSetApp.scan.nextLine();
                try {
                    contact.setPhoneNumber(phone);
                    return contact;
                } catch (NumberFormatException nfe) {
                    mSetApp.fun.printErrorMessages(mSetApp.fun.calculateMessageIndex(i, false, false));
                } catch (IllegalArgumentException iae) {
                    mSetApp.fun.printErrorMessages(mSetApp.fun.calculateMessageIndex(i, true, false));
                }
            }
        }
        mSetApp.fun.printErrorMessages(7);
        return null;
    }

    @Override
    public ArrayList<Contact> addContact(Contact contact, ArrayList<Contact> listOfContacts) {
        listOfContacts.add(listOfContacts.size(), contact);
        return listOfContacts;

    }

    static boolean compareContactDetails(Contact c, String nameOrPhone) {
        return c.getName().equalsIgnoreCase(nameOrPhone) || c.getPhoneNumber().equals(nameOrPhone);
    }

    @Override
    public ArrayList<Contact> removeContact(ArrayList<Contact> listOfContacts, String nameOrPhone, boolean removeAll) {
        ArrayList<Contact> inMethodList = findContact(listOfContacts, nameOrPhone);
        if (!inMethodList.isEmpty()) {
            if (removeAll) {
                ArrayList<Contact> foundList = new ArrayList<>();
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
        System.out.print(Objects.requireNonNullElse(name, "Null"));
        mSetApp.fun.printMenuDots(dotAmount);
        System.out.print(phone);

    }

    @Override
    public void printPhoneBook(ArrayList<Contact> listOfContacts) {
        mSetApp.fun.printFrame(37);
        System.out.println("CONTACTS: ");
        for (Contact contact : listOfContacts) {
            System.out.print("| ");
            printContact(contact);
            int totalLineLength;
            try {
                totalLineLength = 25 + contact.getPhoneNumber().length();
            } catch (NullPointerException npe) {
                totalLineLength = 35;
                System.out.print("      ");
            }
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
    public ArrayList<Contact> sortByNameAlphabetically(ArrayList<Contact> listOfContacts) {
        return super.sortByNameAlphabetically(listOfContacts);
    }

    @Override
    public void exportPhoneBook(ArrayList<Contact> listOfContacts) {

        System.out.println(textsMap.get("enterFileName"));
        String fileName = validateFileName(mSetApp.scan.nextLine());
        try {
            // Creating a File object that
            // represents the disk file
            PrintStream o = new PrintStream(new FileOutputStream(fileName, true));

            // Store current System.out
            // before assigning a new value
            PrintStream console = System.out;

            // Assign o to output stream
            // using setOut() method
            System.setOut(o);

            // printing
            printPhoneBook(listOfContacts);

            // Use stored value for output stream
            System.setOut(console);

        } catch (FileNotFoundException fnfe) {
            System.err.println("Error in writing to file");
        }
    }

    public String validateFileName(String fileName) {
        String fileFullName = fileName + ".txt";
        File file = new File(fileFullName);
        if (!file.isFile()) {
            if (fileName.length() <= 15) {
                return fileFullName;
            } else {
                System.out.println(textsMap.get("nameTooLong"));
                System.out.println(textsMap.get("createRandomFileName"));
                return createRandomPhoneBookName();
            }
        } else {
            int yORn = validationMethods.enterAndValidateYorN("fileExist");
            if (yORn == 1) {
                return fileFullName;
            } else if (yORn == 0) {
                System.out.println(textsMap.get("createRandomFileName"));
                return createRandomPhoneBookName();
            }
        }
        return "0";
    }

    public String createRandomPhoneBookName() {
        DateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        Date today = Calendar.getInstance().getTime();
        String printDate = dFormat.format(today);
        return "PhoneBook " + printDate + ".txt";
    }

    /**
     * Reading a phone book from txt file, file name (if in project folder) or path entered by the user
     * Only name and phone number in the same line format is supported
     * @return ArrayList of valid contacts read from file
     */
    @Override
    public ArrayList<Contact> importPhoneBook() { //TODO still a bug in here (in file content format), fix if there is time
        ArrayList<Contact> importedContactsList = new ArrayList<>();
        String rawLine;
        String name = "";
        String phone = "";
        System.out.println(textsMap.get("enterFileName"));
        String fileName = mSetApp.scan.nextLine();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((rawLine = reader.readLine()) != null) {
                Contact contact = new Contact();
                for (int i = 0; i < rawLine.length(); i++) {
                    int charValue = rawLine.charAt(i);
                    String currentChar = Character.toString((char) charValue);
                    if (((charValue >= 65 && charValue <= 90) || (charValue >= 97 && charValue <= 122) || charValue == 32)) {
                        name = name.concat(currentChar);
                    } else if ((charValue >= 48 && charValue <= 57)) {
                        phone = phone.concat(currentChar);
                    }
                }
                try {
                    if (!(name.equals("CONTACTS") || name.equals("") || phone.equals(""))) {
                        name = mSetApp.validation.validateLettersOnly(name);
                        contact.setName(name);
                        contact.setPhoneNumber(phone);
                        importedContactsList = addContact(contact, importedContactsList);
                    }
                } catch (NumberFormatException nfe) {
                    System.err.println("Phone book file is not in supported format");
                }
                name = "";
                phone = "";
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("File Not Found"); //TODO maybe put text in errors map
        } catch (IOException ioe) {
            System.err.println("Error In Reading Phone Book");
        }
        return importedContactsList;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
