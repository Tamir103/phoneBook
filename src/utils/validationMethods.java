package utils;

import java.util.Map;

/**
 * Class for methods used for validating contact object creation
 */
public class validationMethods {

    /**
     * Clean and validate name property
     * @param name - Name to be validated
     * @return cleaned and validated name or null if name is invalid
     */
    public String validateName(String name) {
        String cleanName = cleanStringInput(name);
        if (isOnlyEnglishLetters(name)) {
            return cleanName;
        }
        return null;
    }

    /**
     * Clean, validate and parse phone number property
     * @param phone - String of phone number to be validated
     * @return formatted and validated phone number or null if phone number is invalid
     */
    public String validatePhone(String phone) {
        String formattedPhone = cleanNumber(phone);
        if (isNumbersOnly(formattedPhone)) {
            if (phoneNumType(formattedPhone) == null) {
                return null;
            } else if (phoneNumType(formattedPhone).equals("cellphone")) {
                if (formattedPhone.length() == 10) {
                    return formattedPhone;
                }
            } else if (phoneNumType(formattedPhone).equals("landline")) {
                if (formattedPhone.length() == 9) {
                    return formattedPhone;
                }
            }
        }
        return null;
    }

    /**
     * Determines phone number type
     * @param phone - Phone number
     * @return cellphone, landline or null
     */
    public String phoneNumType(String phone) {
    // ASCII for 0 = 48, 5 = 53
        if (phone.charAt(0) == 48) {
            if (phone.charAt(1) == 53) {
                return "cellphone";
            } else {
                return "landline";
            }
        } else {
            return null;
        }
    }

    /**
     * Validating only english letters, using validName method to remove white spaces
     *
     * @param str to validate
     * @return true if only english letters
     */
    public boolean isOnlyEnglishLetters(String str) {
        // ASCII letters "a" = 97, "z" = 122, "A" = 65, "Z" = 90, " " = 32
        String validName = cleanStringInput(str);
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
    public String cleanStringInput(String str) {
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
        String validName = cleanStringInput(num);
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
