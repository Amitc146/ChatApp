/**
 * Server thread to accept new users
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UsersAccepter extends Thread {
    private Server server;
    private ServerSocket serverSocket;


    public UsersAccepter(Server server, ServerSocket serverSocket) {
        this.server = server;
        this.serverSocket = serverSocket;
    }


    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                server.addUser(new User(socket, server));
            } catch (IOException e) {
                System.exit(1);
            }
        }
    }

}
