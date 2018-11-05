import java.awt.Color;
import java.awt.Point;
import gui.Oval ;
import gui.* ;

public class EventBalls extends Event {
    private long date;
    private EventManager manager;
    private BallsSimulator balls;

    public EventBalls(long date, BallsSimulator balls, EventManager manager){
	super(date);
	this.balls = balls;
	this.manager = manager;
    }

    public void execute(){
	balls.getBalls().translate(10, 10);
	balls.getWindow().reset();
	for (Balle b : balls.getBalls().getBalls()){
	    balls.getWindow().addGraphicalElement(new Oval(b.getX(), b.getY(),
					       Color.decode("#1f77b4"),
					       Color.decode("#1f77b4"),
					       10));
	}
	manager.addEvent(new EventBalls(this.getDate() + 1, balls, manager));
    }
}
