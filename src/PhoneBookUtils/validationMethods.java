package PhoneBookUtils;

import java.util.Map;

/**
 * Class for methods used for validating contact object creation
 */
public class validationMethods {

    /**
     * Clean and validate String contains only english letters using removeWhiteSpaces and isOnlyEnglish letters methods
     * @param str - String to be validated
     * @return cleaned and validated string or null if string is invalid
     */
    public String validateLettersOnly(String str) {
        String cleanStr = removeWhiteSpaces(str);
        if (isOnlyEnglishLetters(str)) {
            return cleanStr;
        }
        return null;
    }

    /**
     * Validating only english letters, using validName method to remove white spaces
     *
     * @param str to validate
     * @return true if only english letters
     */
    public boolean isOnlyEnglishLetters(String str) {
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
    public String removeWhiteSpaces(String str) {
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
    public String removeHyphen(String hyphenedStr) {
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
    public String cleanNumber(String num) {
        String validName = removeWhiteSpaces(num);
        try {
            for (int i = 0; i < validName.length(); i++) {
                int charValue = validName.charAt(i);
                if (charValue == 45 || charValue == 32) {
                    validName = removeHyphen(validName);
                }
            }
        } catch (NullPointerException npe) {
        }
        return validName;
    }

    /**
     * Cleaning and validating phone number chars
     *
     * @param num - Number to be validated
     * @return true if string contains only numbers
     */
    public boolean isNumbersOnly(String num) {
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

    public boolean isItemNumValid(int num, Map map) {
        return num <= map.size() && num > 0;
    }
}