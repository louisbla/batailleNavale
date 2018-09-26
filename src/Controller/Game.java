package Controller;

import java.util.Scanner;

import Model.*;

public class Game implements Runnable{

	public final static int TAILLE = 10;

	private boolean jeuEnCours = true;
	private Scanner in = new Scanner(System.in);

	public void run() {
		Joueur J1 = new Joueur("Joueur 1");
		Joueur J2 = new Joueur("Joueur 2");
		
		//Initialisation des Noms des Joueurs
		initName(J1);
		initName(J2);
		
		//Initialisation des Bateaux
		initBateau(J1);
		initBateau(J2);
		
		while(jeuEnCours) {
			//Tirs des joueurs
			//D�placement si tir �chou�
			jeuEnCours = false;
		}
		//fin de partie, on demande aux joueurs s'ils veulent rejouer
		demanderNouvellePartie();
	}
	
	public void initName(Joueur j) {
		System.out.print("Veuillez entrer le nom du " + j.getName() + " : ");
		j.setName(in.nextLine());
	}
	
	public void initBateau(Joueur j) {
		int x;
		int y;
		boolean isVertical;
		
		//Cr�ation des bateaux
		j.initialisationJoueur();
		
		//Placement des bateaux
		System.out.println("----------------Placement des bateaux de " + j.getName() + "----------------");
		for (int i = 0; i < j.getNbBateau(); i++) {
			System.out.println("Type : " + j.getBateauList().get(i).getName() + " | Taille : " + j.getBateauList().get(i).getTaille());
			System.out.print("Position X : ");
			x = in.nextInt();
			System.out.print("Position Y : ");
			y = in.nextInt();
			System.out.print("Vertical(true ou false) : ");
			isVertical = in.nextBoolean();
			j.placerBateau(j.getBateauList().get(i), x, y, isVertical);
		}
	}
	
	private void demanderNouvellePartie() {
		String rejouer = "";
		while(!rejouer.equals("oui") && !rejouer.equals("non")) {
			System.out.println("Rejouer ?  (Taper 'oui' ou 'non')");
			rejouer = in.nextLine();
		}
		if(rejouer.equals("non")) {
			jeuEnCours = false;
		}
	}

}
