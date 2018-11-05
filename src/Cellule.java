import gui.* ;
import java.awt.Color ;
import gui.Rectangle ;
import java.awt.Point;

public class Cellule {
    private int alive;
    private int[] voisins = {};
    private int nbStates = 2;
    public Cellule(){
	this.alive = 0;
    }
    public Cellule(int alive){
	this.alive = alive;
    }
    public Cellule(int alive, int[] voisins, int states){
	this.alive = alive;
	this.voisins = voisins;
	this.nbStates = states;
    }
    public void setAlive(int val){
	if (val < this.nbStates){
	    this.alive = val;
	}
    }
    public void setVoisins(int[] liste){
	this.voisins = liste;
    }
    public void setStates(int number){
	this.nbStates = number;
    }
       
    int[] getVoisins(){
	return this.voisins;
    }
    int getAlive(){
	return this.alive;
    }
    void ajouterVoisin(int pos){
	int[] tampon = new int[this.voisins.length + 1];
	for (int k = 0; k < this.voisins.length; k++){
	    tampon[k] = this.voisins[k];
	}
	tampon[this.voisins.length] = pos;
	this.voisins = tampon;
    }
    @Override
    public String toString(){
	return String.valueOf(this.getAlive());
    }
}
