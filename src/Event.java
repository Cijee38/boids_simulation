
public abstract class Event {
    /**
       Classe abstraite définissant un objet Event
     */
    private long date;

    public Event(long d) {
	this.date = d;
    }
    
    public long getDate() {
	return(this.date);
    } 

    public abstract void execute();
}
