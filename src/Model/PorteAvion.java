package Model;
import Controller.*;

public class PorteAvion extends Bateau{
	
	private final static int viePA = VIE;	// nb de vie porte avion
	private final static int taillePA = 5;	// taille porte avion
	private final static int porteePA = 3;	// portee porte avion
	private final static String namePA = "Porte Avion";
	
	public PorteAvion(int i) {
		super(taillePA,porteePA,i,viePA,namePA);
	}
	

}
