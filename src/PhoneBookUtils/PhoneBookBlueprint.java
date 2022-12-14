package PhoneBookUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public abstract class PhoneBookBlueprint extends Contact implements Comparable {

    public abstract Map<Integer, String> generatePhoneBookMenu();

    public abstract ArrayList<Contact> generateContactsList();

    public abstract Contact createContact();

    public abstract ArrayList<Contact> addContact(Contact contact, ArrayList<Contact> listOfContacts);

    public abstract ArrayList<Contact> removeContact(ArrayList<Contact> listOfContacts, String nameOrPhone, boolean removeAll);

    public abstract void printPhoneBook(ArrayList<Contact> listOfContacts);

    public abstract ArrayList<Contact> findContact(ArrayList<Contact> listOfContacts, String contactNameOrPhone);

    private final Comparator<Contact> compareByName = (Contact o1, Contact o2) -> o1.getName().compareToIgnoreCase(o2.getName());
    private final Comparator<Contact> compareByPhone = Comparator.comparing(Contact::getPhoneNumber);

    public ArrayList<Contact> sortByNameAlphabetically(ArrayList<Contact> listOfContacts){
        Collections.sort(listOfContacts, compareByName);
        return listOfContacts;
    }
    public ArrayList<Contact> sortByNameReverse(ArrayList<Contact> listOfContacts) {
        Collections.sort(listOfContacts, compareByName.reversed());
        return listOfContacts;
    }

    /**
     * Sorts list by phone number, character by character using comparator
     * @param listOfContacts List to be sorted
     * @return List sorted
     */
    // Does not sort by the mathematically biggest number, but by comparing each number character of one to the same position number character of the other
    public ArrayList<Contact> sortByPhoneBigToSmall(ArrayList<Contact> listOfContacts) {
//        Collections.sort(listOfContacts, (o1, o2) -> {
//            int phone1, phone2;
        /*    BigInteger phone1, phone2; */
//            try {
//                phone1 = Integer.parseInt(o1.getPhoneNumber());
      /*  phone1 = new BigInteger(o1.getPhoneNumber()); */
//            } catch (NumberFormatException nfe) {
//                phone1 = 0;
//            }
//            try {
//                phone2 = Integer.parseInt(o2.getPhoneNumber());
           /* phone2 = new BigInteger(o2.getPhoneNumber()); */
//            } catch (NumberFormatException nfe) {
//                phone2 = 0;
//            }
//
//            if(phone1 == phone2)
//                return 0;
//            return phone1 < phone2 ? -1 : 1;
          /*  return phone1.compareTo(phone2) > 0 ? 1 :-1; */
//        });
        Collections.sort(listOfContacts, compareByPhone.reversed());
        return listOfContacts;
    }

    /**
     * Removing duplicates (identical contacts)
     * @param listOfContacts List for duplicates removal
     * @return List with duplicates removed
     */
    public ArrayList<Contact> removeDuplicates(ArrayList<Contact> listOfContacts) {
        ArrayList<Contact> duplicatesList = findDuplicates(listOfContacts);
        if (!duplicatesList.isEmpty()) {
            for (Contact contact : duplicatesList) {
                listOfContacts.remove(contact);
            }
        }
        return listOfContacts;
    }
    public static ArrayList<Contact> findDuplicates(ArrayList<Contact> listOfContacts) {
        ArrayList<Contact> duplicatesList = new ArrayList<>();
        for (int i = 0; i < listOfContacts.size(); i++) {
            Contact c1 = listOfContacts.get(i);
            for (int j = i + 1; j < listOfContacts.size(); j++) {
                Contact c2 = listOfContacts.get(j);
                if (c1.getName().equalsIgnoreCase(c2.getName())) {
                    if (c1.getPhoneNumber().equals(c2.getPhoneNumber())) {
                        duplicatesList.add(c2);
                        i++;
                    }
                }
            }
        }
        return duplicatesList;
    }
    public abstract void exportPhoneBook(ArrayList<Contact> listOfContacts);
    public abstract ArrayList<Contact> importPhoneBook();

}
