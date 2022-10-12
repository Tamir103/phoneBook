package RunClasses;

import utils.contact;
import utils.setApp;

public class Main extends setApp {
    public static void main(String[] args){

        contact p1 = new contact();

        p1.setName("dfks");

        p1.setPhoneNumber("0543-765 y12");

        System.out.println(p1.getName());
        System.out.println(p1.getPhoneNumber());


    }
}
