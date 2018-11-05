public class EventPredateurBirth extends Event{
    private long date;
    private EnsembleBoids boids;
    private EventManager manager;
    private Predateur parent;
    public EventPredateurBirth(long date, EnsembleBoids boids, EventManager manager, Predateur parent){
	super(date);
	this.boids = boids;
	this.manager = manager;
	this.parent = parent;
    }
    public void execute(){
	if(parent.getSex() && boids.getPopulation() <= 80){
	    boids.getBoids().add(new Predateur(parent.getX() + 2, parent.getY() + 2));
	    boids.setPopulation(boids.getPopulation() + 1);
	}
	parent.setPregnant(0);
    }
}
