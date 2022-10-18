package PhoneBookApp;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class appTexts {
    public String welcome = "WELCOME TO THE PHONE BOOK MAIN MENU";
    public String menuInstructions = "WHICH ACTION NUMBER YOU WISH TO EXECUTE?";
    public String invalidInputWarn = "Input is invalid, try again: ";
    public String lastInvalidInputWarn = "Input is still invalid, last chance: ";
    public String inputErrMsg = "Due to invalid inputs the app is unable to perform it's designated actions. \nGOODBYE";
    public String enterName = "Enter contact name (english letters only, up to 20 characters): ";
    public String nameLengthWarn = "Name is limited to 20 characters";
    public String enterPhone = "Enter contact phone number: ";
    public String phoneFormat = "Phone number should have 9 numbers and begin with 0 for landline or 05 for cellphone";
    public String exit = "Thank you for using the PhoneBook Application - GOODBYE";

    // TODO continue and test
    public HashMap<String, String> generateTexts() {
        try {
            // create Gson instance
            Gson gson = new Gson();
            HashMap<String, String> map;

            // create a reader
            String textsFilePath = "C:\\Users\\Tamir\\IdeaProjects\\PhoneBook\\src\\Texts.json";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(textsFilePath));

            // convert JSON file to map
             map = gson.fromJson(bufferedReader, HashMap.class);

            // print map entries
//            for (Map.Entry<?, ?> entry : map.entrySet()) {
//                System.out.println(entry.getKey() + " = " + entry.getValue());
//            }

            bufferedReader.close();
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
