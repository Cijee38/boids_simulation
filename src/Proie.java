import gui.* ;
import java.awt.Color ;
import java.awt.Point;
import java.lang.Math;

public class Proie extends Boids{
    /**
       Herite de Boids, permet de modéliser des Boids-Proies, 
       afin de pouvoir simuler une intéraction entre différentes espèces
    */
    private int[] forceP = new int[2];
    public Proie(int x, int y, int Vx, int Vy){
	super(x, y, Vx, Vy, 3 * Math.PI / 4, 5, 1, "sardine", "0x00ff55", true);
    }
    public Proie(int x, int y){
	super(x, y, 2, 2, 3 * Math.PI / 4, 5, 1, "sardine","0x00ff55", true);
    }

    public void forcePrey(){
	int count = 0;
	forceP[0] =  0;
	forceP[1] =  0;
	for(Boids b : this.getVoisins()){
	    if(!b.getType()){
		count ++;
		//il y a un predateurs parmis les voisins
		if(distance(b) < 50){
		    //pas de lautre coté dun mur + distance pour savoir si cest un predateur
		    forceP[0] +=  this.getX() - b.getX();
		    forceP[1] +=  this.getY() - b.getY();
		}
	    }
	}
	if (count != 0){
	    changeNorme(forceP);
	}
    }
    public void verifyPartner(){
	/**
	   Methode qui va vérifie si un accouplement a lieu d'etre
	 */
	for(Boids b : this.getVoisins()){
	    if(b.getType() && this.getPregnant() == 0){
		//il y a un copain parmis les voisins et non en attente dun bebe
		if(distance(b) < 30 && b.getSex() != this.getSex() && b.getPregnant() == 0){
		    b.setPregnant(1);
		    this.setPregnant(1);
		    //le fait dattendre un enfant est pareil pour chaque sexe chez les Boids
		}
      
	    }
	}
    }
    public void updateForces(int length, int height){
	this.forceAlignement();
	this.forceGravitation(length, height);
	this.forceSeparation(length, height);
	this.forcePrey();
	this.verifyPartner();
    }
    public void updateAcc(){
	int[] forceG = this.getForceG();
	int[] forceA = this.getForceA();
	int[] forceS = this.getForceS();
	this.setAcceleration(forceG[0] + forceA[0] + forceS[0]  + this.forceP[0], forceG[1] + forceA[1] + forceS[1] + this.forceP[1]);
    }

}
