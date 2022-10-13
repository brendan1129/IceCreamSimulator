import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Score extends Thing {

   GamePanel gp;
   int pScore;
   int interval;
   boolean isListening;
   
   Font font;
   
   public Score( GamePanel g, int xPos, int yPos ) {
      isListening = false;
      pScore = 0;
      this.gp = g;
      this.x = xPos;
      this.y = yPos * g.tileSize;
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
   public Score( GamePanel g, int xPos, int yPos, int s ) {
      this.pScore = s;
      this.gp = g;
      this.x = xPos * g.tileSize;
      this.y = yPos * g.tileSize;
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
   public void checkScore(Timer t, Kid k) {
      if( k.stat == Kid.Status.APPROVED && t.labelText > 0 && isListening ) {
         pScore += t.labelText + k.orderSize;
         isListening = false;
      }
      else if ( t.labelText == 0 && isListening ) {
         pScore -= k.orderSize;
         isListening = false;
      }
   }
   public void draw(Graphics g) {
      g.setFont(font);
      g.setColor(Color.BLACK);
      g.drawString(String.valueOf(pScore), this.x, this.y);
   }
   public void drawEndScore(Graphics g) {
      g.setFont(font);
      g.setColor(Color.BLACK);
      g.drawString("SCORE: ", this.x - 22, this.y - gp.tileSize );
      g.drawString(String.valueOf(pScore), this.x + 15, this.y);
   }
   public void drawHighScore(Graphics g, String stringScore) {
      g.setFont(font);
      g.setColor(Color.BLACK);
      g.drawString("HIGH SCORE: ", this.x - 53, this.y - gp.tileSize );
      g.drawString("HIGH SCORE: ", this.x - 57, this.y - gp.tileSize );
      g.drawString("HIGH SCORE: ", this.x - 54, (this.y - gp.tileSize) + 2 );
      g.drawString("HIGH SCORE: ", this.x - 54, (this.y - gp.tileSize) - 2 );
      g.setColor(new Color(255,233,0));
      g.drawString("HIGH SCORE: ", this.x - 55, this.y - gp.tileSize );
      g.setColor(Color.BLACK);
      g.drawString(stringScore, this.x + 15, this.y);

   }
}