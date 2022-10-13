import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputController implements KeyListener { // For managing inputs

   public boolean downPressed, upPressed, leftPressed, rightPressed;
   
   public boolean clicked;

   public void keyTyped(KeyEvent e) {
   
   }
   public void keyPressed(KeyEvent e) {
   
      int key = e.getKeyCode();
      
      if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) { // Down
         downPressed = true;
      }
      if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { // Up
         upPressed = true;
      }
      if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) { // Left
         leftPressed = true;
      }
      if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) { // Right
         rightPressed = true;
      }
      if(key == KeyEvent.VK_SPACE ) {
         clicked = true;
      }
   }
   public void keyReleased(KeyEvent e) {
      
      int key = e.getKeyCode();
      
      if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) { // Down
         downPressed = false;
      }
      if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { // Up
         upPressed = false;
      }
      if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) { // Left
         leftPressed = false;
      }
      if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) { // Right
         rightPressed = false;
      }
      if(key == KeyEvent.VK_SPACE) {
         clicked = false;
      }
   }
}