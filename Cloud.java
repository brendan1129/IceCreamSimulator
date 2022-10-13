import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.Graphics;

public class Cloud extends Thing {

   ImageIcon cloud1, cloud2, cloud3, cloud4, bird;
   Sprite spr;
   GamePanel panel;
   public int cloudCount = 0; // For incrementing count in main update function;
   Random random = new Random();
   
   public Cloud(GamePanel gp, int cloudType, int xPos, int spd, int yLevel) {
      loadSprites();
      this.panel = gp;
      this.speed = spd;
      this.y = gp.tileSize * yLevel;
      this.x = gp.tileSize * xPos + (gp.tileSize/3);
      switch(cloudType) { 
            case 0:
               spr = new Sprite(this.panel, cloud1, this.x, this.y );
            break;
            case 1:
               spr = new Sprite(this.panel, cloud2, this.x, this.y );   
            break;
            case 2:
               spr = new Sprite(this.panel, cloud3, this.x, this.y );
            break;
            case 3:
               spr = new Sprite(this.panel, cloud4, this.x, this.y );   
            break;
            case 4:
               spr = new Sprite(this.panel, bird, this.x, this.y );
            break;
            default:
               System.out.println("Error with cloudType");
            break;
       }
   }
   public void loadSprites(){
      cloud1 = new ImageIcon("resources/Ice Cream/cloud1.png");
      cloud2 = new ImageIcon("resources/Ice Cream/cloud2.png");
      cloud3 = new ImageIcon("resources/Ice Cream/cloud3.png");
      cloud4 = new ImageIcon("resources/Ice Cream/cloud4.png");
      bird = new ImageIcon("resources/Ice Cream/bird.gif");
      }
   public void move(int type) {
      this.spr.x += this.speed;
      if(this.spr.x > panel.screenWidth ) {
         
         this.y = panel.tileSize * random.nextInt(3);
         int rNum;
         if( type == 1 ) {
            rNum = random.nextInt(10);
         }
         else {
            rNum = random.nextInt(3);
         }
         this.spr.y = rNum * panel.tileSize;
         this.spr.x = 0;
         rNum = random.nextInt(5);
         switch(rNum) { 
            case 0:
               this.spr.icon = cloud1;
            break;
            case 1:
               this.spr.icon = cloud2;
            break;
            case 2:
               this.spr.icon = cloud3;
            break;
            case 3:
               this.spr.icon = cloud4;
            break;
            case 4:
               this.spr.icon = bird;
            default:
            break;
         }
      }
   }
   public void draw(Graphics g) {
      this.spr.draw(g);
   }
}