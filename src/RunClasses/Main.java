package RunClasses;

import utils.contact;
import utils.setApp;
import utils.phoneBookFunctions;

public class Main extends setApp {
    public static void main(String[] args){

        contact p1 = new contact();

        phoneBookFunctions fun = new phoneBookFunctions();
        fun.printMenu();
        int input = fun.enterInt();
        System.out.println(input);


    }
}
