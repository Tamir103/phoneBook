package PhoneBookUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public abstract class PhoneBookBlueprint extends Contact implements Comparable {

//    ArrayList<Contact> contactsList = new ArrayList<>();

    public abstract Map<Integer, String> generatePhoneBookMenu();

    public abstract ArrayList<Contact> generateContactsList();

    public abstract Contact createContact();

    public abstract ArrayList<Contact> addContact(Contact contact, ArrayList<Contact> listOfContacts);

    public abstract ArrayList<Contact> removeContact(ArrayList<Contact> listOfContacts, String nameOrPhone, boolean removeAll);

    public abstract void printPhoneBook(ArrayList<Contact> listOfContacts);

    public abstract Contact findContact(String contactName);

    private Comparator<Contact> compareByName = (Contact o1, Contact o2) -> o1.getName().compareTo(o2.getName());
    private Comparator<Contact> compareByPhone = (Contact o1, Contact o2) -> o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
    // TODO need to be tested
    public ArrayList<Contact> sortByNameAlphabetically(ArrayList<Contact> listOfContacts){
//        Collections.sort(listOfContacts, ((o1, o2) -> o1.getName().compareTo(o2.getName())));
//                listOfContacts, new Comparator<Contact>() {
//            @Override
//            public int compare(Contact o1, Contact o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
        Collections.sort(listOfContacts, compareByName);
        return listOfContacts;
    }
    public ArrayList<Contact> sortByNameReverse(ArrayList<Contact> listOfContacts) {
        Collections.sort(listOfContacts, compareByName.reversed());
        return listOfContacts;
    }
    // TODO need to be tested
    public ArrayList<Contact> sortByPhoneBigToSmall(ArrayList<Contact> listOfContacts) {
//        Collections.sort(listOfContacts, Comparator.comparing(Contact::getPhoneNumber));
        Collections.sort(listOfContacts, compareByPhone);
        return listOfContacts;
    }
    public ArrayList<Contact> removeDuplicates(ArrayList<Contact> listOfContacts) {
        // TODO implement this method
        return listOfContacts;
    }
    public abstract void exportPhoneBook(ArrayList<Contact> listOfContacts);
    public abstract ArrayList<Contact> importPhoneBook();

}
