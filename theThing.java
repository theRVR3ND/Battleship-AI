public class theThing{
   //The brain of BattleshipAI
   
   private final String[][] board;
   private final String[] ships = {"A", "B", "S", "C", "P"};
   private final int[] lengths = {  5,   4,   3,   3,   2};
   
   private boolean[][] shotAt;
   
   private int battleshipHits;
   private int shootR, shootC;
   private int origR, origC;
   private int numHits;
   private int checkDir;
   private boolean prevWasHit;
   
   public theThing(){
      shotAt = new boolean[10][10];
      
      String[][] tryBoard = new String[10][10];
      
      //Board initialization//
      for(int i = 0; i < 5; i++){
         int tryR = (int)(Math.random() * 10);
         int tryC = (int)(Math.random() * 10);
         boolean isHorizontal = Math.random() < 0.5;
         
         int onR = tryR;
         int onC = tryC;
         
         for(int l = 0; l < lengths[i]; l++){
            if(onR < 0 || onR >= 10 || onC < 0 || onC >= 10 || tryBoard[onR][onC] != null){
               tryBoard = removeAll(ships[i], tryBoard);
               i--;
               break;
            }else
               tryBoard[onR][onC] = ships[i];
            
            if(isHorizontal)
               onC++;
            else
               onR++;
         }
      }
      board = tryBoard;
      //End board initialization//
      
      battleshipHits = 0;
      shootR = 0;
      shootC = 0;
      origR = -1;
      origC = -1;
      System.out.println(shootR + " " + shootC);
      numHits = 0;
      checkDir = 0;  //1 = North, 2 = East, 3 = South, 4 = West
      prevWasHit = false;
      /*
      for(String[] row : board){
         for(String on : row)
            if(on != null)
               System.out.print(on + " ");
            else
               System.out.print("- ");
         System.out.println("");
      }
      */
   }
   
   public boolean shotAt(int r, int c){
      respond();
      if(board[r][c] != null && ! board[r][c].equals("H")){
         if(board[r][c].equals("B")){
            battleshipHits++;
            if(battleshipHits == 4)
               BattleshipAI.player.setMessage("AI Battleship Sunk");
         }
         board[r][c] = "H";
         return true;
      }
      return false;
   }
   
   private void respond(){
      boolean shoot = true;
      if(! prevWasHit){
         if(checkDir < 5 && origR >= 0){
            checkDir++;
            prevWasHit = true;
            shootR = origR;
            shootC = origC;
            if(checkDir > 4){
               origR = -1;
               prevWasHit = false;
               shoot = false;
               respond();
            }
         }else{
            checkDir = 0;
            shootC = 0;
            shootR = 0;
            while(shotAt[shootR][shootC]){
               shootC += 2;
               if(shootC % 2 != shootR % 2)
                  if(shootC > 0) 
                     shootC--;
                  else
                     shootC++;
               if(shootC >= 10){
                  shootR++;
                  shootC = shootR % 2;
               }
            }
         }
      }
      if(prevWasHit){
         if(origR < 0){
            origR = shootR;
            origC = shootC;
         }
         if(checkDir == 0)
            checkDir = 1;
         
         if(checkDir == 1)
            shootR--;
         else if(checkDir == 2)
            shootC++;
         else if(checkDir == 3)
            shootR++;
         else if(checkDir == 4)
           shootC--;
         
         if(shootR < 0 || shootR >= 10 || shootC < 0 || shootC >= 10){
            checkDir++;
            shootR = origR;
            shootC = origC;
            if(checkDir == 5){
               origR = -1;
               prevWasHit = false;
            }
            shoot = false;
            respond();
         }
      }
      //
      if(! shoot)
         return;
      System.out.println(shootR + " " + shootC + "\t" + checkDir);
      shotAt[shootR][shootC] = true;
      if(BattleshipAI.player.shotAt(shootR, shootC)){
         prevWasHit = true;
         numHits++;
         if(numHits == 17){
            BattleshipAI.player.setMessage("I win");
            System.out.println("AI Wins");
         }
      }else
         prevWasHit = false;
      //
   }
   
   private String[][] removeAll(String remove, String[][] removeFrom){
      for(int r = 0; r < removeFrom.length; r++)
         for(int c = 0; c < removeFrom[0].length; c++)
            if(removeFrom[r][c] != null && removeFrom[r][c].equals(remove))
               removeFrom[r][c] = null;
      return removeFrom;
   }
}