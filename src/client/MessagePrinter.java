import java.io.IOException;

public class MessagePrinter extends Thread {
    private ChatPanel panel;


    public MessagePrinter(ChatPanel panel) {
        this.panel = panel;
        start();
    }


    public void run() {
        Client client = panel.getClient();
        while (true) {
            try {
                panel.printText(client.getMessageFromServer());
            } catch (IOException e) {
                yield();
            }
        }

    }

}
