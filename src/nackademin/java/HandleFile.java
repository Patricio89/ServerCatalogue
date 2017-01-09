package nackademin.java;

import java.io.*;

public class HandleFile {

    public String getDataFromDatabase() {
        String fileContent = null;
        try {
            FileInputStream getFile = new FileInputStream("contacts.csv");
            InputStreamReader contentToString = new InputStreamReader(getFile);
            BufferedReader reader = new BufferedReader(contentToString);
            while (reader.readLine() != null) {
                fileContent = reader.readLine();
                fileContent.replace(","," ");
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return fileContent;
    }
}
