package Model;
import Controller.*;

public class SousMarin extends Bateau{

	private final static int vieSM = Bateau.VIE;	// nb de vie 
	private final static int tailleSM = 3;	// taille 
	private final static int porteeSM = 4;	// portee
	private final static String nameSM = "Sous Marin";

	public SousMarin(int i) {
		super(tailleSM,porteeSM,i,vieSM,nameSM);
	}
}
