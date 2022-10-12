package utils;

/**
 * Phone book contact object.
 * Contains name and phone number for each contact object
 */
public class contact {
    private String name;
    private String phoneNumber;

    private static final validationMethods validation = new validationMethods();

    /**
     * Setting contact name and validating the use of english letters
     * @param name - set contact name
     * @return true if name is valid
     */
    public boolean setName(String name) {
        String validatedName = validation.validateName(name);
        if (validatedName != null) {
            this.name =validatedName;
            return true;
        } else {
            System.err.println("Name input invalid");
            return false;
        }
    }

    public String getName() {
        return this.name;
    }

    /**
     * Setting and validating contact phone number
     * @param phoneNumber to set
     * @return true if phone is valid
     */
    public boolean setPhoneNumber(String phoneNumber) {
        String validatedPhone = validation.validatePhone(phoneNumber);
        if (validatedPhone != null) {
            this.phoneNumber = validatedPhone;
            return true;
        } else {
            System.err.println("Phone input invalid");
            return false;
        }
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }


}
