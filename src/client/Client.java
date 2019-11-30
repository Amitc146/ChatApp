import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    public Client(String host, int port, String userName) throws IOException {
        socket = new Socket(host, port);
        sendUserNameToServer(userName);
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }


    private void sendUserNameToServer(String userName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(userName);
        } catch (Exception e) {
            System.out.println("Failed to send username");
            System.exit(1);
        }
    }


    public void sendMessageToServer(String message) {
        writer.println(message);
    }


    public String getMessageFromServer() throws IOException {
        return reader.readLine();
    }


    public void disconnectFromServer() {
        try {
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Failed to disconnect");
            System.exit(1);
        }
    }

}
