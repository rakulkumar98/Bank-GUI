import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {

        Account account = Application.account;
        String name = account.getAccountHolder();
        setTitle("Online Banking Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 250);

        // Welcome label
        JLabel userLabel = new JLabel("Welcome, " + name + "!", SwingConstants.CENTER);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Buttons
        JButton depBtn = new JButton("Deposit");
        JButton wdBtn = new JButton("Withdraw");
        JButton infoBtn = new JButton("Account Info");
        JButton exitBtn = new JButton("Exit");

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1, 20, 10));
        buttonsPanel.add(depBtn);
        buttonsPanel.add(wdBtn);
        buttonsPanel.add(infoBtn);
        buttonsPanel.add(exitBtn);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(userLabel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        add(mainPanel);

        depBtn.addActionListener(e -> deposit());
        wdBtn.addActionListener(e -> withdraw());
        infoBtn.addActionListener(e -> showInfo());
        exitBtn.addActionListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(
                    MainMenuFrame.this,
                    "Are you sure you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void deposit() {
        String s = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
        if (s == null) return;
        try {
            int amount = Integer.parseInt(s);
            Application.account.deposit(amount);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number.");
        }
    }

    private void withdraw() {
        String s = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
        if (s == null) return;
        try {
            int amount = Integer.parseInt(s);
            Application.account.withdraw(amount);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number.");
        }
    }

    private void showInfo() {
        JOptionPane.showMessageDialog(this,
                "Account Holder: " + Application.account.getAccountHolder() +
                        "\nBalance: " + Application.account.getBalance());
    }
}
