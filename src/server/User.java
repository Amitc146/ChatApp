/**
 * Server thread to create connection with the user
 */

import java.io.*;
import java.net.*;

public class User extends Thread {
    private String userName;
    private Server server;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    public User(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        readUserNameFromClient();
        start();
    }


    private void readUserNameFromClient() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            userName = (String) inputStream.readObject();
        } catch (Exception e) {
            System.out.println("Failed to read username");
            System.exit(1);
        }
    }


    public void sendMessageToClient(String message) {
        writer.println(message);
    }


    private void getMessageFromClient() throws IOException {
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            inputLine = userName + ": " + inputLine;
            server.printUserMessage(inputLine);
        }
    }


    @Override
    public String toString() {
        return userName;
    }


    public void run() {
        try {
            getMessageFromClient();
        } catch (IOException e) {
        } finally {
            try {
                writer.close();
                reader.close();
            } catch (IOException e) {
                System.exit(1);
            }
            server.removeUser(this);
        }
    }


}
