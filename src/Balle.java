import java.awt.Point;

public class Balle{
  private int x;
  private int y;
  private int dirX;
  private int dirY;

  public Balle(){
    this.x = 0;
    this.y = 0;
    this.dirX = 1;
    this.dirY = 1;
  }

  public Balle(int x, int y, int dirX, int dirY){
    this.x = x;
    this.y = y;
    this.dirX = dirX;
    this.dirY = dirY;
  }

  public void setDirectionX(int dirX){
    //Ajout condition si dirX != 1 ou -1 lever une erreur
    this.dirX = dirX;
  }

  public void setDirectionY(int dirY){
    //Ajout condition si dirX != 1 ou -1 lever une erreur
    this.dirY = dirY;
  }

  public void setX(int x){
    //Ajout condition si dirX != 1 ou -1 lever une erreur
    this.x = x;
  }

  public void setY(int y){
    //Ajout condition si dirX != 1 ou -1 lever une erreur
    this.y = y;
  }

  public int getDirectionX(){
    //Ajout condition si dirX != 1 ou -1 lever une erreur
    return this.dirX;
  }

  public int getDirectionY(){
    //Ajout condition si dirX != 1 ou -1 lever une erreur
    return this.dirY;
  }

  public int getX(){
    //Ajout condition si dirX != 1 ou -1 lever une erreur
    return this.x;
  }

  public int getY(){
    //Ajout condition si dirX != 1 ou -1 lever une erreur
    return this.y;
  }


  public String toString(){
    return "( " + String.valueOf(this.x) + " , " + String.valueOf(this.y) + " )";
  }
}
