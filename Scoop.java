import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Scoop extends Thing {
   public Color c;
   public ImageIcon im;
   GamePanel gp;
   int dP;
   
   public Scoop(Color color, GamePanel panel, ImageIcon image, int xPos, int yPos, int drawPos) {
      this.gp = panel;
      this.im = image;
      this.c = color;
      this.x = xPos;
      this.y = yPos;
      this.dP = drawPos;
      this.speed = 5;
   }
   public void draw( Graphics g ) {
      im.paintIcon(gp, g, x, y);
   }
   public void update() {
      if(this.y < dP) {
         this.y += this.speed;
      }
   }
}