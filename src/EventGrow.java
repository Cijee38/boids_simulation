public class EventGrow extends Event{
    private long date;
    private EnsembleBoids boids;
    private EventManager manager;

    public EventGrow(long date, EnsembleBoids boids, EventManager manager){
	super(date);
	this.boids = boids;
	this.manager = manager;
    }
    public void execute(){
	for(Boids b : boids.getBoids()){
	    if(b.getType()){
		int[] rays = b.getRayons();
		if (rays[0] <= 10){
		    b.setRayons(rays[0] + 1, rays[1]);
		}
	    }
	}
	manager.addEvent(new EventGrow(this.getDate() + 520, boids, manager));
    }
}
