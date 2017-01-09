package nackademin.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleRequest{
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket clientSocket;
    private Server server;

    private void disconnect(){
        try {
            server.messageToClient("Closing connection to server...");
            reader.close();
            writer.close();
            clientSocket.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private String getData(){
        HandleFile handleFile = new HandleFile();
        String content = handleFile.getDataFromDatabase();
        return content;
    }

    public void validateRequest(){
        String messageFromClient = server.messageFromClient();
        if (messageFromClient.equals("getall")){
            String databaseContent =  getData();
            writer.println(databaseContent);
            server.messageToClient("database information sent.");
            writer.flush();
        }else if (messageFromClient.equals("exit")){
            disconnect();
        }else {
            server.messageToClient("Unknown request.");
        }
    }
}
