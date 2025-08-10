import javax.swing.*;
import java.io.*;

class Account {
    private String accountHolder;
    private int balance = 0;

    public void setAccountHolder(String name) {
        this.accountHolder = name;
    }
    public String getAccountHolder() { return accountHolder; }
    public int getBalance() { return balance; }

    public void deposit(int amount) {
        if(amount <= 0) {
            JOptionPane.showMessageDialog(null, "Amount must be positive!");
            return;
        }
        balance += amount;
        JOptionPane.showMessageDialog(null, "Deposited: " + amount +"\nAvailable Balance: " + getBalance());
    }

    public void withdraw(int amount) {
        if(amount <= 0) {
            JOptionPane.showMessageDialog(null, "Amount must be positive!");
            return;
        }
        if(amount > balance) {
            JOptionPane.showMessageDialog(null, "Insufficient funds!");
            return;
        }
        balance -= amount;
        JOptionPane.showMessageDialog(null, "Withdrawn: " + amount +"\nAvailable Balance: " + getBalance());
    }
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(accountHolder);
            writer.println(balance);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving account: " + ex.getMessage());
        }
    }

    // Load account data from a file
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            accountHolder = reader.readLine();
            balance = Integer.parseInt(reader.readLine());
        } catch (Exception ex) {
            // File does not exist or is invalid, start fresh
            accountHolder = "";
            balance = 0;
        }
    }

}