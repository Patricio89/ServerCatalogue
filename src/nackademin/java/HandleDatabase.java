package nackademin.java;

import java.io.*;

public class HandleDatabase {
    private File dbContacts = null;
    private String line = null;
    private String modified = null;

    public String getData() {
        try {
            File dbContacts = new File("C:\\Users\\patri\\Desktop\\ServerCatalogue\\src\\contacts.csv");
            FileReader fileReader = new FileReader(dbContacts);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.readLine() != null) {

                line += bufferedReader.readLine() + "\n";
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

