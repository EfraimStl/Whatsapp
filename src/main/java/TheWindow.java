import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.*;

public class TheWindow extends JFrame {
    private static ChromeDriver driver;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    public TheWindow() {
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setBackground(Color.white);

        JButton button = new JButton();
        button.setBounds(100, 100, 300, 300);
        button.setVisible(true);
        button.setText("START");
        this.add(button);

        button.addActionListener((e -> {
            button.setVisible(false);
           PhoneNumber phoneNumber = new PhoneNumber();
           this.dispose();
        }));

    }
}




