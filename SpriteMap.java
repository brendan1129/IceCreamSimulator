import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class SpriteMap {
   GamePanel gp;
   // One arraylist for each row of sprites because I don't know how else to do it
   ArrayList<Sprite> sprite1 = new ArrayList<Sprite>();
   ArrayList<Sprite> sprite2 = new ArrayList<Sprite>();
   ArrayList<Sprite> sprite3 = new ArrayList<Sprite>();
   ArrayList<Sprite> sprite4 = new ArrayList<Sprite>();
   ArrayList<Sprite> sprite5 = new ArrayList<Sprite>();
   ArrayList<Sprite> sprite6 = new ArrayList<Sprite>();
   ArrayList<Sprite> sprite7 = new ArrayList<Sprite>();
   ArrayList<Sprite> sprite8 = new ArrayList<Sprite>();
   ArrayList<Sprite> sprite9 = new ArrayList<Sprite>();
   ArrayList<Sprite> sprite10 = new ArrayList<Sprite>();

   // ImageIcon variables to represent different sprites
   ImageIcon tableTop1, tableTop2, tableMid1, tableMid2, tableBot1, tableBot2;
   ImageIcon holder, glass, glassShine, glassSideR, glassSideL;
   ImageIcon wallBot1, wallBot2, wallBot3, wallBot4, wallMid, wallTop1, wallTop2;
   ImageIcon sky1, sky2, sky3, sky4;
   AlphaImageIcon gls, glsS;
   
   // current Draw for incrementing columns of sprites. The idea is to
   // draw a basic map using the variables above and then draw more customized sprites as needed
   // in the draw function
   int drawInt;
   int currDraw = 0;
   // Place custom sprites here
   Sprite hld;
   Sprite glSR, glSL, glSH; // glass side right and left
   Sprite tB2, tB3, tM2, tM3, tT2, tT3;
   Sprite s;
   
   public SpriteMap(GamePanel panel) {
      this.gp = panel;
      drawInt = gp.tileSize;
      loadSprites();
   }
   public void loadSprites() {
      tableTop1 =  new ImageIcon("resources/Ice Cream/sprite_09.png");
      tableTop2 =  new ImageIcon("resources/Ice Cream/sprite_10.png");
      tableMid1 =  new ImageIcon("resources/Ice Cream/sprite_11.png");
      tableMid2 =  new ImageIcon("resources/Ice Cream/sprite_12.png");
      tableBot1 =  new ImageIcon("resources/Ice Cream/sprite_13.png");
      tableBot2 =  new ImageIcon("resources/Ice Cream/sprite_14.png");
      
      holder = new ImageIcon("resources/Ice Cream/sprite_15.png");
      glass =  new ImageIcon("resources/Ice Cream/sprite_22.png");
      gls = new AlphaImageIcon(glass, .7f);
      glassShine =  new ImageIcon("resources/Ice Cream/sprite_23.png");
      glsS = new AlphaImageIcon(glassShine, .7f);
      
      wallBot1 =  new ImageIcon("resources/Ice Cream/sprite_16.png");
      wallBot2 =  new ImageIcon("resources/Ice Cream/sprite_17.png");
      wallBot3 =  new ImageIcon("resources/Ice Cream/sprite_20.png");
      wallBot4 =  new ImageIcon("resources/Ice Cream/sprite_21.png");
      wallMid = new ImageIcon("resources/Ice Cream/sprite_33.png");
      wallTop1 =  new ImageIcon("resources/Ice Cream/sprite_18.png");
      wallTop2 =  new ImageIcon("resources/Ice Cream/sprite_19.png");
      
      sky1 = new ImageIcon("resources/Ice Cream/sprite_24.png");
      sky2 = new ImageIcon("resources/Ice Cream/sprite_25.png");
      sky3 = new ImageIcon("resources/Ice Cream/sprite_26.png");
      sky4 = new ImageIcon("resources/Ice Cream/sprite_27.png");
     
      for( int i = 0; i <= gp.maxHSize; i++ ) {
         if( i == 2 || i == 13 ) {
         sprite1.add(new Sprite(this.gp, sky3, currDraw, 0));
         sprite2.add(new Sprite(this.gp, sky2, currDraw, drawInt));
         sprite3.add(new Sprite(this.gp, sky1, currDraw, drawInt*2));
         sprite5.add(new Sprite(this.gp, glsS, currDraw, drawInt*4));
         sprite6.add(new Sprite(this.gp, wallTop2, currDraw, drawInt*5));
         sprite7.add(new Sprite(this.gp, wallBot2, currDraw, drawInt*6));
         sprite8.add(new Sprite(this.gp, tableTop1, currDraw, drawInt*7));
         sprite9.add(new Sprite(this.gp, tableMid1, currDraw, drawInt*8));
         sprite10.add(new Sprite(this.gp, tableBot1, currDraw, drawInt*9));
         }
         else {
         sprite1.add(new Sprite(this.gp, sky3, currDraw, 0));
         sprite2.add(new Sprite(this.gp, sky2, currDraw, drawInt));
         sprite3.add(new Sprite(this.gp, sky1, currDraw, drawInt*2));
         sprite5.add(new Sprite(this.gp, gls, currDraw, drawInt*4));
         sprite6.add(new Sprite(this.gp, wallTop2, currDraw, drawInt*5));
         sprite7.add(new Sprite(this.gp, wallBot2, currDraw, drawInt*6));
         sprite8.add(new Sprite(this.gp, tableTop1, currDraw, drawInt*7));
         sprite9.add(new Sprite(this.gp, tableMid1, currDraw, drawInt*8));
         sprite10.add(new Sprite(this.gp, tableBot1, currDraw, drawInt*9));
         }
         currDraw += drawInt;
      }
      // Load customs here
      hld = new Sprite(this.gp, holder, gp.tileSize * 7, gp.tileSize * 5 );
      tT2 = new Sprite(this.gp, tableTop2, gp.tileSize * 1, gp.tileSize * 7);
      tM2 = new Sprite(this.gp, tableMid2, gp.tileSize * 11, gp.tileSize * 8);
      tB2 = new Sprite(this.gp, tableBot2, gp.tileSize * 9, gp.tileSize * 9);
      tT3 = new Sprite(this.gp, tableTop2, gp.tileSize * 7, gp.tileSize * 7);
      tM3 = new Sprite(this.gp, tableMid2, gp.tileSize * 4, gp.tileSize * 8);
      tB3 = new Sprite(this.gp, tableBot2, gp.tileSize * 2, gp.tileSize * 9);

   }
   public void drawStart(Graphics g) {
      for(int i = 0; i < gp.maxHSize; i++) {
         sprite1.get(i).draw(g);
         sprite2.get(i).draw(g);
         sprite3.get(i).draw(g);
      }
   }
   public void draw(Graphics g) {
      
      for( int i = 0; i <= gp.maxHSize; i++ ) {
         if(i == 7) {
            sprite1.get(i).draw(g);
            sprite2.get(i).draw(g);
            sprite3.get(i).draw(g);
            //sprite4.get(i).draw(g);
            sprite6.get(i).draw(g);
            sprite7.get(i).draw(g);
            sprite8.get(i).draw(g);
            sprite9.get(i).draw(g);
            sprite10.get(i).draw(g);
         }
         else {
            sprite1.get(i).draw(g);
            sprite2.get(i).draw(g);
            sprite3.get(i).draw(g);
            //sprite4.get(i).draw(g);
            sprite5.get(i).drawAlpha(g);
            sprite6.get(i).draw(g);
            sprite7.get(i).draw(g);
            sprite8.get(i).draw(g);
            sprite9.get(i).draw(g);
            sprite10.get(i).draw(g);
         }
      }
      hld.draw(g);
      tT2.draw(g);
      tM2.draw(g);
      tB2.draw(g);
      tT3.draw(g);
      tM3.draw(g);
      tB3.draw(g);
   }
}