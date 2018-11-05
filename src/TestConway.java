import gui.GUISimulator ;
import java.awt.Color ;
import gui.Rectangle ;
import java.awt.Point;

public class TestConway {
    public static void main ( String [] args) {
	GUISimulator gui = new GUISimulator (500 , 500 , Color.WHITE );
	int[] cells = new int[10000];
	for(int k = 0; k < 99; k++){
	    cells[k] = 1;
	}
	gui.setSimulable (new Conway(cells, 100, 100, gui, 2));
    }
}
