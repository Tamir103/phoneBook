package PhoneBookApp;

import java.util.Map;

import static PhoneBookApp.setApp.*;

public class PhoneBookAppMethods {
    public static void printMenu() {
        System.out.println(texts.welcome);
        for (Map.Entry<Integer, String> entry : menu.entrySet()) {
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
        System.out.println();
        System.out.println(texts.menuInstructions);
    }

    public static void printMenuDots(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(".");
        }
    }

    public static String enterString() {
        return scan.nextLine();
    }

    /**
     * Getting user choice from menu and validate it
     *
     * @return int input or 0 if input invalid 3 times
     */
    public static int enterMenuChoice() {
        for (int i = 0; i < 3; i++) {
            String input = enterString();
            if (validate.isNumbersOnly(input)) {
                int inputInt = Integer.parseInt(input);
                if (validate.isItemNumValid(inputInt, menu)) {
                    return inputInt;
                }
            }
           myPhoneBook.printInvalidInputWarn(i, true, true);
        }
        System.err.println(texts.inputErrMsg);
        return 0;
    }


}
