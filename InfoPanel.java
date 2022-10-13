import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
public class InfoPanel extends Thing {
   GamePanel gp;
   Font font;
   boolean isOn;
   String text = "WELCOME TO THE ICE CREAM SIMULATOR!";
   String text2 = "THE OBJECTIVE OF THE GAME IS SIMPLE: YOU’RE AN ICE CREAM MAN.";
   String text3 = "TODAY, THE SUN IS BEAMING LIKE NEVER BEFORE. ";
   String text4 = "SERVE THESE KIDS BEFORE THEY GET IMPATIENT AND LEAVE";
   String text5 = "( YOU KNOW HOW KIDS CAN BE... )";
   String text6 = "WASD OR KEYS TO MOVE. SPACE BAR TO CLICK.";
   String text7 = "THE QUESTION MARK WILL TELL YOU IF YOU’RE CORRECT.";
   String text8 = "THE TRASH CAN BE USED TO RESET THE ICE CREAM CONE.";
   String text9 = "GOOD LUCK!";
   
   public InfoPanel(GamePanel gp, int xPos, int yPos) {
      this.gp = gp;
      this.x = xPos * this.gp.tileSize;
      this.y = yPos * this.gp.tileSize;
      isOn = false;
      try {
         font = Font.createFont(Font.TRUETYPE_FONT,new File("munro.ttf")).deriveFont(20f);
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
         ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("munro.ttf")));
      }
      catch ( FontFormatException e ) {
         e.printStackTrace();
      }
      catch ( IOException e ) {
         e.printStackTrace();
      }
   }
   public void toggle() {
      if(isOn) isOn = false;
      else isOn = true;
   }
   public void draw(Graphics g) {
      if(isOn) {
         g.setColor(Color.LIGHT_GRAY);
         g.fill3DRect(this.x, this.y, 520, 250, true);
         g.setColor(Color.BLACK);
         g.setFont(font);
         g.drawString(text, this.x + 106, this.y + 24);
         g.drawString(text2, this.x + 16, this.y + gp.tileSize);
         g.drawString(text3, this.x + 74, this.y + gp.tileSize + 24);
         g.drawString(text4, this.x + 42, this.y + gp.tileSize * 2);
         g.drawString(text5, this.x + 130, this.y + (gp.tileSize * 2) + 24);
         
         g.drawString(text6, this.x + 92, this.y + (gp.tileSize * 3) + 24);
         g.drawString(text7, this.x + 56, this.y + gp.tileSize * 4);
         g.drawString(text8, this.x + 58, this.y + (gp.tileSize * 4) + 24);
         g.setColor(new Color(255,233,0));
         g.drawString(text9, this.x + 220, this.y + gp.tileSize * 5);
      }
   }
}