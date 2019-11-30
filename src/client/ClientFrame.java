/**
 * Main frame to run the client.
 */

import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {
    private LoginPanel loginPanel;
    private ChatPanel chatPanel;


    public ClientFrame() throws HeadlessException {
        loginPanel = new LoginPanel(this);

        setTitle("Chat");
        setSize(450, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(loginPanel);
        setVisible(true);
    }


    public void setChatPanel(ChatPanel chatPanel) {
        this.chatPanel = chatPanel;
        chatPanel.setVisible(false);
        add(chatPanel);
    }


    public void showChatPanel() {
        setVisible(false);
        loginPanel.setVisible(false);
        chatPanel.setVisible(true);
        setVisible(true);
    }


    public void showLoginPanel() {
        setVisible(false);
        chatPanel.setVisible(false);
        loginPanel.setVisible(true);
        setVisible(true);
    }
}
