package gui;
import java.awt.EventQueue;

public class Run {

  public static void main( String [ ] args ) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        Common.showFrame( new Logowanie( ) );
      }
    });
  }
}
