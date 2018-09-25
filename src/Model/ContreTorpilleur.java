package Model;
import Controller.*;

public class ContreTorpilleur extends Bateau{
	
	private final static int vieCT = Bateau.VIE;	// nb de vie 
	private final static int tailleCT = 3;	// taille 
	private final static int porteeCT = 2;	// portee 
	
	public ContreTorpilleur(int i) {
		super(tailleCT,porteeCT,i,vieCT);
	}

}
