public class EventMigration extends Event {
    private long date;
    private EventManager manager;
    private Migration migration;

    public EventMigration(long date, Migration migration, EventManager manager){
	super(date);
	this.manager = manager;
	this.migration = migration;
    }

    public void execute(){
	migration.updateVoisins();
	Cellule[] tampon = new Cellule[migration.getCellules().length];
	for (int k = 0; k < migration.getCellules().length; k++) {
	    tampon[k] = new Cellule();
	    tampon[k].setStates(migration.getStates());
	}
	for (int k = 0; k < migration.getCellules().length; k++) {
	    if (migration.getCellules()[k].getAlive() == migration.getStates() - 1) {
		if (migration.getCellules()[k].getVoisins().length >= 3) {
		    tampon[k].setAlive(0);
		} 
		else {
		    tampon[k].setAlive(migration.getStates() - 1);
		}
	    } 
	    else {
		if (migration.getCellules()[k].getVoisins().length >= 3) {
			tampon[k].setAlive(migration.getCellules()[k].getAlive() + 1); 
		    } 
		else {
			tampon[k].setAlive(migration.getCellules()[k].getAlive());
		}
	    }
	}
	migration.setCellules(tampon);
	manager.addEvent(new EventMigration(this.getDate() + 1, migration, manager));
	migration.drawCells();
    }
}
