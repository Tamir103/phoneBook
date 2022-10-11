package utils;

public class contact {
    private String name;
    private String phoneNumber;

    public void setName(String name) {
        // ASCII letters a = 97, z = 122, A = 65, Z = 90
        for (int i = 0; i < name.length(); i++) {
            char currentChar = name.charAt(i);
            int charValue = (int) currentChar;
            if (!((charValue >= 65 && charValue <= 90) || (charValue >= 97 && charValue <= 122))) {
                throw new IllegalArgumentException("ONLY ENGLISH LETTERS ALLOWED");
            }
        }
        this.name = name;
    }
}
