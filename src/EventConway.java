public class EventConway extends Event{
    private long date;
    private Conway conway;
    private EventManager manager;

    public EventConway(long date, Conway conway, EventManager manager){
	super(date);
	this.conway = conway;
	this.manager = manager;
    }

    public void execute(){
	conway.updateVoisins();
	Cellule[] tampon = new Cellule[conway.getCellules().length];
	for (int k = 0; k < conway.getCellules().length; k++){
	    tampon[k] = new Cellule();
	}
	for (int k = 0; k < conway.getHeight() * conway.getLength(); k++){
	    if (conway.getCellules()[k].getAlive() == 1) {
		// cellule vivante
		if(conway.getCellules()[k].getVoisins().length == 3 | conway.getCellules()[k].getVoisins().length == 2 ){
		    //reste vivante
		    tampon[k].setAlive(1);
		}
	    }
	    else {
		//cellule non vivante
		if (conway.getCellules()[k].getVoisins().length == 3) {
		    //devient vivante
		    tampon[k].setAlive(1);
		}
	    }
	}
	conway.setCellules(tampon);
	manager.addEvent(new EventConway(this.getDate() + 1, conway, manager));
	conway.drawCells();
    }
}
