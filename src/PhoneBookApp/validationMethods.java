package PhoneBookApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for methods used for validating contact object creation
 */
public class validationMethods {

    static SetApp mSetApp = SetApp.getInstance();

    /**
     * Clean and validate String contains only english letters using removeWhiteSpaces and isOnlyEnglish letters methods
     *
     * @param str - String to be validated
     * @return cleaned and validated string or null if string is invalid
     */
    public static String validateLettersOnly(String str) {
        String cleanStr = removeWhiteSpaces(str);
        if (isOnlyEnglishLetters(str)) {
            return cleanStr;
        }
        return null;
    }

    /**
     * Validating only english letters, using removeWhiteSpaces method to remove white spaces
     *
     * @param str to validate
     * @return true if only english letters
     */
    public static boolean isOnlyEnglishLetters(String str) {
        // ASCII letters "a" = 97, "z" = 122, "A" = 65, "Z" = 90, " " = 32
        String validName = removeWhiteSpaces(str);
        for (int i = 0; i < validName.length(); i++) {
            int charValue = validName.charAt(i);
            if (!((charValue >= 65 && charValue <= 90) || (charValue >= 97 && charValue <= 122) || charValue == 32)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Valid name is not null or empty and has no consecutive spaces
     *
     * @param str - String input to format
     * @return String without consecutive spaces or null
     */
    public static String removeWhiteSpaces(String str) {
        String nameNoUselessSpaces = str.replaceAll("\\s+", " ").trim();
        if (nameNoUselessSpaces.length() > 0) {
            return nameNoUselessSpaces;
        } else {
            return null;
        }
    }

    /**
     * Removing hyphens from string
     *
     * @param hyphenedStr String with hyphen
     * @return String without hyphen
     */
    public static String removeHyphen(String hyphenedStr) {
        String[] splittedStr = hyphenedStr.split("[- +]");
        String result = "";
        for (String s : splittedStr) {
            result = result.concat(s);
        }
        return result;
    }

    /**
     * Cleaning string from white spaces and hyphens
     *
     * @param num - String numbers to be cleaned
     * @return Cleaned number string
     */
    public static String cleanNumber(String num) {
        String validName = removeWhiteSpaces(num);
        try {
            for (int i = 0; i < validName.length(); i++) {
                int charValue = validName.charAt(i);
                if (charValue == 45 || charValue == 32) {
                    validName = removeHyphen(validName);
                }
            }
        } catch (NullPointerException npe) {
            System.err.println("Error in cleanNumber method");
        }
        return validName;
    }

    /**
     * Cleaning and validating phone number chars
     *
     * @param num - Number to be validated
     * @return true if string contains only numbers
     */
    public static boolean isNumbersOnly(String num) {
        String cleanNum = cleanNumber(num);
        try {
            for (int i = 0; i < cleanNum.length(); i++) {
                int charValue = cleanNum.charAt(i);
                if (!(charValue >= 48 && charValue <= 57)) {
                    return false;
                }
            }
        } catch (NullPointerException npe) {
            return false;
        }
        return true;
    }

    public static boolean isItemNumValid(int num, Map map) {
        return num <= map.size() && num > 0;
    }

    /**
     * Accepts and validates Y or N input
     * @param startMessage - Which message to prompt the user with
     * @return - 1 for Y, 0 for N, 2 for invalid input
     */
    public static int enterAndValidateYorN(String startMessage) {
        myPhoneBook.printTextsFromMap(startMessage);
        for (int i = 0; i < 3; i++) {
            int errorMessage = PhoneBookAppMethods.calculateMessageIndex(i, true, true, true);
            try {
                String input = mSetApp.scan.nextLine();
                if (isYorN(input).equalsIgnoreCase("Y")) {
                    return 1;
                } else if (isYorN(input).equalsIgnoreCase("N")) {
                    return 0;
                }
            } catch (NullPointerException npe) {
                PhoneBookAppMethods.printErrorMessages(errorMessage);
            }
        }
        return 2;
    }

    public static String isYorN(String input) {
        if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")) {
            return input;
        }
        return null;
    }
}
