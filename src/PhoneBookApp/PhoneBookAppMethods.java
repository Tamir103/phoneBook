package PhoneBookApp;

import java.util.Map;



public class PhoneBookAppMethods {

    static SetApp mSetApp = SetApp.getInstance();

    public void printMenu() {
        for (Map.Entry<Integer, String> entry : mSetApp.menu.entrySet()) {
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
        System.out.println(myPhoneBook.textsMap.get("menuInstructions"));
    }

    public static void printMenuDots(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(".");
        }
    }
    public static void printFrame(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print("~");
        }
        System.out.println();
    }

    /**
     * Getting user choice from menu and validate it
     *
     * @return int input or 0 if input invalid 3 times
     */
    public static int menuChoice(/*String input*/) {
        for (int i = 0; i < 3; i++) {
            String input = mSetApp.scan.nextLine();
            if (validationMethods.isNumbersOnly(input)) {
                int inputInt = Integer.parseInt(input);
                if (validationMethods.isItemNumValid(inputInt, mSetApp.menu)) {
                    return inputInt;
                }
            }
            printErrorMessages(1);
        }
        printErrorMessages(4);
        return 0;
    }

    public static boolean isListsSizesEquals(int listSize1, int listSize2) {
        return listSize1 == listSize2;
    }
    public static boolean performAnotherAction(String input) { // TODO organize and maybe use yn method
//        System.out.println(myPhoneBook.textsMap.get("anotherAction"));
//        String input = setApp.scan.nextLine();
        boolean result = false;
        try {
            if (validationMethods.isYorN(input).equalsIgnoreCase("Y")) {
                result = true;
            } else if (validationMethods.isYorN(input).equalsIgnoreCase("N")) {
                result = false;
            }
        } catch (NullPointerException npe) {
            printErrorMessages(1);
        }
        return result;
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
     *                     8 - Contact not in phone book
     */
    public static void printErrorMessages(int messageIndex) {
        switch (messageIndex) {
            case 1 -> System.err.println(myPhoneBook.textsMap.get("invalidInputWarn"));
            case 2 -> System.err.println(myPhoneBook.textsMap.get("nameLengthWarn"));
            case 3 -> System.err.println(myPhoneBook.textsMap.get("phoneFormat"));
            case 4 -> System.err.println(myPhoneBook.textsMap.get("lastInvalidInputWarn") + " - " + appTexts.textsMap.get("invalidInputWarn"));
            case 5 -> System.err.println(myPhoneBook.textsMap.get("lastInvalidInputWarn") + " - " + appTexts.textsMap.get("nameLengthWarn"));
            case 6 -> System.err.println(myPhoneBook.textsMap.get("lastInvalidInputWarn") + " - " + appTexts.textsMap.get("phoneFormat"));
            case 7 -> System.err.println(myPhoneBook.textsMap.get("inputErrMsg"));
            case 8 -> System.err.println(myPhoneBook.textsMap.get("contactNotExist"));
            case 9 -> System.err.println(myPhoneBook.textsMap.get("lastInvalidInputWarn"));
        }
    }

    //TODO not DRY enough
    public static int calculateMessageIndex(int i, boolean isLengthValid, boolean isNameField) { // TODO delete comments
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
        } else {
            return 7;
        }
    }


}
