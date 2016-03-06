import java.awt.Graphics;
import java.awt.Color;

public class gridSquare{
   
   private final int x, y, w, h;
   private final Color col;
   private boolean highlighted;
   private String fillType;
   
   public gridSquare(int xIn, int yIn, int wIn, int hIn, Color colIn){
      x = xIn;
      y = yIn;
      w = wIn;
      h = hIn;
      col = colIn;
      
      highlighted = false;
      fillType = "NONE";
   }
   
   public void draw(Graphics g){
      g.setColor(col);
      g.drawRect(x, y, w, h);
      if(fillType.equals("FILL")){
         g.setColor(col);
         g.fillRect(x, y, w, h);
      }else if(fillType.equals("SHADE-FILL")){
         g.setColor(new Color(0, 255, 0, 112));
         g.fillRect(x, y, w, h);
      }
      if(highlighted)
         g.drawRect(x + 2, y + 2, w - 4, h - 4);
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
   
   public void setFill(String in){
      fillType = in;
   }
   
   public String fillType(){
      return fillType;
   }
}