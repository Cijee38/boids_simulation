import gui.GUISimulator ;
import java.awt.Color ;
import gui.Rectangle ;
public class TestBallsSimulator {
  public static void main ( String [] args ) {
    GUISimulator gui = new GUISimulator (500 , 500 , Color.BLACK );
    int[] coord = {50, 21,100,120, 140, 10, 450, 210};
    gui.setSimulable (new BallsSimulator (coord, gui));

  }
}
