package Model;
import Controller.*;

public class Torpilleur extends Bateau{
	
	private final static int vieT = Bateau.VIE;	// nb de vie 
	private final static int tailleTorpilleur = 2;	// taille 
	private final static int porteeTorpilleur = 5;	// portee 
	
	public Torpilleur(int i) {
		super(tailleTorpilleur,porteeTorpilleur,i,vieT);
	}

}