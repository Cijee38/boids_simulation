# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testGUI balls simulator conway schelling migration ensemble

testGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGUI.java
balls:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBalls.java
simulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBallsSimulator.java
conway:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestConway.java
schelling:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestSchelling.java
migration:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestMigration.java
ensemble:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBoids.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM

exeBalls:
	java -classpath bin:bin/gui.jar TestBalls

exeSimulator:
	java -classpath bin:bin/gui.jar TestBallsSimulator

exeGUI:
	java -classpath bin:bin/gui.jar TestGUI
exeConway:
	java -classpath bin:bin/gui.jar TestConway
exeSchelling:
	java -classpath bin:bin/gui.jar TestSchelling
exeMigration:
	java -classpath bin:bin/gui.jar TestMigration
exeBoids:
	java -classpath bin:bin/gui.jar TestBoids
clean:
	rm -rf bin/*.class
	rm -rf src/*.class
