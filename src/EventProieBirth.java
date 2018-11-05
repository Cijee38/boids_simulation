public class EventProieBirth extends Event{
    /**
       Classe devenement qui correspond a la naissance d'un boids Proie
     */
    private long date;
    private EnsembleBoids boids;
    private EventManager manager;
    private Proie parent;
    public EventProieBirth(long date, EnsembleBoids boids, EventManager manager, Proie parent){
	super(date);
	this.boids = boids;
	this.manager = manager;
	this.parent = parent;
    }
    public void execute(){
	if(parent.getAlive() == 0){
	    if(parent.getSex() && boids.getPopulation() <= 80){
		boids.getBoids().add(new Proie(parent.getX() + 5, parent.getY() + 5));
		boids.setPopulation(boids.getPopulation() + 1);
	    }
	    parent.setPregnant(0);
	}
    }
}
