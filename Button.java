import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class Button extends Thing implements MouseListener {
   
   GamePanel gp;
   ImageIcon im;
   ImageIcon litIm;
   ImageIcon unlitIm;
   public boolean isClicked;
   Color c;
   
   public Button(GamePanel panel, ImageIcon image, Color color, int xPos, int yPos) {
   
      this.gp = panel;
      this.im = image;
      this.c = color;
      this.x = xPos * gp.tileSize;
      this.y = yPos * gp.tileSize;
      isClicked = false;
   }
   public Button(GamePanel panel, ImageIcon image, ImageIcon clickableImage, Color color, int xPos, int yPos) {
      this.gp = panel;
      this.im = image;
      this.litIm = clickableImage;
      this.unlitIm = image;
      this.c = color;
      this.x = xPos * gp.tileSize;
      this.y = yPos * gp.tileSize;
      isClicked = false;
   }
   public void mouseClicked(MouseEvent e) {
   }
   public void mousePressed(MouseEvent e) {
      /* IN PROGRESS */
      isClicked = true;
      System.out.println("hello");
   }
   public void mouseReleased(MouseEvent e) {
      isClicked = false;
   }
   public void mouseExited(MouseEvent e) {
   }
   public void mouseEntered(MouseEvent e) {
   }
   public void deactivate() {
      // TODO: Change the presentation of the button if it is deactivated
   }
   public void draw(Graphics g) {
      im.paintIcon(this.gp, g, this.x, this.y);
   }
}