import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ChatPanel extends JPanel implements ActionListener {
    private ClientFrame clientFrame;
    private Client client;
    private JTextArea textArea;
    private JTextField textField;
    private JButton sendButton;
    private JButton disconnectButton;


    public ChatPanel(String host, int port, String userName, ClientFrame clientFrame) throws IOException {
        setLayout(new BorderLayout());
        addTextPanel();
        addDisconnectButton();

        this.clientFrame = clientFrame;
        this.client = new Client(host, port, userName);
        new MessagePrinter(this);
    }


    public Client getClient() {
        return client;
    }


    public void printText(String message) {
        textArea.append(message + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }


    // Setting up a chatting window, including a main text area and small text field for user input
    private void addTextPanel() {
        JPanel textPanel = new JPanel();

        textArea = new JTextArea(20, 34);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textPanel.add(scroll);

        sendButton = new JButton("send");
        sendButton.addActionListener(this);
        textField = new JTextField(27);
        textField.setPreferredSize(new Dimension(100, 25));
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.addKeyListener(new KeyHelper());
        textPanel.add(textField);
        textPanel.add(sendButton);

        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(textPanel, BorderLayout.CENTER);
    }


    private void addDisconnectButton() {
        JPanel disconnectPanel = new JPanel();
        disconnectButton = new JButton("Disconnect");
        disconnectButton.addActionListener(this);
        disconnectPanel.add(disconnectButton);
        add(disconnectPanel, BorderLayout.SOUTH);
    }


    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == sendButton)
            send();
        else
            disconnect();
    }


    private void send() {
        if (!textField.getText().isBlank()) {
            client.sendMessageToServer(textField.getText());
            textField.setText("");
        }
    }


    private void disconnect() {
        client.disconnectFromServer();
        clientFrame.showLoginPanel();
    }


    // Adds feature to send message with the keyboard by hitting "Enter"
    private class KeyHelper extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                send();
        }
    }
}
