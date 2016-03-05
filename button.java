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
      g.setColor(super.getCol());
      if(fillType.equals("FILL"))
         g.fillRect(super.getX(), super.getY(), super.getW(), super.getH());
      else if(fillType.equals("HALF-FILL"))
         for(int r = super.getY(); r < super.getY() + super.getH(); r++)
            for(int c = super.getX() + r % 2; c < super.getX() + super.getW(); c += 2)
               g.drawLine(c, r, c, r);
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