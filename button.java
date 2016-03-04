import java.awt.Graphics;
import java.awt.Color;

public class button{
   
   private final int x, y, w, h;
   private final Color col;
   private boolean highlighted;

   public button(int xIn, int yIn, int wIn, int hIn, Color colIn){
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
   
   public void checkMouse(int xIn, int yIn){
      if(xIn >= x && 
         xIn <= x + w && 
         yIn >= y && 
         yIn <= y + h)
         highlighted = true;
      else
         highlighted = false;
   }
}