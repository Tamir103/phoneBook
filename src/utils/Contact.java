package utils;

/**
 * Phone book contact object.
 * Contains name and phone number for each contact object
 */
public class Contact {
    private String name;
    private String phoneNumber;

    private static final validationMethods validation = new validationMethods();

    /**
     * Setting contact name and validating the use of english letters
     *
     * @param name - set contact name
     * @throws IllegalArgumentException if input invalid
     */
    public void setName(String name) {
        String validatedName = validation.validateName(name);
        if (validatedName != null) {
            this.name = validatedName;
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
        String validatedPhone = validation.validatePhone(phoneNumber);
        if (validatedPhone != null) {
            this.phoneNumber = validatedPhone;
        } else {
            throw new NumberFormatException("Phone input invalid");
        }
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }


}
