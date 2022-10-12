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
     *
     * @param name - set contact name
     */
    public void setName(String name) {
        this.name = validation.validateName(name);
    }

    public String getName() {
        return this.name;
    }

    /**
     * Setting and validating contact phone number
     * @param phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = validation.validatePhone(phoneNumber);
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }


}
