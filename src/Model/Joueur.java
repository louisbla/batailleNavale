package Model;
import java.util.ArrayList;

import Controller.Game;
import Exception.*;

public class Joueur {

	private String name;					// nom du joueur
	private ArrayList<Bateau> bateauList;	// Liste des bateaux
	private int nbBateau;					// nb de bateau encore en vie
	private int[][] grille;					// grille de placement des bateaux selon leur id
	private int[] dernierTir = new int[3];	// {x,y,touche} x,y coordonne du dernier, tir touche=1 si touche 0 sinon

	{
		bateauList = new ArrayList<Bateau>();
		initialisationJoueur();
		grille = new int[Game.TAILLE][Game.TAILLE];
	}
	
	public Joueur() {name="";}
	public Joueur(String n) {name=n;}

	/*ajout des bateaux*/
	public void initialisationJoueur() {
		bateauList.add(new PorteAvion(1));	// porte avion avec id en parametre
		bateauList.add(new Croiseur(2));	// croiseur
		bateauList.add(new ContreTorpilleur(3));	// contre torpilleur
		bateauList.add(new SousMarin(4));	// sous-marin
		bateauList.add(new Torpilleur(5));	// torpilleur
		setNbBateau(bateauList.size());		// tout les bateau sont en vie
	}

	/* verifier si on peut placer un bateau du joueur J au point (x,y) selon ses autres bateaux
	 - (x,y) = point le plus en haut à gauche de bateau
	 - isVertical = est il a la verticale ou non */
	public boolean checkPlacement( Bateau boat ,int X, int Y, boolean vertical) {
		boolean placeDispo = false;
		// test du bateau dans la grille en premier pour eviter le out of range au second test
		if(this.checkPlacementDansGrille(boat, X, Y, vertical)) {
			placeDispo = this.checkPlacementAvecBateaux(boat, X, Y, vertical);
		}
		return placeDispo;
	}
	
	public boolean checkTir(Bateau boat, int X, int Y) {
		boolean tirPossible = true;
		
		if(X<0 || X>=this.grille.length || Y<0 || Y>=this.grille[0].length) { // verification si point (x,y) dans la grille
			tirPossible = false;
		}
		if(boat.isVertical) {
			if(Y < boat.y - boat.porte || Y > boat.y + boat.taille + boat.porte - 1) {
				tirPossible = false;
			}else if(Y < boat.y && X != boat.x) {
				tirPossible = false;
			}else if(Y >= boat.y + boat.taille && X != boat.x) {
				tirPossible = false;
			}else if(X < boat.x - boat.porte || X > boat.x + boat.porte) {
				tirPossible = false;
			}
		}else {
			if(X < boat.x - boat.porte || X > boat.x + boat.taille + boat.porte - 1) {
				tirPossible = false;
			}else if(X < boat.x && Y != boat.y) {
				tirPossible = false;
			}else if(X >= boat.x + boat.taille && Y != boat.y) {
				tirPossible = false;
			}else if(Y < boat.y - boat.porte || Y > boat.y + boat.porte) {
				tirPossible = false;
			}
		}
		return tirPossible;
	}
	
	// verifie que le bateau de J ne sort pas de la grille au coordone (x,y)
	public boolean checkPlacementDansGrille(Bateau b ,int X, int Y, boolean vertical) {
		boolean check = true;
		int l =b.getTaille();
		if(X<0 || X>=this.grille.length || Y<0 || Y>=this.grille[0].length) { // verification si point (x,y) dans la grille de J
			check = false;
		}
		if(vertical) {
			if( (Y+l-1)>=this.grille[0].length) { // verification si le bout du bateau dans la grille de J
				check = false;
			}
		}else {
			if( (X+l-1)>=this.grille.length) { // verification si le bout du bateau dans la grille de J
				check = false;
			}
		}
		return check;
	}

	// verifie que le bateau de J ne chevauche pas sur d autre de ses bateaux
	public boolean checkPlacementAvecBateaux(Bateau b ,int X, int Y, boolean vertical) {
		boolean check = true;
		int l =b.getTaille();
		if(vertical) {	// test vertical
			for (int i = 0; i < l; i++) {
				if(this.grille[Y+i][X]!=0 && this.grille[Y+i][X]!=b.getId() ) {// test si la case est occupe par un bateau different
					check=false;
				}
			}
		}else {			// test horizontal
			for (int i = 0; i < l; i++) {
				if(this.grille[Y][X+i]!=0 && this.grille[Y][X+i]!=b.getId() ) {// test si la case est occupe par un bateau different
					check=false;
				}
			}
		}
		return check;
	}
	
	/*palce un bateau dans la grille de J, et lui affecte les coordonne*/
	public void placerBateau( Bateau boat,int X, int Y, boolean vertical) throws ExceptionPlacement {
		if(checkPlacement(boat, X, Y, vertical)) {
			boat.setX(X);
			boat.setY(Y);
			boat.setVertical(vertical);
			
			if(vertical) {
				for (int i = 0; i <boat.getTaille(); i++) {
					grille[Y+i][X]=boat.getId();
				}
			}else {
				for (int i = 0; i <boat.getTaille(); i++) {
					grille[Y][X+i]=boat.getId();
				}
			}
		}else {
			throw new ExceptionPlacement("Placement du bateau interdit. Veuillez réessayer.");
		}
	}

	/*Enleve le bateau de la grille, sans affecter ses coordonnes*/
	public void enleverBateau( Bateau boat) {
		int x = boat.getX();
		int y = boat.getY();
		if(boat.getVertical()) {
			for (int i = 0; i < boat.getTaille(); i++) {
				this.grille[y+i][x]=0;
			}
		}else {
			for (int i = 0; i < boat.getTaille(); i++) {
				this.grille[y][x+i]=0;
			}
		}
	}
	
	/*verifie et bouge le bateau vers le haut, bas, gauche ou droite*/
	public void moveUp(Bateau boat) throws ExceptionPlacement, ExceptionMouvement {
		if(this.checkPlacement(boat, boat.getX(), boat.getY()-1, boat.getVertical() )) {
			this.enleverBateau(boat);
			this.placerBateau(boat, boat.getX(), boat.getY()-1, boat.getVertical());
		}else {
			throw new ExceptionMouvement("Deplacement vers le haut impossible, réessayez");
		}
	}
	
	public void moveDown(Bateau boat) throws ExceptionPlacement, ExceptionMouvement {
		if(this.checkPlacement(boat, boat.getX(), boat.getY()+1, boat.getVertical() )) {
			this.enleverBateau(boat);
			this.placerBateau(boat, boat.getX(), boat.getY()+1, boat.getVertical());
		}else{
			throw new ExceptionMouvement("Deplacement vers le bas impossible, réessayez");
		}
	}
	
	public void moveLeft(Bateau boat) throws ExceptionPlacement, ExceptionMouvement {
		if(this.checkPlacement(boat, boat.getX()-1, boat.getY(), boat.getVertical() )) {
			this.enleverBateau(boat);
			this.placerBateau(boat, boat.getX()-1, boat.getY(), boat.getVertical());
		}else{
			throw new ExceptionMouvement("Deplacement vers la gauche impossible, réessayez");
		}
	}
	
	public void moveRight(Bateau boat) throws ExceptionPlacement, ExceptionMouvement {
		if(this.checkPlacement(boat, boat.getX()+1, boat.getY(), boat.getVertical() )) {
			this.enleverBateau(boat);
			this.placerBateau(boat, boat.getX()+1, boat.getY(), boat.getVertical());
		}else{
			throw new ExceptionMouvement("Deplacement vers la droite impossible, réessayez");
		}
	}
	
	public void Rotation(Bateau boat) throws ExceptionPlacement, ExceptionMouvement {
		if(this.checkPlacement(boat, boat.getX(), boat.getY(), !boat.getVertical() )) {
			this.enleverBateau(boat);
			this.placerBateau(boat, boat.getX(), boat.getY(), !boat.getVertical());
		}else{
			throw new ExceptionMouvement("Rotation impossible, réessayez");
		}
	}
	
	/*Tire sur un adversaire en x,y avec un bateau boat*/
	public boolean tirer(Joueur j2, int x, int y, Bateau boat) throws ExceptionTir {
		if(checkTir(boat, x, y)) {
			dernierTir[0]=x;
			dernierTir[1]=y;
			int idTir = j2.getGrille()[x][y];
			
			if(idTir!=0) {			// test si tir reussi
				dernierTir[2]=1;
				j2.getBateau(idTir).Touche();
				j2.updateBateau();	// mise a jour de la grille et du nb de bateau
				System.out.println("Touche !");

				return true;
			}else {
				dernierTir[2]=0;
				System.out.println("Rate");
				return false;
			}
		}else {
			throw new ExceptionTir("Tir interdit, reessayez.");
		}
	}
	
	public void updateBateau() {		// mise a jour de la liste des bateau et le nb de bateau en vie lorsque l'un est detruit
		for (int i = 0; i < bateauList.size(); i++) {
			if(bateauList.get(i).getVie()==0) {
				enleverBateau(bateauList.get(i));
				nbBateau--;
				System.out.println(bateauList.get(i).getName()+" detruit !");
			}
		}
	}
	
	/*getter et setter*/
	public Bateau getBateau(int i) {		// retourn un bateau de la liste de J selon son identfiant
		Bateau b = null;
		for (int j = 0; j < this.bateauList.size(); j++) {
			if(this.bateauList.get(j).getId()==i) {
				b=this.bateauList.get(j);
			}
		}
		return b;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Bateau> getBateauList() {
		return bateauList;
	}
	public void setBateauList(ArrayList<Bateau> list) {
		bateauList = list;
	}

	public int getNbBateau() {
		return nbBateau;
	}

	public void setNbBateau(int nbBateau) {
		this.nbBateau = nbBateau;
	}
	public int[][] getGrille(){
		return grille;
	}
	public int[] getDernierTir() {
		return dernierTir;
	}
	

}
