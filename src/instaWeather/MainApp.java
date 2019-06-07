package instaWeather;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class MainApp extends JFrame {

  private JPanel contentPane;
  protected static String address;
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
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
    
    JLabel lb1 = new JLabel("");
    lb1.setBounds(23, 24, 69, 21);
    contentPane.add(lb1);
    
    JLabel lb2 = new JLabel("");
    lb2.setBounds(23, 58, 89, 16);
    contentPane.add(lb2);
    
    JPanel panel = new JPanel();
    panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    panel.setBounds(12, 13, 135, 76);
    contentPane.add(panel);
    
    JLabel lb4 = new JLabel("");
    lb4.setBounds(350, 112, 56, 39);
    contentPane.add(lb4);
    
    JLabel lb3 = new JLabel("");
    lb3.setBounds(43, 112, 69, 39);
    contentPane.add(lb3);
  }

}
