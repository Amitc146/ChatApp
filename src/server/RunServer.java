import java.io.IOException;

public class RunServer {
    public static void main(String[] args) {
        if (args.length == 0)
            invalidPort();
        try {
            int port = Integer.parseInt(args[0]);
            new Server(port);
        } catch (IOException e) {
            System.out.println("Failed to start the server.");
        } catch (NumberFormatException f) {
            invalidPort();
        }
    }

    private static void invalidPort() {
        System.out.println("Invalid port");
        System.exit(1);
    }
}
