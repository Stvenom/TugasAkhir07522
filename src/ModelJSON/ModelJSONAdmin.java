package ModelJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ModelJSONAdmin<T> {
        private final String fname;

        public ModelJSONAdmin(String fname) {
            this.fname = fname;
            setupFile();
        }

        public boolean checkFile() {
            boolean status = false;
            if (new java.io.File(fname).exists()) {
                status = true;
            }
            return status;
        }

        public void setupFile() {
            try {
                if (checkFile() == false) {
                    File file = new File(fname);
                    if (file.createNewFile()) {
                        try (FileWriter fileWriter = new FileWriter(file, true);
                             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                            bufferedWriter.write("[]");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error.");
                e.printStackTrace();
            }
        }

        public JsonArray convertListToJsonArray(ArrayList<T> list) {
            return new Gson().toJsonTree(list).getAsJsonArray();
        }

        private ArrayList<T> convertJsonArrayToList(JsonArray jsonArray, Type type) {
            Gson gson = new Gson();
            return gson.fromJson(jsonArray, type);
        }

        public void writeToFile(ArrayList<T> menuList) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(menuList);
            try (FileWriter file = new FileWriter(fname)) {
                file.write(json);
            } catch (IOException e) {
                System.out.println("Error.");
                e.printStackTrace();
            }
        }

        public ArrayList<T> readFromFile(Type type) {
            ArrayList<T> list = new ArrayList<>();
            try (Reader reader = new FileReader(fname)) {
                JsonArray jsonArray = new JsonParser().parse(reader).getAsJsonArray();
                list = convertJsonArrayToList(jsonArray, type);
            } catch (Exception e) {
                System.out.println("Error.");
                e.printStackTrace();
            }
            return list;
        }

}

