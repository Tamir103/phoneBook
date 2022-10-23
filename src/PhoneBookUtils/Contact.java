package PhoneBookUtils;

/**
 * Phone book contact object.
 * Contains name and phone number for each contact object
 */
public class Contact {
    private String name;
    private String phoneNumber;

//    public Contact(String name, String phoneNumber) {
//        setName(name);
//        setPhoneNumber(phoneNumber);
//    }

    /**
     * Setting contact name and validating the use of english letters
     * In this case name is limited to 20 characters
     * @param name - set contact name
     * @throws IllegalArgumentException if input invalid
     */
    public void setName(String name) {
        int charLimit = 20;
        String validatedName = validateName(name, charLimit);
        if (!validatedName.equals("2")) {
            if (!validatedName.equals("1")) {
                this.name = validatedName;
            } else {
                throw new ArithmeticException("Name length invalid");
            }
        } else {
            throw new IllegalArgumentException("Name input is invalid");
        }
    }

    public String getName() {
        return this.name;
    }

    /**
     * Setting and validating contact phone number
     *
     * @param phoneNumber to set
     * @throws NumberFormatException if input invalid
     */
    public void setPhoneNumber(String phoneNumber) {
        String validatedPhone = validatePhone(phoneNumber);
        if (validatedPhone != null) {
            if (!validatedPhone.equals("length invalid")) {
            this.phoneNumber = validatedPhone;
            } else {
                throw new IllegalArgumentException("Phone input length invalid");
            }
        } else {
            throw new NumberFormatException("Phone input invalid");
        }
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Clean and validate name property
     *
     * @param name - Name to be validated
     * @param numOfCharsLimit How many characters allowed
     * @return cleaned and validated name, "1" if characters limit invalid or "2" if not in english
     */
    public String validateName(String name, int numOfCharsLimit) {
        String cleanName = cleanStringInput(name);
        if (isOnlyEnglishLetters(name)) {
            if (numOfCharsRestriction(name, numOfCharsLimit)) {
                return cleanName;
            } else {
                return "1";
            }
        } else {
            return "2";
        }
    }

    private boolean numOfCharsRestriction(String str, int limit) {
        return str.length() <= limit;
    }

    /**
     * Clean, validate and parse phone number property
     *
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
        return "length invalid";
    }

    /**
     * Determines phone number type
     *
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
        if (validName != null) {
            for (int i = 0; i < validName.length(); i++) {
                int charValue = validName.charAt(i);
                if (!((charValue >= 65 && charValue <= 90) || (charValue >= 97 && charValue <= 122) || charValue == 32)) {
                    return false;
                }
            }
        } else {
            return false;
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
        String validNum;
        try {
            validNum = cleanStringInput(num);
            for (int i = 0; i < validNum.length(); i++) {
                int charValue = validNum.charAt(i);
                if (charValue == 45 || charValue == 32) {
                    validNum = removeHyphen(validNum);
                }
            }
        } catch (NullPointerException npe) {
            return null;
        }
        return validNum;
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


}
