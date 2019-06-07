package instaWeather;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
    tf1.setBounds(147, 129, 116, 22);
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
  }

}
