package PhoneBookApp;

import java.util.Map;


public class PhoneBookAppMethods {
    public static void printMenu() {
        System.out.println(appTexts.textsMap.get("welcome"));
        for (Map.Entry<Integer, String> entry : setApp.menu.entrySet()) {
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
        System.out.println(appTexts.textsMap.get("menuInstructions"));
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

    public static String enterString() {
        return setApp.scan.nextLine();
    }

    /**
     * Getting user choice from menu and validate it
     *
     * @return int input or 0 if input invalid 3 times
     */
    public static int enterMenuChoice() {
        for (int i = 0; i < 3; i++) {
            String input = enterString();
            if (validationMethods.isNumbersOnly(input)) {
                int inputInt = Integer.parseInt(input);
                if (validationMethods.isItemNumValid(inputInt, setApp.menu)) {
                    return inputInt;
                }
            }
           printErrorMessages(1);
        }
        printErrorMessages(5);
        return 0;
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
     */
    public static void printErrorMessages(int messageIndex) {
        switch (messageIndex) {
            case 1 -> System.err.println(appTexts.textsMap.get("invalidInputWarn"));
            case 2 -> System.err.println(appTexts.textsMap.get("nameLengthWarn"));
            case 3 -> System.err.println(appTexts.textsMap.get("phoneFormat"));
            case 4 -> System.err.println(appTexts.textsMap.get("lastInvalidInputWarn"));
            case 5 -> System.err.println(appTexts.textsMap.get("nameLengthWarn") + " - " + appTexts.textsMap.get("lastInvalidInputWarn"));
            case 6 -> System.err.println(appTexts.textsMap.get("phoneFormat") + " - " + appTexts.textsMap.get("lastInvalidInputWarn"));
            case 7 -> System.err.println(appTexts.textsMap.get("inputErrMsg"));
        }
    }

    public static int calculateMessageIndex(int i, boolean isLengthValid, boolean isNameField) {
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
            return 1;
        }
    }


}
