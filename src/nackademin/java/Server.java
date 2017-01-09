package nackademin.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int defaultTCP = 61616;
    private ServerSocket server;
    private Socket clientConnection;
    private PrintWriter writer;
    private BufferedReader reader;
    HandleRequest handler = new HandleRequest();

    public void online() {
        setUpServer();
        while (true){
            waitForClient();
            setupCommunication();
            messageFromClient();
            handler.validateRequest();
        }
    }

    private void setUpServer(){
        try {
            server = new ServerSocket(defaultTCP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForClient(){
        try {
            clientConnection = server.accept();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupCommunication() {
        try {
            writer = new PrintWriter(clientConnection.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            messageToClient("Connection to server established.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void messageToClient(String message){
        writer.println(message);
        writer.flush();
    }

    public String messageFromClient(){
        String message = null;
        try {
            while ((message = reader.readLine()) != null){
                return message;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

}
