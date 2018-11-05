import java.util.Comparator;

public class EventComparator implements Comparator<Object> {
    public int compare(Object a, Object b){
	Event e1 = (Event) a;
	Event e2 = (Event) b;
	return Integer.compare((int)e1.getDate(), (int)e2.getDate());
    }
}
