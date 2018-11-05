import gui.* ;
import java.awt.Color ;
import gui.Rectangle ;
import java.awt.Point ;

public class Migration extends Conway{
    /**
       Classe héritant de Conway, représentation le jeu de l'immigration
       Partie 3.2
     */
    private int nbStates;
    private EventManager manager;
    public Migration(int[] pos, int length, int height, GUISimulator gui, int nbStates){
	super(pos, length, height, gui, nbStates);
	this.nbStates = nbStates;
	manager = new EventManager();
	manager.addEvent(new EventMigration(0, this, manager));
    }

    @Override
    public void next(){
	manager.next();
    }

    public void setManager(EventManager manager) {
	manager = manager;
    }

    public EventManager getManager(){
	return manager;
    }

    @Override
    public void drawCells(){
	getWindow().reset();
	for (int k = 0; k < this.getCellules().length; k++){
	    int type = this.getCellules()[k].getAlive();
	    if (type != 0){
		getWindow().addGraphicalElement(
						new Rectangle((k % getLength()) * 14 + 5, (k / getLength()) * 14 + 5 ,
							      Color.decode("#" + String.valueOf(0x222222 * type)),
							      Color.decode("#" + String.valueOf(0x222222 * type)),
							      10));
	    }
	}
    }

    @Override
    public void updateVoisins(){
	for (int k = 0; k < this.getCellules().length; k++){
	    for (int i = -1; i < 2; i++){
		for (int j = -1; j < 2; j++){
		    if ((i != 0 | j != 0)){
			Cellule cel = this.getCellules()[Math.floorMod(((k % this.getLength()) + i), getLength())+ Math.floorMod(((k/this.getHeight()) + j), getHeight())*getLength()];
			if(this.getCellules()[k].getAlive() == 0){
			    if (cel.getAlive() == nbStates - 1){
				cel.ajouterVoisin(k);
			    }
			}
			else {
			    if (cel.getAlive() == this.getCellules()[k].getAlive() - 1){
				cel.ajouterVoisin(k);
			    }
			}
		    }
		}
	    }
	}
    }
    int getStates(){
	return this.nbStates;
    }

}
