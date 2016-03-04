import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class playerScreen extends JPanel{
   
   private button[][] gridFire;
   private button[][] gridHits;
   
   public playerScreen(){
      gridFire = new button[10][10];
      gridHits = new button[10][10];
      
      for(int r = 0; r < 10; r++){
         for(int c = 0; c < 10; c++){
            gridHits[r][c] = new button(25 + 40 * c, 25 + 40 * r, 40, 40, Color.green);
            gridFire[r][c] = new button(25 + 40 * c, 450 + 40 * r, 40, 40, Color.green);
         }
      }
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      //
         //Draw background
      g.setColor(new Color(17, 82, 162));
      g.fillRect(0, 0, 450, 875);
         //Draw grid buttons
      for(button[] row : gridFire)
         for(button on : row){
            on.checkMouse((int)BattleshipAI.mousePos.getX(), (int)BattleshipAI.mousePos.getY()); 
            on.draw(g);
         }
      for(button[] row : gridHits)
         for(button on : row)
            on.draw(g);
      //
      repaint((long)(0.0001), 0, 0, 450, 875);
   }
   
   public void fire(){
      int r = ((int)BattleshipAI.mousePos.getY() - 450) / 40;
      int c = ((int)BattleshipAI.mousePos.getX() - 25) / 40;
   }
}