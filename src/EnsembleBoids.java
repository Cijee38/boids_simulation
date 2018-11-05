import gui.* ;
import java.awt.Color ;
import java.awt.Point;
import java.util.ArrayList;

public class EnsembleBoids implements Simulable{
    /**
       Classe représentant un ensemble de Boids, implémente l'interface simulable;
       Partie 4
       Equipe 78
    */
    private ArrayList<Boids> boids = new ArrayList<Boids>();
    private ArrayList<Boids> init = new ArrayList<Boids>();
    private int rayon = 100;
    private int length;
    private int height;
    private EventManager manager;
    private GUISimulator window;
    private boolean reproduction;
    private int pop = 0;
    public EnsembleBoids(Boids[] boids, GUISimulator gui, int length, int height, boolean reproduction){
	/*
	  Classe modélisant la cohabitation des Boids passé en paramètre
	 */
	for (Boids b : boids){
	    this.boids.add(b);
	    this.init.add(b);
	    this.pop ++;
	}
	this.length = length;
	this.height = height;
	window = gui;
	manager = new EventManager();
	manager.addEvent(new EventProie(0, this, manager));
	manager.addEvent(new EventPredateur(0, this, manager));
	manager.addEvent(new EventGrow(5, this, manager));
	this.drawBoids();
	this.reproduction = reproduction;
    }


    public void setPopulation(int pop){
	this.pop = pop;
    }
    public int getPopulation(){
	return pop;
    }
    public int getLength(){
	return length;
    }

    public int getHeight(){
	return height;
    }

    public ArrayList<Boids> getBoids(){
	return boids;
    }

    public void next(){
	manager.next();
    }

    public void drawBoids(){
	window.reset();
	for(Boids b : this.boids){
	    if(b.getAlive() == 0){
		b.draw(window);
	    }
	}
    }


    public void restart(){
	this.boids = new ArrayList<Boids>();
	for (Boids b : this.init){
	    b.restart();
	    this.boids.add(b);
	}
	this.pop = 0;
	manager = new EventManager();
	manager.addEvent(new EventProie(0, this, manager));
	manager.addEvent(new EventPredateur(0, this, manager));
	manager.addEvent(new EventGrow(5, this, manager));
	drawBoids();
    }

    public void updateVoisins(){
	for(Boids b1 : this.boids){
	    //for (int k = 0; k < this.boids.size(); k++){
	    //Boids b1 = this.boids.get(k);
	    if(b1.getAlive() == 0){
		if(reproduction){
		    if(b1.getPregnant() == 1){
			if(b1.getType()){
			    this.manager.addEvent(new EventProieBirth(200, this, manager, (Proie)b1));
			}else{
			    this.manager.addEvent(new EventPredateurBirth(500, this, manager, (Predateur)b1));
			}
			b1.setPregnant(2);
		    }
		}
		b1.resetVoisins();
		for(Boids b2: this.boids){
		//	for (int j = k +1; j < this.boids.size(); j++){
		//  Boids b2 = this.boids.get(j);
		    if(b2.getAlive() == 0){
			if(b1.estVoisin(b2, rayon, length, height) && b1.dansVision(b2)){
			    b1.ajoutVoisin(b2);
			    //    b2.ajoutVoisin(b1);
			}
		    }
		}
	    }else if(b1.getAlive() == 2){
		pop -= 1;
		b1.setAlive(1);
	    }

	}
    }

}
