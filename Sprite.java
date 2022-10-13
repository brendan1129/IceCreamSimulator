import javax.swing.ImageIcon;
import java.awt.Graphics;
public class Sprite extends Thing {

   ImageIcon icon;
   GamePanel panel;
   AlphaImageIcon aIcon;
   
   public Sprite(GamePanel p, ImageIcon i, int xPos, int yPos) {
      this.icon = i;
      this.x = xPos;
      this.y = yPos;
      this.panel = p;
   }
   public Sprite(GamePanel p, AlphaImageIcon ai, int xPos, int yPos) {
      this.aIcon = ai;
      this.x = xPos;
      this.y = yPos;
      this.panel = p;
   }
   public void draw(Graphics g) {
      icon.paintIcon(panel, g, this.x, this.y);
   }
   public void drawAlpha(Graphics g) {
      aIcon.paintIcon(panel, g, this.x, this.y);
   }
}