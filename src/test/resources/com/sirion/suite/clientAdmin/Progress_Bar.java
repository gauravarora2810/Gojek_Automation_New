package test.resources.com.sirion.suite.clientAdmin;


import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.testng.annotations.Test;

public class Progress_Bar extends JPanel {

	private static final long serialVersionUID = 1L;

	JProgressBar pbar;
	static JFrame frame;
	static Container pane;


  static final int MY_MINIMUM = 0;

  static final int MY_MAXIMUM = 25;
  

  public Progress_Bar() {
    pbar = new JProgressBar();
    pbar.setMinimum(MY_MINIMUM);
    pbar.setMaximum(MY_MAXIMUM);
    add(pbar);
    
    
  }

  public void updateBar(int newValue) {
    pbar.setValue(newValue);
    pbar.setSize(150, 15);
  }
@Test
  public void bar() {
	  try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	          catch (Exception e) {}


    final Progress_Bar it = new Progress_Bar();

     frame = new JFrame("Connecting to Database....");
   
    
    frame.setBounds(600, 360, 280, 80);
    JOptionPane.showMessageDialog(null, "Trying to connect....","Message", JOptionPane.INFORMATION_MESSAGE);
    frame.setContentPane(it);
    JLabel label = new JLabel("Connect ho raha hai..please have patience");
    frame.add(label);
    
    label.setFont(new Font("Tahoma", Font.BOLD, 10));
    frame.setVisible(true);
    //frame.setResizable(false);
    

    for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) 
    {
      final int percent = i;
      try 
      {
        SwingUtilities.invokeLater(new Runnable() {public void run(){it.updateBar(percent);}});
        java.lang.Thread.sleep(100);
      } 
      catch (InterruptedException e) 
      {
    	  ;
      }
    }
    frame.setVisible(false);
  }
}
