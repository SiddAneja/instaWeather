package instaWeather;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import com.sun.prism.Image;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainApp extends JFrame {

  private JPanel contentPane;
  protected static String address;
  private JLabel lb1;
  private JLabel lb2;
  private JLabel lb3;
  private JLabel lb4;
  private ImageIcon imgIcon;
  private JRadioButton rb1;
  private JRadioButton rb2;
  private double tempInC;
  private double tempInF;
  private final ButtonGroup buttonGroup = new ButtonGroup();
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
  }

  public void run() {
    lb1.setText(address.toUpperCase());
    tempInC = (double) Math.round(getWeather.getInstance().getTemperatureCelsius(address));
    tempInF = (double) Math.round(getWeather.getInstance().getTemperatureFahrenheit(address));
    lb3.setText(Double.toString(tempInC));
    String icon = getWeather.getInstance().getIcon(address);
    String description = getWeather.getInstance().getDescription(address);
    lb2.setText(description);
    URL imgURL;
    try {
      imgURL = new URL("http://openweathermap.org/img/w/" + icon + ".png");
      //imgIcon = ImageIO.read(imgURL);
      imgIcon = new ImageIcon(imgURL,"");
      lb4.setIcon(imgIcon);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  /**
   * Create the frame.
   */
  public MainApp(String address) {
    MainApp.address = address;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    lb1 = new JLabel("");
    lb1.setBounds(23, 24, 126, 21);
    contentPane.add(lb1);
    
    lb2 = new JLabel("");
    lb2.setBounds(23, 58, 146, 16);
    contentPane.add(lb2);
    
    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    panel.setBounds(12, 13, 178, 76);
    contentPane.add(panel);
    
    lb4 = new JLabel("");
    lb4.setBounds(63, 112, 56, 39);
    contentPane.add(lb4);
    
    lb3 = new JLabel("");
    lb3.setBounds(134, 112, 69, 39);
    contentPane.add(lb3);
    
    rb1 = new JRadioButton("Celsius");
    rb1.setSelected(true);
    rb1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        lb3.setText(Double.toString(tempInC));
      }
    });
    buttonGroup.add(rb1);
    rb1.setBounds(283, 54, 127, 25);
    contentPane.add(rb1);
    
    rb2 = new JRadioButton("Fahrenheit");
    rb2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        lb3.setText(Double.toString(tempInF));
      }
    });
    buttonGroup.add(rb2);
    rb2.setBounds(283, 84, 127, 25);
    contentPane.add(rb2);
    
    JLabel lblSelectOne = new JLabel("Select one:");
    lblSelectOne.setBounds(283, 20, 91, 29);
    contentPane.add(lblSelectOne);
    run();
  }
}
