import gui.* ;
import java.awt.Color ;
import gui.Rectangle ;
import java.awt.Point;

public class Conway implements Simulable {
    /**
       Représentation du modèle du jeu de la vie de Conway
       Partie 3.1
       Equipe 78
    */
    private Cellule[] cellules;
    private GUISimulator window;
    private int length;
    private int height;
    private Cellule[] init;
    private EventManager manager;
    public Conway(int[] pos, int length, int height, GUISimulator gui){
	//new GUISimulator (length * 10, height * 10, Color.BLACK);
	window = gui;
	this.length = length;
	this.height = height;
	cellules = new Cellule[height * length];
	init = new Cellule[height * length];
	for (int k = 0; k < height * length; k++){
	    this.cellules[k] = new Cellule();
	    this.cellules[k].setStates(2);
	    init[k] = new Cellule(this.cellules[k].getAlive(), this.cellules[k].getVoisins(),2);
	}
	for(int k = 0; k < pos.length; k++){
	    this.cellules[k].setAlive(pos[k]);
	    init[k].setAlive(pos[k]);
	}
	manager = new EventManager();
	manager.addEvent(new EventConway(0, this, manager));
	drawCells();
    }
    public Conway(int[] pos, int length, int height, GUISimulator gui, int nbStates ){
	//new GUISimulator (length * 10, height * 10, Color.BLACK);
	window = gui;
	this.length = length;
	this.height = height;
	cellules = new Cellule[height * length];
	init = new Cellule[height * length];
	for (int k = 0; k < height * length; k++){
	    this.cellules[k] = new Cellule(1);
	    this.cellules[k].setStates(nbStates);
	    init[k] = new Cellule(this.cellules[k].getAlive(), this.cellules[k].getVoisins(), nbStates);
	}
	for(int k = 0; k < pos.length; k++){
	    this.cellules[k].setAlive(pos[k]);
	    init[k].setAlive(pos[k]);
	}
	manager = new EventManager();
	manager.addEvent(new EventConway(0, this, manager));
	drawCells();
    }

    @Override
    public void next(){
	manager.next();
    }
    @Override
    public void restart () {
	for(int k = 0; k < this.init.length; k++){
	    this.cellules[k].setAlive(init[k].getAlive());
	    this.cellules[k].setVoisins(init[k].getVoisins());
	}
	drawCells();
    }

    public void updateVoisins(){
	/**
	   Update number of all cellules' neighbors
	 */
	for(int k = 0; k < this.getCellules().length; k++){
	    if (this.cellules[k].getAlive() == 1){
		for (int i = -1; i < 2; i++){
		    for (int j = -1; j < 2; j++){
			if ((i != 0 | j != 0)){
			    this.cellules[Math.floorMod(((k % this.length) + i), length) + Math.floorMod(((k/this.height) + j), height) * length].ajouterVoisin(k);
			}
		    }
		}
	    }
	}
    }
    @Override
    public String toString(){
	String result = "";
	for (Cellule cel : this.cellules){
	    result += cel.toString();
	}
	return result;
    }
    public void setCellules(Cellule[] tampon){
	this.cellules = tampon;
    }
    public Cellule[] getCellules(){
	return this.cellules;
    }
    public Cellule[] getInit(){
	return this.init;
    }
    public int getLength(){
	return this.length;
    }
    public int getHeight(){
	return this.height;
    }
    public GUISimulator getWindow(){
	return this.window;
    }
    public void drawCells(){
	getWindow().reset();
	for(int k = 0;  k < this.cellules.length ; k++){
	    int type = this.cellules[k].getAlive();
	    if (type != 0){
		window.addGraphicalElement(
					   new Rectangle((k%length) * 14 + 5 , (k/length) * 14 + 5 ,
							 Color.decode("#1f77b4"),
							 Color.decode("#1f77b4"),
							 10));
	    }
	}
    }
}
