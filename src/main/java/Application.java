import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Application {
    static Account account = new Account();

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Default look and feel.
        }

        SwingUtilities.invokeLater(LoginFrame::new);
    }

}