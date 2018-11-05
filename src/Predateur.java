import gui.* ;
import java.awt.Color ;
import java.awt.Point;
import java.lang.Math;

public class Predateur extends Boids{
    /**
       Herite de Boids, permet de modéliser des Boids-Proies, 
       afin de pouvoir simuler une intéraction entre différentes espèces
    */
    private int[] forceP = new int[2];
    public Predateur(int x, int y, int Vx, int Vy){
	super(x, y, Vx, Vy, 2 * Math.PI / 3, 15, 3, "requin", "0x5555ff", false);
    }
    public Predateur(int x, int y){
	super(x, y, 2, 0, 2 * Math.PI / 3, 15, 3,"requin", "0x5555ff", false);
    }
    public void forcePrey(){
	int count = 0;
	forceP[0] =  0;
	forceP[1] =  0;
	for(Boids b : this.getVoisins()){
	    if(b.getType()){
		count ++;
		//il y a un proies parmis les voisins
		if(distance(b)<20){
		    //le predateur mange la proie
		    b.setAlive(2);
		    int[] rays = this.getRayons();
		    if(rays[0] < 30){
			this.setRayons(rays[0] + 1, rays[1]);
		    }
		}
		else if(distance(b)<80){
		    //pas de lautre coté dun mur + distance pour savoir si cest une proie
		    forceP[0] +=  - this.getX() + b.getX();
		    forceP[1] +=  - this.getY() + b.getX();
		}
	    }
	}
	if (count != 0){
	    changeNorme(forceP);
	}
    }
    public void verifyPartner(){
	for(Boids b : this.getVoisins()){
	    if(!b.getType() && this.getPregnant() == 0){
		//il y a un copain parmis les voisins
		if(distance(b) < 10 && b.getSex() != this.getSex() && b.getPregnant() == 0){
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
