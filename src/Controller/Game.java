package Controller;

import java.util.Scanner;

import Model.*;

public class Game implements Runnable{

	public final static int TAILLE = 10;
	private boolean jeuEnCours = true;
	private Scanner in = new Scanner(System.in);

	
	public void run() {
		
		
		while(jeuEnCours) {
			//On demande le nom joueur 1
			System.out.println("Veuillez entrer le nom du Joueur 1 :");
			String j1 = in.nextLine();
			
			//On demande le nom joueur 2
			System.out.println("Veuillez entrer le nom du Joueur 2 :");
			String j2 = in.nextLine();
			
			//Création des objets joueurs
			Joueur J1 = new Joueur(j1);
			Joueur J2 = new Joueur(j2);
			
			//Placement des bateaux
			
			
			//Tirs des joueurs
			
			
			//fin de partie, on demande aux joueurs s'ils veulent rejouer
			demanderNouvellePartie();
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
