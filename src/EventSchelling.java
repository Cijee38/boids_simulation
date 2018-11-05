import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

public class EventSchelling extends Event {
    private long date;
    private EventManager manager;
    private Schelling schelling;

    public EventSchelling(long date, Schelling schelling, EventManager manager){
	super(date);
	this.manager = manager;
	this.schelling = schelling;
    }

    public void execute(){
	if (schelling.emptyQueue.isEmpty()){
	    return;
	}
	schelling.updateVoisins();
	Cellule[] tampon = new Cellule[schelling.getCellules().length];
	for (int k = 0; k < schelling.getCellules().length; k++){
	    tampon[k] = new Cellule();
	    tampon[k].setStates(schelling.getStates());
	}
	for (int k = 0; k < schelling.getCellules().length; k++){
	    int type = schelling.getCellules()[k].getAlive();
	    if (type != 0) {
		//cellule au rang k habitée
		if (schelling.getCellules()[k].getVoisins().length > schelling.getParam()){
		    //déménagement car plus de voisins d'origine différente que le seuil
		    int randint = ThreadLocalRandom.current().nextInt(0, schelling.emptyQueue.size() - 1);
		    int newk = schelling.emptyQueue.get(randint);
		    schelling.emptyQueue.remove(randint);
		    tampon[newk].setAlive(type);
		    schelling.emptyQueue.add(k);
		} else {
		    tampon[k].setAlive(type);
		}
	    }
	}
	schelling.setCellules(tampon);
	manager.addEvent(new EventSchelling(this.getDate() + 1, schelling, manager));
	schelling.drawCells();
    }
}
