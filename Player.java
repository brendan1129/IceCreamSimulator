import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class Player extends Thing {
 
   enum CursorType {
      MOUSE,
      KEYS
   }
   
     
   GamePanel gp;
   InputController keys;
   ImageIcon im;
   Color scoopColor;
   Toolkit tk;
   CursorType cType;

   public boolean clicking = false;
   public boolean checking = false;
   public boolean trashing = false;
   public boolean playing = false;
   public boolean exiting = false;
   public boolean learning = false;
   public boolean retrying = false;
  
   public boolean isColliding = false;
   public boolean isRetrying = false;
   public boolean isLearning = false;
   public boolean isTrashing = false;
   public boolean isChecking = false;
   public boolean isPlaying = false;
   public boolean isExiting = false;
   
   public Player(GamePanel gamepanel, InputController controller, ImageIcon image) {
   
      this.gp = gamepanel;
      this.keys = controller;
      this.im = image;
      this.cType = CursorType.KEYS;
      spawn();
   }
   
   public void spawn() {
      this.x = gp.tileSize * 8;
      this.y = gp.tileSize * 8;
      this.speed = 5;
   }
   public void update() {
      switch(cType) {
         case KEYS:
         if(keys.downPressed == true) {
            this.y += this.speed;
         }
         if(keys.upPressed == true) {
            this.y -= this.speed;
         }
         if(keys.rightPressed == true) {
            this.x += this.speed;
         }
         if(keys.leftPressed == true) {
            this.x -= this.speed;
         }
         if(keys.clicked == true && isColliding ) {
            clicking = true;
         }
         else if(keys.clicked == true && isTrashing ) {
            //System.out.println( "Trashing" );
            trashing = true;
         }
         else if(keys.clicked == true && isChecking ) {
            //System.out.println( "Checking" );
            checking = true;
         }
         else if(keys.clicked == true && isPlaying ) {
            playing = true;
         }
         else if(keys.clicked == true && isLearning ) {
            learning = true;
         }
         else if(keys.clicked == true && isExiting ) {
            exiting = true;
         }
         else if(keys.clicked == true && isRetrying) {
            retrying = true;
         }
         else {
            playing = false;
            checking = false;
            trashing = false;
            clicking = false;
            exiting = false;
            learning = false;
            retrying = false;
         }
         break;
         default:
         break;
      }
   }
   public void switchCursors() {
      if( this.cType == CursorType.KEYS ) {          
         tk = Toolkit.getDefaultToolkit();
         Cursor c = tk.createCustomCursor(im.getImage(), new Point(0, 0), "");
         gp.setCursor(c);
         this.cType = CursorType.MOUSE;
      }
   }
   public void toKeys() {
      this.cType = CursorType.KEYS;
      gp.setCursor(Cursor.getDefaultCursor());
   }
   public void draw(Graphics g) { // Only do this after spawning
      im.paintIcon(this.gp, g, this.x, this.y);
   }
   public void detectCollision(Button b1, Button b2, Button b3) {
      // If players x or y when subtracted by an objects x or y is still a number between 0 and the objects size, then it is colliding
      if( (this.x - b1.x >= 0 && this.x - b1.x <= 50 && this.y - b1.y >= 0 && this.y - b1.y <= 50) && !keys.clicked) {
         isColliding = true;
         scoopColor = b1.c;
      }
      else if ( (this.x - b2.x >= 0 && this.x - b2.x <= 50 && this.y - b2.y >= 0 && this.y - b2.y <= 50) && !keys.clicked) {
         isColliding = true;
         scoopColor = b2.c;
      }
      else if ( (this.x - b3.x >= 0 && this.x - b3.x <= 50 && this.y - b3.y >= 0 && this.y - b3.y <= 50) && !keys.clicked) {
         isColliding = true;
         scoopColor = b3.c;
      }
      else {
         isColliding = false;
      }
   }
   public void detectTrash(Button b1) {
      if( (this.x - b1.x >= 0 && this.x - b1.x <= 50 && this.y - b1.y >= 0 && this.y - b1.y <= 50) && !keys.clicked) {
         isTrashing = true;
      }
      else {
         isTrashing = false;
      }
   }
   public void detectRetry(Button b1) {
      if( (this.x - b1.x >= 0 && this.x - b1.x <= 40 && this.y - b1.y >= 0 && this.y - b1.y <= 40) && !keys.clicked) {
         isRetrying = true;
         b1.im = b1.litIm;
      }
      else {
         isRetrying = false;
         b1.im = b1.unlitIm;
      }
   }
   public void detectCheck(Button b1) {
      if( (this.x - b1.x >= 0 && this.x - b1.x <= 50 && this.y - b1.y >= 0 && this.y - b1.y <= 50) && !keys.clicked) {
         isChecking = true;
      }
      else {
         isChecking = false;
      }
   }
   public void detectPlay(Button b1) {
      if( (this.x - b1.x >= 0 && this.x - b1.x <= 40 && this.y - b1.y >= 0 && this.y - b1.y <= 40) && !keys.clicked ) {
         isPlaying = true;
         b1.im = b1.litIm;
      }
      else {
         isPlaying = false;
         b1.im = b1.unlitIm;
      }
   }
   public void detectExit(Button b1) {
      if( ((this.x - b1.x >= 0 && this.x - b1.x <= 40 && this.y - b1.y >= 0 && this.y - b1.y <= 40) && !keys.clicked) || b1.isClicked ) {
         isExiting = true;
         b1.im = b1.litIm;
      }
      else {
         isExiting = false;
         b1.im = b1.unlitIm;
      }
   }
   public void detectInfo(Button b1) {
      if( ((this.x - b1.x >= 0 && this.x - b1.x <= 40 && this.y - b1.y >= 0 && this.y - b1.y <= 40) && !keys.clicked) || b1.isClicked ) {
         isLearning = true;
         b1.im = b1.litIm;
      }
      else {
         isLearning = false;
         b1.im = b1.unlitIm;
      }
   }
}