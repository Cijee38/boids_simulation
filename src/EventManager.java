import java.util.PriorityQueue;

public class EventManager {
    /**
       Classe permettant de gérer des évenements en fonction de leur temps
     */
  private long currentDate = 0;
  private long initDate = 0;
  public PriorityQueue<Event> listeEvent;

  public EventManager() {
    EventComparator comp = new EventComparator();
    this.listeEvent = new PriorityQueue<Event>(1000, comp);
  }

  public EventManager(long date) {
    EventComparator comp = new EventComparator();
    this.listeEvent = new PriorityQueue<Event>(1000, comp);
    this.currentDate = date;
    this.initDate = 0;
  }

  public void addEvent(Event e){
    this.listeEvent.add(e);
  }

  public void next() {
    while(!this.listeEvent.isEmpty() && this.listeEvent.element().getDate() <= this.currentDate){
      Event e = this.listeEvent.remove();
      e.execute();
    }
    this.currentDate += 1;
  }

  public boolean isFinished() {
    return(listeEvent.isEmpty());
  }

  public void restart() {
    EventComparator comp = new EventComparator();
    this.listeEvent = new PriorityQueue<Event>(1000, comp);
    this.currentDate = this.initDate;
  }

}
