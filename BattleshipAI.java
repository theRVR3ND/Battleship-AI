import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Point;

public class BattleshipAI{

   public static Point mousePos = new Point(-10, -10);
   public static playerScreen player;
   public static theThing AI;
   
   public static void main(String[] args){
      JFrame frame = new JFrame("The Glorious Battleship AI?");
      player = new playerScreen();
      AI = new theThing();
      
      frame.setSize(466, 913);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addMouseMotionListener(new mouseListener());
      frame.addMouseListener(new mouseEventListener());
      frame.setContentPane(player);
      frame.setVisible(true);
   }
   
   public static class mouseListener implements MouseMotionListener{
      public void mouseDragged(MouseEvent e){}
      
      public void mouseMoved(MouseEvent e){
         mousePos = new Point(e.getX() - 10, e.getY() - 30);
      }
   }
   
   public static class mouseEventListener implements MouseListener{
      public void mousePressed(MouseEvent e){}
      
      public void mouseReleased(MouseEvent e){}
      
      public void mouseEntered(MouseEvent e){}
      
      public void mouseExited(MouseEvent e){}
      
      public void mouseClicked(MouseEvent e){
         BattleshipAI.player.fire();
      }
   }
}