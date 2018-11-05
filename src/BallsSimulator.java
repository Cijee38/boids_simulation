import gui.* ;
import java.awt.Color ;
import gui.Oval;
import java.awt.Point;


public class BallsSimulator implements Simulable {
  private Balls balles;
  private GUISimulator window;
  private EventManager manager;
  public BallsSimulator(){
    this.balles = new Balls();
    new GUISimulator (500 , 500 , Color.BLACK);
    manager = new EventManager();
    manager.addEvent(new EventBalls(0, this, manager));
  }
  public BallsSimulator(int n, GUISimulator gui){
    this.balles = new Balls(n);
    this.window = gui;
    manager = new EventManager();
    manager.addEvent(new EventBalls(0, this, manager));
  }
  public BallsSimulator(int[] coord, GUISimulator gui){
    this.balles = new Balls(coord);
    this.window = gui;
    manager = new EventManager();
    manager.addEvent(new EventBalls(0, this, manager));
  }

    public GUISimulator getWindow(){
	return window;
    }

    public Balls getBalls() {
	return balles;
    }
  @Override
  public void next(){
    manager.next();
    //this.balles.translate(10, 10);
    //window.reset();
    //for (Balle b : this.balles.getBalls()){
    //  window.addGraphicalElement(
    //  new Oval(b.getX() , b.getY() ,
    //  Color.decode("#1f77b4"),
    //  Color.decode("#1f77b4"),
    //  10));
    // }
  }
  @Override
  public void restart () {
    this.balles.reInit();
    window.reset();
    for (Balle b : this.balles.getBalls()){
      window.addGraphicalElement(
      new Oval(b.getX() , b.getY() ,
      Color.decode("#1f77b4"),
      Color.decode("#1f77b4"),
      10));
    }
  }
}
