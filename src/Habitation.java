import gui.* ;
import java.awt.Color ;
import gui.Rectangle ;
import java.awt.Point;

public class Habitation{
	int etat = 0;
	int[] voisins = {};
	public CelluleMultiple(){
		this.etat = 0;
	}

	public CelluleMultiple(int n){
		this.etat = n;
	}
	
	void ajouterVoisinMultiple(int pos){
		int[] tampon = new int[this.voisins.length + 1];
		for (int k = 0; k < this.voisins.length + 1){
			tampon[k] = this.voisins[k];
		}
		tampon[this.voisins.length] = pos;
		this.voisins = tampon;
	}

}
