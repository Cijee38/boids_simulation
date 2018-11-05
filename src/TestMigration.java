import gui.GUISimulator ;
import java.awt.Color ;
import gui.Rectangle ;
import java.awt.Point ;

public class TestMigration{
    public static void main( String [] args){
	GUISimulator gui = new GUISimulator (500, 500, Color.WHITE );
	int cells[] = {3,0,1,1,0,3,1,1,1,2,1,1,3,2,2,0,1,2,2,2,0,3,2,2,1};
	gui.setSimulable(new Migration(cells, 5, 5, gui, 4));
	
    }
}
