import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;


public class Kid extends Thing {

   public enum Status {
      GREETING,
      ORDERING,
      WAITING,
      APPROVED,
      DENIED,
      DEAD
   }
   
   GamePanel gp;
   Sprite spr;
   ImageIcon kid1, kid2, kid3;
   ImageIcon kid1S, kid2S, kid3S;
   ImageIcon kid1H, kid2H, kid3H; // For when they're happy and have full tummies of ice cream
   Status stat;
   Color[] order;
   int orderSize;
   int initX, initY;
   Random r;
   
   public Kid(GamePanel p, int xPos, int yPos, int kidType) {   
      this.gp = p;
      this.x = xPos * gp.tileSize;
      initX = xPos * gp.tileSize;
      this.y = yPos * gp.tileSize;  
      initY = yPos * gp.tileSize;
      this.speed = 2;
      this.stat = Status.GREETING;
      loadSprites();
      if(kidType == 0){
      spr = new Sprite(this.gp, kid1, this.x, this.y);
      }
      if(kidType == 1){
      spr = new Sprite(this.gp, kid2, this.x, this.y);
      }
      if(kidType == 2){
      spr = new Sprite(this.gp, kid3, this.x, this.y);
      }
      order = new Color[0];
   }
   
   public void update() {
      switch(stat) {
         case GREETING:
            greet(6.98f);
         break;
         case ORDERING:
            makeDecision();
            //printDecision();
            isPrinting = true;
            stat = Status.WAITING;
         break;
         case WAITING:
         break;
         case APPROVED:
            isPrinting = false;
            if(spr.icon == kid1) spr.icon = kid1H;
            if(spr.icon == kid2) spr.icon = kid2H;
            if(spr.icon == kid3) spr.icon = kid3H;
            sayBye();
         break;
         case DENIED:
            isPrinting = false;
            sayBye();
         break;
         case DEAD:
            resetKid();
         break;
         default:
         break;
      }
   }
   // Need random generator for type of kid
   // Need some kind of method for randomly deciding their order
   // Possibly have a random decision for size of array, then allocate array, and iterate through placing a random color
   // Then have a method for drawing that order to the GamePanel somehow
   public void makeDecision() {
      r = new Random();
      orderSize = r.nextInt(6);
      if(orderSize <= 1) {
         orderSize += 2;
      }
      order = new Color[orderSize];
      for(int i = 0; i < orderSize; i++) {
         int decision = r.nextInt(3);
         switch(decision) {
            case 0:
               order[i] = Color.RED;
            break;
            case 1:
               order[i] = Color.YELLOW;
            break;
            case 2:
               order[i] = Color.BLUE;
            break;
            default:
            break;
         }
      }
   }
   public void printDecision(){
      for(int i = 0; i < orderSize; i++) {
         if(order[i] == Color.BLUE) { System.out.println("Blue scoop"); }
         if(order[i] == Color.YELLOW) { System.out.println("Yellow scoop"); }
         if(order[i] == Color.RED) { System.out.println("Red scoop"); }
      }
   }
   public void resetKid() {
      this.x = initX;
      this.y = initY;
      r = new Random();
      int newKid = r.nextInt(3);
      switch(newKid) {
         case 0:
           this.spr = new Sprite(this.gp, kid1, this.x, this.y);
         break;
         case 1:
           this.spr = new Sprite(this.gp, kid2, this.x, this.y);
         break;
         case 2:
           this.spr = new Sprite(this.gp, kid3, this.x, this.y);
         default:
         break;
      }

      this.stat = Status.GREETING;
   }
   public void greet(float x) {
      if( this.x <= (float)gp.tileSize * x ) {
         spr.x += this.speed;
         this.x += this.speed;
      }
      else {
         stat = Status.ORDERING;
      }
   }
   public void detectTimer(Timer t) {
      if(t.labelText == 0 && this.stat == Status.WAITING) {
         if(spr.icon == kid1) spr.icon = kid1S;
         if(spr.icon == kid2) spr.icon = kid2S;
         if(spr.icon == kid3) spr.icon = kid3S;
         this.stat = Status.DENIED;
         sayBye();
      }
   }
   public void sayBye() {
      if( this.x <= gp.screenWidth ) {
         spr.x += this.speed;
         this.x += this.speed;
      }
      else {
         stat = Status.DEAD;
      }
   }
   public void draw( Graphics g ) {
      spr.draw(g);
   }
   public void loadSprites() {
      kid1 = new ImageIcon("resources/Ice Cream/kid1.png");
      kid1S = new ImageIcon("resources/Ice Cream/kid1S.png");
      kid1H = new ImageIcon("resources/Ice Cream/kid1H.png");
      kid2 = new ImageIcon("resources/Ice Cream/kid2.png");
      kid2S = new ImageIcon("resources/Ice Cream/kid2S.png");
      kid2H = new ImageIcon("resources/Ice Cream/kid2H.png");
      kid3 = new ImageIcon("resources/Ice Cream/kid3.png");
      kid3S = new ImageIcon("resources/Ice Cream/kid3S.png");
      kid3H = new ImageIcon("resources/Ice Cream/kid3H.png");
      
   }
   public boolean isOK(ArrayList<Scoop> whatWeHave, Color[] whatTheyWant) {
      if( whatWeHave.size() == 0 || whatTheyWant.length == 0 ) {
         System.out.println("Wrong");
         return false;
      }
      else {
         for( int i = 0; i < whatTheyWant.length; i++ ) {
            if( whatWeHave.size() != whatTheyWant.length ) 
            { 
               System.out.println( "Wrong" ); 
               return false;
            }
            if( whatWeHave.get(i).c != whatTheyWant[i] ) {
               System.out.println("Wrong");
               return false;
            }
         }
         return true;
      }
   }
   public boolean isPrinting = false;
   public int count = 0;
   public int interval = 60;
   public boolean isOrdering() {
      return this.stat == Status.ORDERING;
   }
   public void drawOrder(Graphics g) {
      int drawInt = this.x + 64;
      int i = 0;
      g.setColor(Color.DARK_GRAY);
      g.fillOval(drawInt-3, this.y + 55, 31, 31);
      g.setColor(order[i]);
      g.fillOval(drawInt, this.y + 58, 25, 25 );
      drawInt += 48;
      i++;
      while( i < orderSize ) {
         if( count < interval ) {
            count++;
         }
         else {
            count = 0;
            g.setColor(Color.DARK_GRAY);
            g.fillOval(drawInt-3, this.y + 55, 31, 31);
            g.setColor(order[i]);
            g.fillOval(drawInt, this.y + 58, 25, 25);
            drawInt += 48;
            i++;
         }
      }
      
   }
}