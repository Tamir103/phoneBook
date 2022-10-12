package RunClasses;

import utils.contact;

public class Main {
    public static void main(String[] args) {

        contact p1 = new contact();

        p1.setName("   de re qqq       ");

        p1.setPhoneNumber("0543-765 12");

        System.out.println(p1.getName());
        System.out.println(p1.getPhoneNumber());
    }
}
