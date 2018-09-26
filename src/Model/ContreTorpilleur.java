package Model;
import Controller.*;

public class ContreTorpilleur extends Bateau{
	
	private final static int vieCT = Bateau.VIE;	// nb de vie 
	private final static int tailleCT = 3;	// taille 
	private final static int porteeCT = 2;	// portee 
	private final static String nameCT = "Contre Torpilleur";
	
	public ContreTorpilleur(int i) {
		super(tailleCT,porteeCT,i,vieCT,nameCT);
	}

}
