import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class PhoneNumber extends JFrame implements ActionListener {
    public static ChromeDriver driver;
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 600;
    private int x = 200, y1 = 150, y2 = 250, width = 200, height = 50;

   Button button1 = new Button("Send message");
    JTextField phoneNumber = new JTextField();
    JTextField message = new JTextField();
    JLabel errorLabel = new JLabel();
    JLabel phoneNumberLabel = new JLabel("ENTER PHONE NUMBER");
    JLabel textLabel = new JLabel(" ENTER MESSAGE");
    JLabel afterSend = new JLabel("message was send successfully");
    JLabel title = new JLabel("Logged in successfully");


    public PhoneNumber() {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        boolean flag = true;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://web.whatsapp.com/");
        while (flag) {
            if (driver.getPageSource().contains("Search input textbox")) {
                flag = false;
            }
            }

        // window setting
        this.getContentPane().setBackground(Color.BLACK);
        this.setTitle("login");
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);


        // font
        Font myFont = new Font("Arial", Font.BOLD, 15);


        // button setting
        button1.setFont(myFont);
        button1.setBackground(Color.orange.darker());
        button1.setBounds(90, 400, 300, 70);
        button1.setFocusable(true);
        button1.addActionListener(this);
        button1.setEnabled(true);
        this.add(button1);

        // phone number
        phoneNumber.setBounds(this.x, this.y1, this.width, this.height);
        phoneNumber.setBackground(Color.WHITE);
        phoneNumber.setBackground(Color.blue);
        phoneNumber.setFont(myFont);
        this.add(phoneNumber);
        phoneNumber.setVisible(true);

        // text
        message.setBounds(this.x, this.y2, this.width, this.height);
        message.setBackground(Color.blue);
        message.setFont(myFont);
        this.add(message);
        this.add(phoneNumber);
        this.setVisible(true);

        // label of title
        title.setBounds(40, 20, 420, 80);
        title.setForeground(Color.blue);
        title.setBackground(Color.BLACK);
        title.setOpaque(true);
        title.setFont(myFont);
        this.add(title);

        // font Labels
        Font labelsFont = new Font("Arial", Font.BOLD, 10);

        // phone number label
        phoneNumberLabel.setBounds(10, this.y1, 170, this.height);
        phoneNumberLabel.setForeground(Color.blue);
        phoneNumberLabel.setBackground(Color.BLACK);
        phoneNumberLabel.setOpaque(true);
        phoneNumberLabel.setFont(labelsFont);
        this.add(phoneNumberLabel);

        // text label
        textLabel.setBounds(10, this.y2, 170, this.height);
        textLabel.setForeground(Color.blue);
        textLabel.setBackground(Color.BLACK);
        textLabel.setOpaque(true);
        textLabel.setFont(labelsFont);
        this.add(textLabel);

        // errorLabel setting
        errorLabel.setBounds(90, 350, 300, 50);
        errorLabel.setBackground(Color.BLACK.darker());
        errorLabel.setForeground(Color.RED);
        errorLabel.setBackground(Color.BLACK);
        errorLabel.setOpaque(true);
        errorLabel.setFont(myFont);
        errorLabel.setVisible(false);
        this.add(errorLabel);

        //after send
        afterSend.setBounds(10, this.y2, 500, this.height);
        afterSend.setBackground(Color.blue);
        afterSend.setForeground(Color.green);
        afterSend.setFont(myFont);
        afterSend.setVisible(false);
        this.add(afterSend);

    }

    String deleteFirstNumber (String A){
         return A.substring(1);
    }

    boolean messsageIsNotEmpty(JTextField A) {
        if (A.getText().length() > 0)
            return true;
        return false;
    }

    boolean numberIsValuableTest(JTextField A) {
        String Begin = "";
        int numberOfDigits = A.getText().length();
        for (int i = 0; i < 2; i++) {
            Begin += A.getText().charAt(i);
        }
        for (int i = 0; i < A.getText().length(); i++)

            if (A.getText().charAt(i) < '0' || A.getText().charAt(i)>'9')
                return false;

        if (numberOfDigits != 10)
            return false;
        return Begin.equals("05");
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1){
            try {
                if (!numberIsValuableTest(phoneNumber) || !messsageIsNotEmpty(message)) {
                    errorLabel.setVisible(true);
                }
                if (numberIsValuableTest(phoneNumber) && !messsageIsNotEmpty(message))
                    errorLabel.setText("please enter a message");
                if (!numberIsValuableTest(phoneNumber))
                    errorLabel.setText("Please Enter A valuable phone number");
                if (numberIsValuableTest(phoneNumber) && messsageIsNotEmpty(message)) {
                    errorLabel.setVisible(false);
                    textLabel.setVisible(false);
                    phoneNumber.setVisible(false);
                    phoneNumberLabel.setVisible(false);
                    message.setVisible(false);
                    button1.setVisible(false);//*
                    title.setVisible(false);

                    boolean flag = true;
                    while (flag) {
                        driver.get("https://web.whatsapp.com/send?phone=972" + deleteFirstNumber(phoneNumber.getText()));
                        System.out.println("hi");
                        flag = false;
                    }
                    flag = true;
                    while (flag) {
                        if (driver.getPageSource().contains("Search…")) {
                            WebElement textBox = driver.findElement(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div.p3_M1 > div > div.fd365im1.to2l77zo.bbv8nyr4.mwp4sxku.gfz4du6o.ag5g9lrv > p"));
                            textBox.click();
                            textBox.sendKeys(message.getText());
                            WebElement send = driver.findElement(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div._3HQNh._1Ae7k > button > span"));
                            send.click();
                            flag = false;
                            Robot robot = new Robot();
                            robot.delay(2000);
                            driver.close();
                        }
                    }
                    afterSend.setVisible(true);
                }
            } catch (Exception x) {
                errorLabel.setVisible(true);
                errorLabel.setText("you must enter correct phone number");
            }
        ;
        }
    }
}


