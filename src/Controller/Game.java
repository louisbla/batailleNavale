package Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import Exception.ExceptionPlacement;
import Exception.ExceptionTir;
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
		
		while(J1.getNbBateau() != 0 && J1.getNbBateau() != 0) {
			tourJoueur(J1, J2);
			tourJoueur(J2, J1);
		}
		
		//Afficher le gagnant
		
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
		
		//Placement des bateaux
		System.out.println("----------------Placement des bateaux de " + j.getName() + "----------------");
		for (int i = 0; i < j.getNbBateau(); i++) {
			try {
				in = new Scanner(System.in);
				
				System.out.println("Placer le bateau de type : " + j.getBateauList().get(i).getName() + " | Taille : " + j.getBateauList().get(i).getTaille());
				System.out.print("Position X : ");
				x = in.nextInt();
				System.out.print("Position Y : ");
				y = in.nextInt();
				System.out.print("Vertical(true ou false) : ");
				isVertical = in.nextBoolean();
				
				j.placerBateau(j.getBateauList().get(i), x, y, isVertical);
			} catch (InputMismatchException e) {
				System.out.println("Le paramètre entré n'était pas bon, veuillez réessayer.");
				i--;
			}catch (ExceptionPlacement e) {
				System.out.println(e.getMessage());
				i--;
			}
		}
	}
	
	public void tourJoueur(Joueur j1, Joueur j2) {
		//Afficher la grille avec le tir précédent
		
		//Tirs des joueurs
		tirer(j1, j2);
		
		//Effectuer le tir
		
		//Deplacement si tir rate
		
	}
	
	public void tirer(Joueur j1, Joueur j2) {
		System.out.println("Au tour de " + j1.getName() + " de tirer !");
		System.out.print("Choix Bateau(1, 2, 3, 4, 5) : ");
		int choix = in.nextInt();
		
		System.out.print("Coordonnée sur x :");
		int x = in.nextInt();
		
		System.out.print("Coordonnée sur y :");
		int y = in.nextInt();
		
		try {
			j1.tirer(j2, x, y, j1.getBateauList().get(choix - 1));
		} catch (ExceptionTir e) {
			System.out.println(e.getMessage());
			tirer(j1, j2);
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
