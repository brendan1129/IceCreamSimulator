import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

   enum GameState {
         START,
         PLAYING,
         END
   }
   
   // Screen Settings
   final int pixelSize = 16; // 16 Pixels per Tile
   final int scale = 3; // Scale up 3X
   
   final int tileSize = pixelSize * scale; // 3 * 16 = 48 pixels per tile
   final int maxVSize = 10; // 48 * 16 = 768px tall
   final int maxHSize = 15; //48 * 20 = 960px wide
   final int screenHeight = tileSize * maxVSize;
   final int screenWidth = tileSize * maxHSize;
   
   int FPS = 60; // frames per second
   
   InputController keys = new InputController(); // Custom event handler for input of keys
   
   Thread gameThread; // For running off system.time
   
   ImageIcon cursor = new ImageIcon("resources/Ice Cream/Ice Cream-2.png");
   
   ImageIcon button1 = new ImageIcon("resources/Ice Cream/sprite_02.png");
   
   ImageIcon button2 = new ImageIcon("resources/Ice Cream/sprite_03.png");
   
   ImageIcon button3 = new ImageIcon("resources/Ice Cream/sprite_04.png");
   
   ImageIcon button4 = new ImageIcon("resources/Ice Cream/sprite_32.png");
   
   ImageIcon button5 = new ImageIcon("resources/Ice Cream/check.png");
   
   ImageIcon title = new ImageIcon("resources/Ice Cream/logo.png");
   
   ImageIcon playB = new ImageIcon("resources/Ice Cream/play.png");
   
   ImageIcon playBLit = new ImageIcon("resources/Ice Cream/playLit.png");
   
   ImageIcon switcher = new ImageIcon("resources/Ice Cream/changer.png");
   
   ImageIcon info = new ImageIcon("resources/Ice Cream/instructions.png");
   
   ImageIcon infoLit = new ImageIcon("resources/Ice Cream/instructionsLit.png");
   
   ImageIcon retry = new ImageIcon("resources/Ice Cream/retry.png");
   
   ImageIcon retryLit = new ImageIcon("resources/Ice Cream/retryLit.png");
   
   ImageIcon exit = new ImageIcon("resources/Ice Cream/exit.png");
   
   ImageIcon exitLit = new ImageIcon("resources/Ice Cream/exitLit.png");
   
   Sprite tS = new Sprite(this, title, 202, tileSize * 2);
   
   // Mostly custom classes from here on out
      
   Button b1 = new Button(this, button2, Color.BLUE, 5, 8);
   
   Button b2 = new Button(this, button1, Color.RED, 7, 8);
   
   Button b3 = new Button(this, button3, Color.YELLOW, 9, 8);
   
   Button clear = new Button(this, button4, Color.RED, 11, 8);
   
   Button check = new Button(this, button5, Color.RED, 3, 8);
   
   Button play = new Button(this, playB, playBLit, Color.BLUE, 7, 7);
   
   Button cSwitch = new Button(this, switcher, Color.BLUE, 5, 7);
   
   Button infoB = new Button(this, info, infoLit, Color.BLUE, 9, 7);
   
   Button retryB = new Button(this, retry, retryLit, Color.BLUE, 7, 7);
   
   Button exitB = new Button(this, exit, exitLit, Color.BLUE, 5, 7);
   
   Player player = new Player(this, keys, cursor);
  
   SpriteMap spriteMap = new SpriteMap(this);
   
   IceCream ice = new IceCream(this, new ImageIcon("resources/Ice Cream/sprite_05.png"), tileSize * 7, tileSize * 5);
   
   Random r = new Random();
   
   // This is about when I learned to reference the tile size in my custom classes constructors 
   Cloud cloud1 = new Cloud(this, 1, 5, 1, r.nextInt(3) );
   Cloud cloud2 = new Cloud(this, 2, 0, 1, r.nextInt(3) );
   Cloud cloud3 = new Cloud(this, 2, 12, 1, r.nextInt(3) );
   Cloud cloud4 = new Cloud(this, 1, 8, 1, r.nextInt(3) );
   Cloud cloud5 = new Cloud(this, 1, 6, 1, r.nextInt(3) );
   
   Cloud c1 = new Cloud(this, 1, 5, 1, r.nextInt(9));
   Cloud c2 = new Cloud(this, 2, 0, 1, r.nextInt(9));
   Cloud c3 = new Cloud(this, 2, 12, 1, r.nextInt(9));
   Cloud c4 = new Cloud(this, 3, 8, 1, r.nextInt(9));
   Cloud c5 = new Cloud(this, 4, 9, 1, r.nextInt(9) );
   
   // This is a bird that acts like a cloud... but all clouds above can also become birds.. 
   // don't worry about it
   
   Cloud bird = new Cloud(this, 4, 9, 1, 2);
   
   Kid kid = new Kid(this, 0, 4, r.nextInt(3));
   
   Timer t = new Timer(this, false, 1, 7, 7, 11);
 
   Timer t0 = new Timer(this, true, 0, 13, 1, 60);
      
   Score s = new Score(this, 75, 1);
   
   Score fScore;
   
   // These next two objects are a doozy so I am sectioning them off //
   
   Score hScore;
   
   HighScoreLoader hsL;
   
   // ---------- //
   
  
   InfoPanel ip = new InfoPanel(this, 2, 1);
               
   GameState state; // le state of le game, declaration at the top
   
   public Color bgC; // backGround color
   
   public GamePanel() {
      this.state = GameState.START;
      this.bgC = new Color(24, 164, 232);
      this.setPreferredSize(new Dimension(screenWidth, screenHeight));
      this.setBackground(bgC);
      this.addKeyListener(keys);
      this.setFocusable(true);
   }
   
   public void StartGameThread() {
      gameThread = new Thread(this);
      gameThread.start();
   }
   @Override
   public void run() {
      //TODO: Run thread ---~~------~~ this is the game loop
         double interval = 1000000000/FPS; // 0.01666 seconds or 60 frames per second
         double nextDraw = System.nanoTime() + interval;
         
      while ( gameThread != null ) {
        
         update();
         repaint();
         
         try {       
           
      // Game loop for 60 FPS
            double remainingTime = nextDraw - System.nanoTime();
            remainingTime = remainingTime/1000000;
            if ( remainingTime < 0 ) {
               remainingTime = 0;
            }
            Thread.sleep((long)remainingTime);
            nextDraw += interval;
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
   
   boolean saving = true;
   public void update() {
   player.update();
   
      switch(state) {
         case START:
         
            this.setBackground(bgC);
            c1.move(1);
            c2.move(1);
            c3.move(1);
            c4.move(1);
            c5.move(1);
            //player.detectSwitch(cSwitch);
            player.detectPlay(play);
            player.detectInfo(infoB);
            player.detectExit(exitB);
            if(player.learning){
               ip.toggle();
            }
            if(player.playing) {
               state = GameState.PLAYING;
            }
            if(player.exiting) {
               System.exit(0);
            }
            s.pScore = 0;
         break;
         
         case PLAYING:
         
            this.setBackground(bgC);
   
            player.detectCollision(b1, b2, b3);
            if(player.clicking ) {
               ice.addScoop(player.scoopColor);
            }
            
            ice.update();
      
            
            player.detectTrash(clear);
            
            if(player.trashing) {
               ice.clear();
            }
            
            kid.update();
            if(kid.isOrdering()) {
               s.isListening = true;
               t.reset();
            }
            if(kid.stat == Kid.Status.WAITING) {
               t.isOn = true;
            }
            if( t.isOn && kid.stat == Kid.Status.WAITING ) {
               t.updateT1();
            }
            
            kid.detectTimer(t);
            s.checkScore(t, kid);
            player.detectCheck(check);
            if(player.checking) {
               if( kid.isOK(ice.scoops, kid.order) && kid.stat == Kid.Status.WAITING ) {
                  System.out.println("Correct");
                  kid.stat = Kid.Status.APPROVED;
                  t.isOn = false;
                  t.isUpdating = false;
                  ice.clear();
                  s.isListening = true;
               }
               else {
                  System.out.println("Incorrect");
               }
            }
            s.checkScore(t, kid);
      
            cloud1.move(0);
            cloud2.move(0);
            cloud3.move(0);
            cloud4.move(0);
            cloud5.move(0);
            bird.move(0);
            t0.updateT1();
            if( t0.labelText == 0 ) {
               if( kid.stat == Kid.Status.APPROVED || kid.stat == Kid.Status.DENIED ) {
                  this.state = GameState.END;
               }
               if( t.labelText != 0 ) {
                  break;
               }
               
               this.state = GameState.END;
            }
            
         break;
         case END:
            t0.labelText = 60;
            this.setBackground(Color.LIGHT_GRAY);
            player.detectRetry(retryB);
            player.detectExit(exitB);
            if( player.exiting ) {
               System.exit(0);
            }
            if( player.retrying ) {
               this.state = GameState.START;
            }
         break;
         default:
            this.setBackground(Color.LIGHT_GRAY);
         break;
      }
   }
   public void paintComponent(Graphics g) {
   
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g; // This is from the tutorial I followed and I don't use it
      
      switch(state) {
         case START:
         
            spriteMap.drawStart(g);
            c1.draw(g);
            c2.draw(g);
            tS.draw(g);
            c3.draw(g);
            c4.draw(g);
            c5.draw(g);
            play.draw(g);
            //cSwitch.draw(g);
            exitB.draw(g);
            infoB.draw(g);
            ip.draw(g);
            if(player.cType == Player.CursorType.KEYS) {
               player.draw(g);
            }
            
         break;
         case PLAYING:
         
            kid.draw(g);
            spriteMap.draw(g);
            cloud1.draw(g);
            cloud2.draw(g);
            cloud3.draw(g);
            cloud4.draw(g);
            cloud5.draw(g);
            bird.draw(g);
            clear.draw(g);
            check.draw(g);
            b1.draw(g);
            b2.draw(g);
            b3.draw(g);
            if (t.isUpdating) {
               t.drawT1(g);
            }
            s.draw(g);
            ice.draw(g);
            player.draw(g);
            if (kid.isPrinting) {
               kid.drawOrder(g);
            }
            if (t0.isUpdating) {
               t0.drawT0(g);
            }
            
         break;
         case END:
         
            fScore = new Score(this, 7, 5, s.pScore);
            
            fScore.drawEndScore(g);
            /*if(saving) {
               String score = hsL.loadScore();
               hScore.drawHighScore(g, score);
               saving = false;
            }*/
            retryB.draw(g);
            exitB.draw(g);
            if(player.cType == Player.CursorType.KEYS) {
               player.draw(g);
            }
            
         break;
         default:
         break;
         
      }
   }
}