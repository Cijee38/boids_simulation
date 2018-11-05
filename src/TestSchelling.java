import gui.GUISimulator ;
import java.awt.Color ;
import gui.Rectangle ;
import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

public class TestSchelling {
    public static void main ( String [] args) {
	GUISimulator gui = new GUISimulator (500 , 500 , Color.WHITE );
	int[] cells = new int[1600];
	for(int k = 0; k < 1600; k+=2){
	    cells[k] = ThreadLocalRandom.current().nextInt(0, 4);
	    cells[k+1] = ThreadLocalRandom.current().nextInt(0, 2);
	}
	gui.setSimulable (new Schelling(cells, 40, 40, gui, 4, 3));
    }
}
