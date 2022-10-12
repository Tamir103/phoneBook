package utils;

/**
 * Phone book contact object.
 * Contains name and phone number for each contact object
 */
public class contact {
    private String name;
    private String phoneNumber;

    /**
     * Setting contact name and validating the use of english letters
     * @param name - set contact name
     */
    public void setName(String name) {
        String cleanName = validStringInput(name);
        if (isOnlyEnglishLetters(name)) {
            System.out.println(cleanName);
            this.name = cleanName;
        }
    }

    public String getName() {
        return this.name;
    }

    /**
     * Setting and validating contact phone number
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        phoneNumber = validStringInput(phoneNumber);
        // validating valid length and that the number begins with 0
        if (phoneNumber.length() == 9 && phoneNumber.charAt(0) == 0) {

        }
    }

    /**
     * Validating only english letters, using validName method to remove white spaces
     * @param str to validate
     * @return true if only english letters
     * @throws IllegalArgumentException if String has numbers or special chars
     */
    private boolean isOnlyEnglishLetters(String str) {
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
     * @param str - String input to format
     * @return String without consecutive spaces
     * @throws IllegalArgumentException if String is empty
     * @throws NullPointerException if String is null
     */
    private String validStringInput(String str) {
        String nameNoUselessSpaces = str.replaceAll("\\s+", " ").trim();
        if (nameNoUselessSpaces.length() > 0) {
            return nameNoUselessSpaces;
        } else {
            throw new IllegalArgumentException("INVALID INPUT - empty string");
        }
    }
}
