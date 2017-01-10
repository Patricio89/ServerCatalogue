package nackademin.java;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private Socket clientConnection;
    private final int defaultPort = 61616;
    private HandleDatabase handleDatabase = new HandleDatabase();
    public void online(){
        try {

            ServerSocket serverSocket = new ServerSocket(defaultPort);

            while(true) {

                Socket clientSocket = serverSocket.accept();

                new Thread(
                        new Runnable() {

                            public void run() {
                                try {
                                    InputStream inputStream = clientSocket.getInputStream();
                                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                                    OutputStream outputStream = clientSocket.getOutputStream();
                                    PrintWriter writer = new PrintWriter(outputStream);

                                    BufferedReader reader = new BufferedReader(inputStreamReader);
                                    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                                        System.out.println(line);
                                        writer.println("Message received: " + line);
                                        writer.flush();
                                        if (line.equals("getall")) {
                                            String tempString = handleDatabase.getData();
                                            writer.println(tempString);
                                        }else if (line.equals("exit")){
                                            break;
                                        }
                                    }
                                    reader.close();
                                    writer.close();
                                    clientSocket.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                ).start();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
