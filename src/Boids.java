import gui.* ;
import java.awt.Color ;
import java.awt.Point;
import java.lang.Math;
import java.util.ArrayList;

public abstract class Boids extends Balle {
  /**
  Classe abtraite permmettant de définir les principales méthodes propres aux Boids;
  Partie 4
  Equipe 78
  */
  private ArrayList<Boids> voisins = new ArrayList<Boids>();
  private int[] forceG= new int[2];
  private int[] forceA = new int[2];
  private int[] forceS= new int[2];
  private int[] vitesse= new int[2];
  private int[] rayons = new int[2];
  private static int vitesseMax = 10;
  private int[] acceleration= new int[2];
  private int[] orientation= new int[2];
  private int[] init = new int[5];
  private double vision;
  private String name;
  private String color;
  private boolean isPrey;
  private int alive = 0; //0: vivant, 1 : mort, 2: vient detre mangé
  private boolean sex; //true : female
  private int isPregnant = 0;
  public Boids(int x, int y, int Vx, int Vy, double vision, int r1, int r2, String name, String color, boolean isPrey) {
    super(x, y, 1, 1);
    this.vitesse[0] = Vx;
    this.vitesse[1] = Vy;
    this.init[0] = x;
    this.init[1] = y;
    this.init[2] = Vx;
    this.init[3] = Vy;
    this.init[4] = r1;
    this.vision = vision;
    this.rayons[0] = r1;
    this.rayons[1] = r2;
    this.name = name;
    this.color = color;
    this.isPrey = isPrey;
    this.sex = Math.random() < 0.5;
  }
  //methodes pour le restart!!
  public void restart(){
    /*
    reinitialise le Boids
    */
    this.setX(this.init[0]);
    this.setY(this.init[1]);
    this.vitesse[0] = init[2];
    this.vitesse[1] = init[3];
    this.resetForces();
    this.resetVoisins();
    this.alive = 0;
    this.rayons[0] = init[4];
  }
  public void resetVoisins(){
    this.voisins = new ArrayList<Boids>();
  }
  public void resetForces(){
    this.forceG[0] = 0;
    this.forceG[1] = 0;
    this.forceA[0] = 0;
    this.forceA[1] = 0;
    this.forceS[0] = 0;
    this.forceS[1] = 0;
  }

  //methodes accesseurs
  public void setAcceleration(int x, int y) {
    this.acceleration[0] = x;
    this.acceleration[1] = y;
  }
  public void setOrientation(int x, int y) {
    this.orientation[0] = x;
    this.orientation[1] = y;
  }

  public void setVitesse(int x, int y) {
    this.vitesse[0] = x;
    this.vitesse[1] = y;
  }

  public void setAlive(int vie) {
    this.alive = vie;
  }
  public int getAlive() {
    return this.alive;
  }
  public void setCoord(int x, int y,int length,int height){
    setX(Math.floorMod(x, length));
    setY(Math.floorMod(y, height));
  }
  public boolean getType(){
    return this.isPrey;
  }
  public int[] getVitesse() {
    return vitesse;
  }
  public String getName(){
    return name;
  }
  public int[] getRayons(){
    return rayons;
  }
  public void setRayons(int r1, int r2){
    this.rayons[0] = r1;
    this.rayons[1] = r2;
  }
  public int[] getForceG(){
    return this.forceG;
  }
  public int[] getForceA(){
    return this.forceA;
  }
  public int[] getForceS(){
    return this.forceS;
  }
  public ArrayList<Boids> getVoisins(){
    return this.voisins;
  }
  public boolean getSex(){
    return this.sex;
  }
  public int getPregnant(){
    return this.isPregnant;
  }
  public void setPregnant(int etat){
    this.isPregnant = etat;
  }

  //méthodes pour la mise a jour du Boids
  public abstract void updateAcc();
  public void updateVit(){
    this.setVitesse(this.acceleration[0] + this.vitesse[0], this.acceleration[1] + this.vitesse[1]);
    this.changeNorme(vitesse);
  }
  public void updatePos(int length,int height){
    this.setCoord(this.vitesse[0] + this.getX(), this.vitesse[1] + this.getY(), length, height);
  }
  public abstract void updateForces(int length, int height);
  public void ajoutVoisin(Boids b){
    this.voisins.add(b);
  }
  public boolean estVoisin(Boids b, int rayon, int length, int height){
    /*
    La fonction retourne un bouléen true si le boids est dans le voisinage
    les variable bool1, bool2, bool3 et bool4 servent à gérer les cas limites lorsque
    les boids sont d'une part et d'autre d'un mur
    */
    boolean bool1 = distance(b) < rayon && distance(b) != 0;
    boolean bool2 = distance(b.getX() + length, b.getY()) < rayon;
    boolean bool3 = distance(b.getX(), height + b.getY()) < rayon;
    boolean bool4 = distance(b.getX() + length, height + b.getY()) < rayon;
    boolean result = ( bool1 || bool2 || bool3 || bool4);
    return result;
  }
  //methodes de calcul des forces
  public void forceSeparation(int length, int height){
      /**
    Calcul la force de séparation entre les boids
    */
    int[] somme = new int[2];
    int count = 0;
    int[] vecteurTemporaire = new int[2];
    for (Boids b : this.voisins){
      // Si le boids b  est du meme type que notre boids
      if (this.name.equals(b.name)){
        count ++;
        double distance = this.distance(b);
        if (distance < 2 * rayons[0]){
          /*
          Si distance < 2 * rayons on est dans le cas général
          */
          vecteurTemporaire[0] =(this.getX() - b.getX());
          vecteurTemporaire[1] =(this.getY() - b.getY());
          somme = sommeVecteur(somme, vecteurTemporaire);
        }else if (distance > (length - 100)){
          // Sinon ils sont d'une part et d'autre du mur
          if (Math.abs(this.getX() - b.getX())>20){
            if (b.getX()>this.getX()){
              vecteurTemporaire[0] = this.getX() - (length - b.getX());
              vecteurTemporaire[1] = this.getY() - b.getY();
              somme = sommeVecteur(somme, vecteurTemporaire);

            }else{
              vecteurTemporaire[0] = length + b.getX() - this.getX();
              vecteurTemporaire[1] = this.getY() - b.getY();
              somme = sommeVecteur(somme, vecteurTemporaire);
            }
          }else{
            if (b.getY() > this.getY()){
              vecteurTemporaire[0] = this.getX() - b.getX();
              vecteurTemporaire[1] = this.getY() - (height - b.getY());
              somme = sommeVecteur(somme, vecteurTemporaire);
            }else{
              vecteurTemporaire[0] = this.getX() - b.getX();
              vecteurTemporaire[1] = height + b.getY() - this.getY();
              somme = sommeVecteur(somme, vecteurTemporaire);
            }
          }

        }
      }
    }
    if (count != 0){
      forceS[0] = somme[0];
      forceS[1] = somme[1];
      changeNorme(forceS);
      multiplication(3, forceS); // On multiplie par un facteur 3 pour amplifier cette force
    }else{
      forceS[0] = 0;
      forceS[1] = 0;
    }
  }

  public void forceGravitation(int length, int height){
    /*
    Calcul la force de cohésion entre les boids
    */
    int xMoy = 0;
    int yMoy = 0;
    int count = 0;
    for (Boids b : this.voisins){

      if (this.name.equals(b.name)){
        count ++;

        if(this.distance(b) < 100){
          xMoy += b.getX();
          yMoy += b.getY();
        }else if(distance(b.getX() + length, height + b.getY()) < 100){
          if(this.getX() > b.getX()){
            if(this.getY() > b.getY()){
              xMoy += b.getX() + length;
              yMoy += b.getY() + height;
            }else{
              xMoy += b.getX() + length;
              yMoy += b.getY() - height;
            }
          }else{
            if(this.getY() > b.getY()){
              xMoy += b.getX() - length;
              yMoy += b.getY() + height;
            }else{
              xMoy += b.getX() - length;
              yMoy += b.getY() + height;
            }
          }
        }else if(distance(b.getX() + length, b.getY()) < 100){
          if(this.getX() > b.getX()){
            xMoy += b.getX() + length;
            yMoy += b.getY();
          }else{
            xMoy += b.getX() - length;
            yMoy += b.getY();
          }
        }else if(distance(b.getX(), height + b.getY()) < 100){
          if(this.getY() > b.getY()){
            xMoy += b.getX();
            yMoy += b.getY() + height;
          }else{
            xMoy += b.getX();
            yMoy += b.getY() - height;
          }
        }
      }
    }
    if (count != 0){
      xMoy = xMoy/ count;
      yMoy = yMoy / count;
      double d = this.distance(xMoy,yMoy);
      this.forceG[0] = (int) ((xMoy - this.getX())) ;
      this.forceG[1] = (int) ((yMoy - this.getY()));
      changeNorme(forceG); //on normalise
    }else{
      forceG[0] = 0;
      forceG[1] = 0;
    }

  }



  public void forceAlignement(){
    /*
    Calcul la force d'alignement entre les boids
    */
    int count = 0;
    if (this.voisins.size() != 0){
      int sommeVitesseX = 0;
      int sommeVitesseY = 0;
      for (Boids b : this.voisins){
        if (this.name.equals(b.name)){
          count ++;
          sommeVitesseX += b.vitesse[0];
          sommeVitesseY += b.vitesse[1];
        }
      }
      if (count != 0){
        forceA[0] = sommeVitesseX;
        forceA[1] = sommeVitesseY;
        changeNorme(forceA); //On normalise
      }else{
        forceA[0] = 0;
        forceA[1] = 0;
      }
    }
    else{
      forceA[0] = 0;
      forceA[1] = 0;
    }
  }

  //méthodes pratiques
  public int[] calculCentreTete(){
    /*
    calcul le coordonnée du centre de la tete du Boids
    */
    double norme = Math.sqrt(vitesse[0] * vitesse[0] + vitesse[1] * vitesse[1]);
    double dist = (rayons[0] + rayons[1]) / norme;
    double x = dist * vitesse[0];
    double y = dist * vitesse[1];
    int[] result = {(int)x + getX(), (int)y + getY()};
    return result;
  }

  public boolean dansVision(Boids b){
    //verifie quun Boids est bien dans le champs de vision
    //calcul de la distance du boids a la droite directionnel (prejection ortho)
    int[] dir = this.getVitesse();
    double[] droite = this.calcul_droite();
    double dist = Math.abs(droite[0] * b.getX() + b.getY() + droite[1]) /
    Math.sqrt(Math.pow(droite[0], 2) + 1);
    double angle = Math.asin(dist / this.distance(b));
    // verification de la pos en fonction de la direction
    return !(angle < Math.PI - vision) || ((b.getX() - this.getX()) * dir[0] > 0 && (b.getY() - this.getY()) * dir[1] > 0 );
  }

  public void draw(GUISimulator window){
    /*
    déssine le boids, en fonction de sa couleur et de son rayon
    */
    if(this.sex){
      window.addGraphicalElement(new Oval(getX(),
      getY(),
      Color.decode(color),
      Color.decode(color),
      rayons[0] * 2));
      window.addGraphicalElement(new Oval(calculCentreTete()[0],
      calculCentreTete()[1],
      Color.decode("#ff0000"),
      Color.decode("#ff0000"),
      rayons[1] * 2));
    }else{
      window.addGraphicalElement(new Oval(getX(),
      getY(),
      Color.decode(color),
      Color.decode(color),
      rayons[0] * 2));
      window.addGraphicalElement(new Oval(calculCentreTete()[0],
      calculCentreTete()[1],
      Color.decode("#ffffff"),
      Color.decode("#ffffff"),
      rayons[1] * 2));
    }
  }

  public void changeNorme(int[] vecteur){
    /*
    ramène la norme du vecteur à vitesseMax en conservant la direction et le sens du vecteur
    */
    int norme = vitesseMax;
    double facteurVitesse = Math.abs(vecteur[1] / (double)vecteur[0]);
    if (Math.sqrt(Math.pow(vecteur[0], 2) + Math.pow(vecteur[1],2)) > norme){
      if (vecteur[0] > 0){
        vecteur[0] = (int) ((double)norme / (Math.sqrt(1 + Math.pow(facteurVitesse,2))));
        vecteur[1] = (int) (facteurVitesse * (double)vecteur[0]);
      }else{
        vecteur[0] = (int) - ((double)norme / (Math.sqrt(1 + Math.pow(facteurVitesse,2))));
        vecteur[1] = (int) (facteurVitesse * (double)vecteur[0]);
      }
    }
  }
  public double[] calcul_droite(){
    /*
    permet de calculer lequation de la droite correspondant a la direction du boids
    D: ax + y + c =0
    */
    double coef = (double) vitesse[1] / (double)vitesse[0];
    double c = (double) (this.getY() - coef * this.getX());
    double[] result = {coef, c};
    return result;
  }

  public int[] sommeVecteur(int[] vecteur1, int[] vecteur2){
    /*
    somme les vecteurs 1 et 2
    */
    int[] resultat = new int[2];
    resultat[0] = vecteur1[0] + vecteur2[0];
    resultat[1] = vecteur1[1] + vecteur2[1];
    return resultat;
  }

  public void multiplication(int k, int[] vecteur){
    vecteur[0] *= k;
    vecteur[1] *= k;
  }

  public double distance(Boids b){
    return Math.sqrt((this.getX() - b.getX()) * (this.getX() - b.getX()) + (this.getY() - b.getY()) * (this.getY() - b.getY()));
  }
  public double distance(int x, int y){
    return Math.sqrt((this.getX() - x) * (this.getX() -x) + (this.getY() - y) * (this.getY() - y));
  }
  public void division(int facteur, int[] vecteur){
    vecteur[0] = (int) ((float) vecteur[0] / facteur);
    vecteur[1] = (int) ((float) vecteur[1] / facteur);
  }
}
