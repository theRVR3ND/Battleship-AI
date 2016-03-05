public class theThing{
   //The brain of BattleshipAI
   
   private final String[][] board;
   private final String[] ships = {"A", 
                                   "B", 
                                   "S", 
                                   "C", 
                                   "P"};
   private final int[] lengths = {5, 4, 3, 3, 2};
   
   private String[][] hits;
   
   public theThing(){
      hits = new String[10][10];
      
      String[][] tryBoard = new String[10][10];
      
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
      /*
      for(String[] row : board){
         for(String on : row)
            if(on != null)
               System.out.print(on + " ");
            else
               System.out.print("  ");
         System.out.println("");
      }
      */
   }
   
   private boolean shotAt(int r, int c){
      if(board[r][c] != null){
         board[r][c] = null;
         return true;
      }
      return false;
   }
   
   private String[][] removeAll(String remove, String[][] removeFrom){
      for(int r = 0; r < removeFrom.length; r++)
         for(int c = 0; c < removeFrom[0].length; c++)
            if(removeFrom[r][c] != null && removeFrom[r][c].equals(remove))
               removeFrom[r][c] = null;
      return removeFrom;
   }
}