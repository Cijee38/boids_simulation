import gui.* ;
import java.awt.Color ;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Schelling extends Migration{
    /**
       Classe héritant de Migration, représentation du modèle de Schelling
       Partie 3.3
     */
    private int param; //le seuil de segregation
    private EventManager manager;
    public ArrayList<Integer> emptyQueue = new ArrayList<Integer>();

    public Schelling(int[] pos, int length, int height, GUISimulator gui, int nbStates, int param){
	super(pos, length, height, gui, nbStates);
	this.param = param;
	for(int k = 0; k < height * length; k++){
	    if(this.getCellules()[k].getAlive() == 0){
		//cellule vacante
		this.emptyQueue.add(k);
	    }
	}
	manager = new EventManager();
	manager.addEvent(new EventSchelling(0, this, manager));
    }
    @Override
    public void next(){
	manager.next();
    }


    public int getParam(){
	return param;
    }
    @Override
    public void restart () {
	Cellule[] cells = getInit();
	this.emptyQueue = new ArrayList<Integer>();
	for(int k = 0; k < cells.length; k++){
	    this.getCellules()[k].setAlive(cells[k].getAlive());
	    this.getCellules()[k].setVoisins(cells[k].getVoisins());
	    if(cells[k].getAlive() == 0){
		//cellule vacante
		this.emptyQueue.add(k);
	    }
	}
	drawCells();
    }
    
    @Override
    public void drawCells(){
	getWindow().reset();
	for(int k = 0;  k < this.getCellules().length; k++){
	    int type = this.getCellules()[k].getAlive();
	    if (type != 0){
		getWindow().addGraphicalElement(
						new Rectangle((k % getLength()) * 14 + 5 , (k / getLength()) * 14 + 5 ,
							      Color.decode("#" + String.valueOf(0x222222 * type) ),
							      Color.decode("#" + String.valueOf(0x222222 * type)),
							      10));
	    }
	}
    }

    @Override
    public void updateVoisins(){
	/**
Update number of all cellules' neighbors   
	 */
	for(int k = 0; k < this.getCellules().length; k++){
	    int type = this.getCellules()[k].getAlive();
	    if (type != 0){
		for (int i = -1; i < 2; i++){
		    for (int j = -1; j < 2; j++){
			if ((i != 0 | j != 0)){
			    Cellule cel = this.getCellules()[Math.floorMod(((k % this.getLength()) + i), getLength()) + Math.floorMod(((k/this.getHeight()) + j), getHeight()) * getLength()];
			    if(cel.getAlive() != type & cel.getAlive() != 0){
				//la seule chose importante est le nombre de voisins de couleur differente
				cel.ajouterVoisin(k);
			    }
			}
		    }
		}
	    }
	}
    }
}
