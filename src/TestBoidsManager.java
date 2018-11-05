public class TestBoidsManager{
  public static void main(String[] args) throws InterruptedException{
	   EventManager manager = new EventManager();

	    while (!manager.isFinished()) {
	    manager.next();
	    Thread.sleep(1000);
	}
    }
}
