package Model;
import Controller.*;

public class Croiseur extends Bateau{

	private final static int vieC = Bateau.VIE;	// nb de vie 
	private final static int tailleCroiseur = 4;	// taille 
	private final static int porteeCroiseur = 2;	// portee 

	public Croiseur(int i) {
		super(tailleCroiseur,porteeCroiseur,i,vieC);
	}
}
