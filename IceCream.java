import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
public class IceCream extends Thing {

   public ArrayList<Scoop> scoops = new ArrayList<Scoop>();
   ImageIcon i;
   GamePanel gp;
   public int drawPos;
   public int interval = 40;
   
   ImageIcon redScoop = new ImageIcon("resources/Ice Cream/sprite_08.png");
   ImageIcon blueScoop = new ImageIcon("resources/Ice Cream/sprite_07.png");
   ImageIcon yellowScoop = new ImageIcon("resources/Ice Cream/sprite_06.png");
   
   public IceCream(GamePanel panel, ImageIcon icon, int xPos, int yPos) {
      scoops.clear();
      this.gp = panel;
      this.i = icon;
      this.x = xPos;
      this.y = yPos;
      drawPos = this.y - interval;
   }
   
   public void addScoop(Color c) {
   if(scoops.size() < 6 ) {
         if ( c == Color.RED ) {
            Scoop scoo = new Scoop(c, gp, redScoop, this.x, 0, drawPos);
            scoops.add(scoo);
            drawPos -= interval;
         }
         if ( c == Color.BLUE ) {
            Scoop scoo = new Scoop(c, gp, blueScoop, this.x, 0, drawPos);
            scoops.add(scoo);
            drawPos -= interval;
         }
         if ( c == Color.YELLOW ) {
            Scoop scoo = new Scoop(c, gp, yellowScoop, this.x, 0, drawPos);
            scoops.add(scoo);
            drawPos -= interval;
         }
      }
   else {
         System.out.println("Too many scoops you ass hat");
      }
   }
   public void update() {
      if (!scoops.isEmpty()) {
         for(int i = 0; i < scoops.size(); i++ ) {
            scoops.get(i).update();
         }
      }
   }
   public void plop(Scoop s, int dPos) {
      while ( s.y < dPos ) {
         s.y -= s.speed;
      }
   }
   public void draw(Graphics g) {
      i.paintIcon(gp, g, x, y);
      for( int i = 0; i < scoops.size(); i++ ) {
         scoops.get(i).draw(g);
      }
   }
   public void clear() {
      scoops.clear();
      drawPos = this.y - interval;
   }
}
