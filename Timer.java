import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
public class Timer extends Thing {

   GamePanel gp;
   int interval;
   int timer;
   int labelText;
   int fontType;
   boolean isOn;
   boolean isUpdating;
   boolean timersOn;
   File fFile;
   Font font;
   
   public Timer( GamePanel g, boolean on, int fType, int xPos, int yPos, int time ) {
      isOn = on;
      this.gp = g;
      this.fontType = fType;
      if(fType == 0) {
      this.x = gp.tileSize * xPos;
      this.y = gp.tileSize * yPos;
      }
      else {
      this.x = gp.tileSize * xPos + 18;
      this.y = gp.tileSize * yPos - 11;
      }
      interval = 60;
      timer = 0;
      labelText = time;

      try {
         font = Font.createFont(Font.TRUETYPE_FONT,new File("munro.ttf")).deriveFont(40f);
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
   public void updateT1() {
      if(isOn) {
        timer++; 
           if( timer >= interval ) {
                  if ( labelText > 0 ) {
                     labelText--;
                     isUpdating = true;
                  }
                  else {
                     isOn = false;
                     isUpdating = false;
                  }
                  timer = 0;
               }
      }
   }
   public void drawT1(Graphics g) {
         if(fontType == 1 && isUpdating && labelText > 0 ) {
            g.setFont(font);
            float re = 0.1f;
            float gr = 0.9f;
            float bl = 0.1f;
            g.setColor(new Color(re,gr,bl));
            g.drawString(String.valueOf(labelText), this.x, this.y);
         }
         else {
            isUpdating = false;
            isOn = false;
         }
   }
   public void drawT0(Graphics g){

         if (fontType == 0 && isUpdating && labelText > 0) {
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(labelText), this.x, this.y);
         }
         else {
            isUpdating = false;
            isOn = false;
         }
   }
   public void reset() {
      if(fontType == 0) {
         labelText = 0;
         isOn = false;
         isUpdating = false;
      }
      else if(fontType == 1) {
         labelText = 10;
         isOn = false;
         isUpdating = false;
      }
   }
   public void drawOff(Graphics g) {
         g.setColor(gp.bgC);
         g.fillRect(this.x - 20, this.y - 20, gp.tileSize, gp.tileSize);
   }
}