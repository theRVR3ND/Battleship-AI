import java.awt.Graphics;
import java.awt.Color;

public class gridSquare{
   
   private final int x, y, w, h;
   private final Color col;
   private boolean highlighted;
   
   public gridSquare(int xIn, int yIn, int wIn, int hIn, Color colIn){
      x = xIn;
      y = yIn;
      w = wIn;
      h = hIn;
      col = colIn;
      
      highlighted = false;
   }
   
   public void draw(Graphics g){
      g.setColor(col);
      g.drawRect(x, y, w, h);
      if(highlighted)
         g.drawRect(x + 1, y + 1, w - 2, h - 2);
   }
   
   public int getX(){
      return x;
   }
   
   public int getY(){
      return y;
   }
   
   public int getW(){
      return w;
   }
   
   public int getH(){
      return h;
   }
   
   public Color getCol(){
      return col;
   }
   
   public void setHighlight(boolean in){
      highlighted = in;
   }
   
   public boolean getHighlighted(){
      return highlighted;
   }
}