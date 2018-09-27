package Model;
import Controller.Game;

public class Bateau {

	protected String name;
	protected int taille;
	protected int porte;
	protected int vie;
	protected int id;		// numero identitaire du bateau
	protected int x;		// case la plus a gauche dans la grille 
	protected int y;		// case la plus en haut dans la grille 
	protected boolean isVertical;	// definit si le bateau est a la vertical ou non
	
	public static final int VIE = 3; // vie identique à chaque bateau de base
	
	public Bateau(int i){
        taille = 0;
        porte = 0;
        vie = VIE;
        id = i;
        x = -1; y = -1;	// initialement pas sur la grille
        isVertical = false; // initialement a l horizontal
    }
    
    public Bateau(int t, int p, int i){
        taille = t;
        porte = p;
        vie = VIE;
        id = i;
        x = -1; y = -1;
        isVertical = false;
    }
    
    public Bateau(int t, int p, int i, int v, String n){
        taille = t;
        porte = p;
        vie = VIE;
        id = i;
        x = -1; y = -1;
        isVertical = false;
        name = n;
    }
	
    public void Touche() {
    	vie--;
    }

    public String getName() {
		return name;
	}
    
	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getPorte() {
		return porte;
	}

	public void setPorte(int porte) {
		this.porte = porte;
	}

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isVertical() {
		return isVertical;
	}

	public void setVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}
	
	public boolean getVertical() {
		return this.isVertical;
	}
}
