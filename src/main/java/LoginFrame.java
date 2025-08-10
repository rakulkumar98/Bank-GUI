import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField nameField;
    private JPasswordField passField;
    private JButton loginButton;
    private int attempts = 0;

    public LoginFrame() {
        setTitle("Welcome to Bank");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320, 220);
        setLayout(new GridLayout(5, 1, 5, 5));

        // Name input
        add(new JLabel("Enter your name:"));
        nameField = new JTextField();
        add(nameField);

        // Password input
        add(new JLabel("Enter password:"));
        passField = new JPasswordField();
        add(passField);

        // Button panel with centered login button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        buttonPanel.add(loginButton);
        add(buttonPanel);

        loginButton.addActionListener(e -> authenticate());

        // Enter key on password field triggers login
        passField.addActionListener(e -> loginButton.doClick());

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void authenticate() {
        String name = nameField.getText().trim();
        String password = new String(passField.getPassword());

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name.");
            return;
        }

        if (!"user".equals(password)) {
            attempts++;
            if (attempts >= 3) {
                JOptionPane.showMessageDialog(this, "Too many failed attempts. Exiting.");
                dispose();
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect password. Attempts left: " + (3 - attempts));
                passField.setText("");
            }
            return;
        }

        // Successful login
        Application.account.setAccountHolder(name);
        dispose();
        new MainMenuFrame();
    }
}
