package utils;

import javax.naming.InvalidNameException;

public class validationMethods {

    /**
     * Clean and validate name property
     * @param name - Name to be validated
     * @return cleaned and validated name or null if name is invalid
     */
    public String validateName(String name) {
        String cleanName = validStringInput(name);
        if (isOnlyEnglishLetters(name)) {
            return cleanName;
        }
        return null;
    }

    /**
     * Clean, validate and parse phone number property
     * @param phone - String of phone number to be validated
     * @return int of formatted and validated phone number or null if phone number is invalid
     * @throws NumberFormatException if not starting with 0 and not 9 numbers
     */
    public String validatePhone(String phone) {
        String formattedPhone = cleanNumber(phone);
        if (isNumbersOnly(phone)) {
            if (formattedPhone.length() == 9 && formattedPhone.charAt(0) == 48) {
                return formattedPhone;
            } else {
                throw new NumberFormatException("PHONE NUMBER FORMAT IS INVALID");
            }
        }
        return null;
    }

    /**
     * Validating only english letters, using validName method to remove white spaces
     *
     * @param str to validate
     * @return true if only english letters
     * @throws IllegalArgumentException if String has numbers or special chars
     */
    public boolean isOnlyEnglishLetters(String str) {
        // ASCII letters "a" = 97, "z" = 122, "A" = 65, "Z" = 90, " " = 32
        String validName = validStringInput(str);
        for (int i = 0; i < str.length(); i++) {
            int charValue = str.charAt(i);
            if (!((charValue >= 65 && charValue <= 90) || (charValue >= 97 && charValue <= 122) || charValue == 32)) {
                throw new IllegalArgumentException("ONLY ENGLISH LETTERS ALLOWED");
            }
        }
        return true;
    }

    /**
     * Valid name is not null or empty and has no consecutive spaces
     *
     * @param str - String input to format
     * @return String without consecutive spaces
     * @throws IllegalArgumentException if String is empty
     * @throws NullPointerException     if String is null
     */
    public String validStringInput(String str) {
        String nameNoUselessSpaces = str.replaceAll("\\s+", " ").trim();
        if (nameNoUselessSpaces.length() > 0) {
            return nameNoUselessSpaces;
        } else {
            throw new IllegalArgumentException("INVALID INPUT - empty string");
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
        String validName = validStringInput(num);
        for (int i = 0; i < validName.length(); i++) {
            int charValue = validName.charAt(i);
            if (charValue == 45 || charValue == 32) {
                validName = removeHyphen(validName);
            }
        }
        return validName;
    }

    /**
     * Cleaning and validating phone number chars
     *
     * @param num - Number to be validated
     * @return true if string contains only numbers
     * @throws IllegalArgumentException if containing anything but numbers (accepts and removes spaces and hyphens)
     */
    public boolean isNumbersOnly(String num) {
        String cleanNum = cleanNumber(num);
        for (int i = 0; i < cleanNum.length(); i++) {
            int charValue = cleanNum.charAt(i);
            if (!(charValue >= 48 && charValue <= 57)) {
                throw new IllegalArgumentException("ONLY NUMBERS ALLOWED");
            }
        }
        return true;
    }
}
