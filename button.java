import java.awt.Graphics;
import java.awt.Color;

public class button extends gridSquare{
   
   public button(int xIn, int yIn, int wIn, int hIn, Color colIn){
      super(xIn, yIn, wIn, hIn, colIn);
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
}