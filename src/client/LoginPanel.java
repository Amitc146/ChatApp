import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginPanel extends JPanel implements ActionListener {
    private ClientFrame clientFrame;
    private JTextField userNameInput, hostInput, portInput;
    private JButton connectButton, clearButton;
    private String userName, host, port;


    public LoginPanel(ClientFrame clientFrame) {
        this.clientFrame = clientFrame;
        addTextPanel();
        addInputPanel();
        addButtons();
    }


    // Setting up text fields for login info
    private void addInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

        userNameInput = new JTextField(16);
        userNameInput.setPreferredSize(new Dimension(160, 20));
        userNameInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        hostInput = new JTextField(16);
        hostInput.setPreferredSize(new Dimension(160, 20));
        hostInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        portInput = new JTextField(16);
        portInput.setPreferredSize(new Dimension(160, 20));
        portInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        inputPanel.add(userNameInput);
        inputPanel.add(hostInput);
        inputPanel.add(portInput);
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(inputPanel);
    }


    // Adding labels
    private void addTextPanel() {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));

        JLabel userLabel = new JLabel("Username:");
        JLabel hostInputLabel = new JLabel("Host:");
        JLabel portInputLabel = new JLabel("Port:");

        textPanel.add(userLabel);
        textPanel.add(hostInputLabel);
        textPanel.add(portInputLabel);
        textPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 40));
        add(textPanel);
    }


    private void addButtons() {
        connectButton = new JButton("Connect");
        connectButton.setPreferredSize(new Dimension(120, 30));
        connectButton.addActionListener(this);

        clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(120, 30));
        clearButton.addActionListener(this);

        add(connectButton);
        add(clearButton);
    }


    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == connectButton)
            connectClicked();
        else
            clearPanel();
    }


    private void clearPanel() {
        userNameInput.setText("");
        hostInput.setText("");
        portInput.setText("");
    }


    private void connectClicked() {
        userName = userNameInput.getText();
        host = hostInput.getText();
        port = portInput.getText();
        if (!checkEmptyFields())
            createChatPanel();
        else
            popError("Some fields are empty.");
    }


    private void createChatPanel() {
        try {
            clientFrame.setChatPanel(new ChatPanel(getHost(), getPort(), getUserName(), clientFrame));
            clientFrame.showChatPanel();
        } catch (IOException e) {
            popError("Failed to connect to server.");
        } catch (NumberFormatException f) {
            popError("Invalid port.");
        }
    }


    private boolean checkEmptyFields() {
        return userName.isBlank() || host.isBlank() || port.isBlank();
    }


    private void popError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    public String getUserName() {
        return userName;
    }


    public String getHost() {
        return host;
    }


    public int getPort() throws NumberFormatException {
        return Integer.parseInt(port);
    }

}
