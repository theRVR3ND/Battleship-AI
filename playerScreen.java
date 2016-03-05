import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class playerScreen extends JPanel{
   
   private button[][] gridFire;
   private gridSquare[][] gridBoard;
   
   private String[][] board;
   
   public playerScreen(){
      gridFire = new button[10][10];
      gridBoard = new gridSquare[10][10];
      board = new String[10][10];
      
      for(int r = 0; r < 10; r++){
         for(int c = 0; c < 10; c++){
            gridBoard[r][c] = new gridSquare(25 + 40 * c, 450 + 40 * r, 40, 40, Color.green);
            gridFire[r][c] = new button(25 + 40 * c, 25 + 40 * r, 40, 40, Color.green);
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
      for(gridSquare[] row : gridBoard)
         for(gridSquare on : row)
            on.draw(g);
      //
      repaint((long)(0.0001), 0, 0, 450, 875);
   }
   
   public void fire(){
      int r = ((int)BattleshipAI.mousePos.getY() - 25) / 40;
      int c = ((int)BattleshipAI.mousePos.getX() - 25) / 40;
      if(!(r >= 0 && r < 10 && c >= 0 && c < 10))
         return;
      if(BattleshipAI.AI.shotAt(r, c))
         gridFire[r][c].setFill("FILL");
      else
         gridFire[r][c].setFill("HALF-FILL");
   }
   
   public boolean shotAt(int r, int c){
      if(board[r][c] != null){
         board[r][c] = null;
         return true;
      }
      return false;
   }
}