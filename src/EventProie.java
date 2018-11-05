public class EventProie extends Event{
    private long date;
    private EnsembleBoids boids;
    private EventManager manager;

    public EventProie(long date, EnsembleBoids boids, EventManager manager){
	super(date);
	this.boids = boids;
	this.manager = manager;
    }

    public void execute(){
	boids.updateVoisins();
  	for(Boids b : boids.getBoids()){
	    if(b.getType()){
		//met les positions des boids a jour
  		b.updateForces(boids.getLength(), boids.getHeight());
  		b.updateAcc();
  		b.updateVit();
  		b.updatePos(boids.getLength(), boids.getHeight());
	    }
	}
  	boids.drawBoids();
	manager.addEvent(new EventProie(this.getDate() + 1, boids, manager));
    }
}

