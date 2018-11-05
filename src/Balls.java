//import gui.*;

import java.awt.Point;

public class Balls {
  private Balle[] balls;
  private int[] init;


  public Balls(){
    this.balls = new Balle[1];
    balls[0] = new Balle();
    this.init = new int[2];
    this.init[0] = 0;
    this.init[1] = 0;
  }
  public Balls(int n){
    this.balls = new Balle[n];
    this.init = new int[n * 2];
    for(int k = 0; k < n; k++ ){
      balls[k] = new Balle();
      this.init[2*k] = 0;
      this.init[2 * k + 1] = 0;
    }
  }
  public Balls(int[] coord){
    if (coord.length % 2 == 1) {
      throw new ArithmeticException("tableau de taille impaire");
    }
    this.balls = new Balle[coord.length / 2];
    for(int k = 0; k < coord.length / 2 ; k++ ){
      balls[k] = new Balle(coord[2 * k], coord[2 * k + 1], 1, 1);
    }
    //this.init = new int[coord.length];
    this.init = coord;
  }
  Balle[] getBalls(){
    return this.balls;
  }
  void translate(int dx, int dy){
    for (Balle b : this.balls){
      int x = b.getX();
      int y = b.getY();
      int dirX = b.getDirectionX();
      int dirY = b.getDirectionY();
      if(x + dx * dirX > 500){
        //rebond a droite
        b.setX(1000 - (x + dx * dirX));
        b.setDirectionX(dirX * (-1));
      }else if (x + dx * dirX < 0){
        //Rebond a gauche
        b.setX(- (x + dx * dirX));
        b.setDirectionX(dirX * (-1));
      }else {
        b.setX(x + dx * dirX);
      }
      if(y + dy * dirY > 500){
        //rebond en bas
        b.setY(1000 - (y + dy * dirY));
        b.setDirectionY(dirY * (-1));

      }else if (y + dy * dirY < 0){
        //Rebond en haut
        b.setY(- (y + dy * dirY));
        b.setDirectionY(dirY * (-1));

      }else{
        b.setY(y + dy * dirY);
      }
    }
  }
  void reInit(){
    for (int k = 0; k < init.length / 2; k++){
      this.balls[k].setX(init[2 * k]);
      this.balls[k].setY(init[2 * k + 1]);
      this.balls[k].setDirectionX(1);
      this.balls[k].setDirectionY(1);

    }
  }
  @Override
  public String toString(){
    String result = "";
    for (Balle b : this.balls){
      result += b.toString();
    }
    return result;
  }
}
