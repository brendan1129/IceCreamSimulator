// Brendan Hannum's Game Engine
// Using the Java Swing package
import javax.swing.JFrame;

public class Main {

   public static void main(String[] pussyclat) {

      
      JFrame frame = new JFrame();
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.setTitle("Ice Cream Sim");
      GamePanel panel = new GamePanel();
      
      frame.add(panel);
      
      frame.pack();
      
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      
      panel.StartGameThread();
   }
}