import gui.GUISimulator ;
import java.awt.Color ;
import gui.Rectangle ;
import java.awt.Point;

public class TestBoids {
    public static void main ( String [] args) {
	GUISimulator gui = new GUISimulator (500 , 500 , Color.BLACK );
	int[] coord = {250, 250, 230, 230, 140, 180, 430, 25, 14, 120, 470, 198, 201, 451, 74, 345, 48, 185, 2, 1, 26 ,58 , 880, 900, 500, 800, 600, 50, 20,20,15,16,14,90,55,148,900,955,81,500, 800, 800};
	Boids[] boids = new Boids[coord.length / 2];
	for(int k = 0; k<coord.length / 6; k ++){
	    boids[3*k] = new Proie(coord[6 * k], coord[6 * k + 1]);
	    boids[3*k + 1] = new Predateur(coord[6 * k + 2], coord[6 * k + 3]);
	    boids[3*k + 2] = new Proie(coord[6 * k + 4], coord[6 * k + 5]);
	}
	gui.setSimulable (new EnsembleBoids(boids, gui, 1000, 1000, true));
    }
    
}
