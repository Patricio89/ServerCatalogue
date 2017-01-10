package nackademin.java;

import java.io.*;

public class HandleDatabase {
    private File dbContacts;
    private String line = "";
    private String modified;
    private String str;

    public String getData() {
        try {
            dbContacts = new File("C:\\Users\\patri\\Desktop\\ServerCatalogue\\src\\contacts.csv");
            FileReader fileReader = new FileReader(dbContacts);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((str = bufferedReader.readLine()) != null) {

                line += str + "\n";

            }
            modified = line.replace(',',' ');
            System.out.println(modified);
            fileReader.close();
        } catch (IOException i) {
            i.printStackTrace();
        }

        return modified;
    }
}

