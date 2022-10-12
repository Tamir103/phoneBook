package utils;

import java.util.Map;

public class phoneBookFunctions extends setApp {

    public void printMenu() {
        System.out.println("WELCOME TO THE PHONE BOOK - ENTER THE ACTION NUMBER YOU WISH TO EXECUTE");
        for (Map.Entry<Integer,String> entry : menu.entrySet()) {
            if (entry.getKey() < 10) {
                System.out.print(entry.getKey());
                printMenuDots(8);
                System.out.println(entry.getValue());
            } else if (entry.getKey() < 100) {
                System.out.print(entry.getKey());
                printMenuDots(7);
                System.out.println(entry.getValue());
            }

        }
    }
    public void printMenuDots(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(".");
        }
    }
    public String enterString() {
        return scan.nextLine();
    }
    public int enterInt() {
        int inputInt = scan.nextInt();
        scan.nextLine();
        return inputInt;
    }

//    public String enterChoice(String stage) {
//        for (int i = 0; i < 3; i++) {
//            if (stage.equalsIgnoreCase("menu")){
//                System.out.println("Enter your choice");
//                int inputKey = enterInt();
//            }
//        }
//
//
//
//    }

}
