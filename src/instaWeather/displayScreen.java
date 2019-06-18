package instaWeather;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.log4j.BasicConfigurator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class displayScreen {

  private JFrame frame;
  private JLabel lbclock;
  private JTextField tf1;
  protected String address;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          displayScreen window = new displayScreen();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public void clock() {
    Thread clock = new Thread() {
      public void run() { 
        try {
          while(true) {
            Calendar cal = new GregorianCalendar();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            
            int seconds = cal.get(Calendar.SECOND);
            int minutes = cal.get(Calendar.MINUTE);
            int hour = cal.get(Calendar.HOUR);
            
            lbclock.setText(month + "/" + day + "/" + year + "  " + hour + ":" + minutes + ":" + seconds);
            sleep(1000);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    clock.start();
  }
  
  /**
   * Create the application.
   */
  public displayScreen() {
    BasicConfigurator.configure();
    initialize();
    clock();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 450, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    
    tf1 = new JTextField();
    tf1.setBounds(147, 129, 127, 22);
    frame.getContentPane().add(tf1);
    tf1.setColumns(10);
    
    JButton btnGo = new JButton("Go!");
    btnGo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        address = tf1.getText();
        if(address == null) {
          JOptionPane.showMessageDialog(null, "Address cannot be null");
        }
        frame.dispose();
        MainApp application = new MainApp(address);
        application.setVisible(true);
      }
    });
    btnGo.setBounds(157, 164, 97, 25);
    frame.getContentPane().add(btnGo); 
    
    lbclock = new JLabel("");
    lbclock.setBounds(12, 13, 160, 49);
    frame.getContentPane().add(lbclock);
    
    JLabel lblInstaWeather = new JLabel("INSTA WEATHER");
    lblInstaWeather.setFont(new Font("Calisto MT", Font.BOLD, 16));
    lblInstaWeather.setBounds(135, 37, 148, 25);
    frame.getContentPane().add(lblInstaWeather);
    
    JPanel panel = new JPanel();
    panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
    panel.setBounds(80, 90, 263, 118);
    frame.getContentPane().add(panel);
    
    JLabel lblEnterYourAddress = new JLabel("Enter your Address:");
    panel.add(lblEnterYourAddress);
  }
}
