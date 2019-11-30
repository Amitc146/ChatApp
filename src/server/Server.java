import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<User> users;


    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.users = new ArrayList<>();
        System.out.println("*** Server Ready ***");
        new UsersAccepter(this, serverSocket).start();
    }


    public void removeUser(User user) {
        if (users.remove(user)) {
            String message = "* " + user + " has left the chat *";
            sendMessageToAllUsers(message);
            printActiveUsers();
        }
    }


    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            user.sendMessageToClient("Server: Welcome " + user + ". You're ready to chat.");
            sendMessageToAllUsers("* " + user + " has joined the chat *");
            printActiveUsers();
        }
    }


    public void printUserMessage(String message) {
        sendMessageToAllUsers(message);
    }


    private void sendMessageToAllUsers(String message) {
        System.out.println(message);
        for (User user : users) {
            user.sendMessageToClient(message);
        }
    }


    private void printActiveUsers() {
        sendMessageToAllUsers("* Active Users: " + users);
    }


}
