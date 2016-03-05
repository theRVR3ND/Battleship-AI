import java.awt.Graphics;
import java.awt.Color;

public class button extends gridSquare{
   
   private String fillType;

   public button(int xIn, int yIn, int wIn, int hIn, Color colIn){
      super(xIn, yIn, wIn, hIn, colIn);
      fillType = "NONE";
   }
   
   public void draw(Graphics g){
      super.draw(g);
      
      if(fillType.equals("FILL"))
         g.setColor(super.getCol());
      else if(fillType.equals("SHADE-FILL"))
         g.setColor(new Color(0, 255, 0, 125));
      else//if(fillType.equals("NONE"))
         return;
         
      g.fillRect(super.getX(), super.getY(), super.getW(), super.getH());   
   }
   
   public void checkMouse(int xIn, int yIn){
      if(xIn >= super.getX() && 
         xIn <= super.getX() + super.getW() && 
         yIn >= super.getY() && 
         yIn <= super.getY() + super.getH())
         super.setHighlight(true);
      else
         super.setHighlight(false);
   }
   
   public void setFill(String modeIn){
      fillType = modeIn;
   }
}