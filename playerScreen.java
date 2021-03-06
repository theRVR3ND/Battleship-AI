import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class playerScreen extends JPanel{
   
   private button[][] gridFire;
   private gridSquare[][] gridBoard;
   
   private String[][] board;
   
   private final String[] shipSymb = {"A", "B", "S", "C", "P"};
   private final int[] shipLengths = { 5,   4,   3,   3,   2};
   private int placeR, placeC, shipRot, placeInd;              //shipRot: 1 = North, 2 = East, 3 = South, 4 = West
   private int numHits;
   private boolean initializing;
   
   private String message;
   
   public playerScreen(){
      gridFire = new button[10][10];
      gridBoard = new gridSquare[10][10];
      board = new String[10][10];
      
      placeR = 0;
      placeC = 0;
      shipRot = 1;
      initializing = true;
      placeInd = 0;
      numHits = 0;
      
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
            if(! initializing)
               on.checkMouse((int)BattleshipAI.mousePos.getX(), (int)BattleshipAI.mousePos.getY()); 
            on.draw(g);
         }
      for(gridSquare[] row : gridBoard)
         for(gridSquare on : row)
            on.draw(g);
         //Draw representations of ships
      for(int r = 0; r < 10; r++)
         for(int c = 0; c < 10; c++)
            if(board[r][c] != null)
               gridBoard[r][c].setHighlight(true);
            else
               gridBoard[r][c].setHighlight(false);
      if(initializing){
         int glowR = placeR, glowC = placeC;
         for(int i = 0; i < shipLengths[placeInd]; i++){   
            gridBoard[glowR][glowC].setHighlight(true);
            if(!(glowR >= 0 && glowR < 10 && glowC >= 0 && glowC < 10))
               break;
            if(shipRot == 1)
               glowR++;
            else if(shipRot == 2)
               glowC++;
            else if(shipRot == 3)
               glowR--;
            else//if(shipRot == 4)
               glowC--;
         }
      }
         //Draw message
      if(message != null){
         g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
         g.drawString(message, 0, 0);
      }
      //
      repaint((long)(0.0001), 0, 0, 450, 875);
   }
   
   public void setMessage(String in){
      message = in;
   }
   
   public boolean isInitializing(){
      return initializing;
   }
   
   public void keyPress(int keyCode){
      int shipRotShift = 0;
      int placeRShift = 0, placeCShift = 0;
      
      if(keyCode == KeyEvent.VK_Q)
         shipRotShift++;
      else if(keyCode == KeyEvent.VK_E)
         shipRotShift--;
      else if(keyCode == KeyEvent.VK_W)
         placeRShift--;
      else if(keyCode == KeyEvent.VK_A)
         placeCShift--;
      else if(keyCode == KeyEvent.VK_S)
         placeRShift++;
      else if(keyCode == KeyEvent.VK_D)
         placeCShift++;
      
      if(checkShipPlace(placeR + placeRShift, placeC + placeCShift, shipRot + shipRotShift)){
         shipRot += shipRotShift;
         placeC += placeCShift;
         placeR += placeRShift;
      }
      
      if(keyCode == KeyEvent.VK_ENTER){
         placeShip();
         do{
            placeR = (int)(Math.random() * 10);
            placeC = (int)(Math.random() * 10);
            shipRot = (int)(Math.random() * 4 + 1);
         }while(board[placeC][placeR] != null || ! checkShipPlace(placeR, placeC, shipRot));
         placeInd++;
         if(placeInd == 5)
            initializing = false;
      }  
      
      shipRot %= 4;
      if(placeR == -1)
         placeR = 0;
      else if(placeR == 10)
         placeR = 9;
      else if(placeC == -1)
         placeC = 0;
      else if(placeC == 10)
         placeC = 9;
   }
   
   public void fire(){
      if(initializing)
         return;
      int r = ((int)BattleshipAI.mousePos.getY() - 25) / 40;
      int c = ((int)BattleshipAI.mousePos.getX() - 25) / 40;
      if(!(r >= 0 && r < 10 && c >= 0 && c < 10))
         return;
      if(BattleshipAI.AI.shotAt(r, c)){
         gridFire[r][c].setFill("FILL");
         numHits++;
         if(numHits == 17){
            message = "I win(!)";
            System.out.println("Player wins");
         }
      }else if(! gridFire[r][c].fillType().equals("FILL"))
         gridFire[r][c].setFill("SHADE-FILL");
      message = null;
   }
   
   public boolean shotAt(int r, int c){
      if(r < 0 || r >= 10 || c < 0 || c >= 10)
         return false;
      if(board[r][c] == null){
         gridBoard[r][c].setFill("SHADE-FILL");
         return false;
      }else if(! board[r][c].equals("H")){
         board[r][c] = "H";
         gridBoard[r][c].setFill("FILL");
         return true;
      }
      return false;
   }
   
   private boolean checkShipPlace(int r, int c, int rot){
      for(int i = 0; i < shipLengths[placeInd]; i++){
         if(r < 0 || r >= 10 | c < 0 || c >= 10 || board[r][c] != null)
            return false;
         if(rot == 1)
            r++;
         else if(rot == 2)
            c++;
         else if(rot == 3)
            r--;
         else//if(rot == 4)
            c--;
      }
      return true;
   }
   
   private void placeShip(){
      int r = placeR, c = placeC;
      for(int i = 0; i < shipLengths[placeInd]; i++){
         board[r][c] = "S";
         if(shipRot == 1)
            r++;
         else if(shipRot == 2)
            c++;
         else if(shipRot == 3)
            r--;
         else//if(shipRot == 4)
            c--;
      }
   }
}