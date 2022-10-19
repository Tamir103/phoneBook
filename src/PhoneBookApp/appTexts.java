package PhoneBookApp;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class appTexts {
    static HashMap<String, String> textsMap = generateTexts();
    public static HashMap<String, String> generateTexts(/*String jsonPath*/) {
        try {
            // create Gson instance
            Gson gson = new Gson();
            HashMap<String, String> map;

            // create a reader
            String textsFilePath = "C:\\Users\\Tamir\\IdeaProjects\\PhoneBook\\src\\Texts.json";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(/*textsFilePath*/ "Texts.json"));

            // convert JSON file to map
             map = gson.fromJson(bufferedReader, HashMap.class);

            bufferedReader.close();
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
