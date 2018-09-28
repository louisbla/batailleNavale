package Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import Exception.*;
import Model.*;
import view.*;

public class Game implements Runnable {

	public static final int TAILLE = 10;
	public static final int NB_DEPLACEMENT = 2;
	private final int DELAI = 1000;
	
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

		while (J1.getNbBateau() != 0 && J2.getNbBateau() != 0) {

			tourJoueur(J1, J2);
			try {Thread.sleep(DELAI);} catch (InterruptedException e) {e.printStackTrace();}	// delai entre chaque tour 

			if(J1.getNbBateau() != 0 && J2.getNbBateau() != 0) { // test de partie en cours
				tourJoueur(J2, J1);
				try {Thread.sleep(DELAI);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}

		//Afficher le gagnant
		if (J1.getNbBateau() > 0) {
			System.out.println(J1.getName() + " gagne la partie !!");
		}else {
			System.out.println(J2.getName() + " gagne la partie !!");
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

		//Placement des bateaux
		System.out.println("----------------Placement des bateaux de " + j.getName() + "----------------");
		for (int i = 0; i < j.getNbBateau(); i++) {
			Affichage.afficheGrille(j.getGrille());
			try {
				in = new Scanner(System.in);

				System.out.println("Placer le bateau de type : " + j.getBateauList().get(i).getName() + " | Taille : " + j.getBateauList().get(i).getTaille());
				System.out.print("Position X (entre 0 et " + (TAILLE - 1) + ") : ");
				x = in.nextInt();
				System.out.print("Position Y (entre 0 et " + (TAILLE - 1) + ") : ");
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
		System.out.println("==============================================");
		System.out.println("Au tour de " + j1.getName() + " de tirer !");

		//Afficher la grille avec le tir précédent et les bateaux en vie
		Affichage.afficheGrille(j1.getGrille());
		Affichage.afficheBateauEnVie(j1);
		//Tirs du joueur
		phaseDeTir(j1, j2);

		//Deplacement si tir rate
		if (j1.getDernierTir()[2] == 0) {
			phaseDeplacement(j1);
		}
	}

	public void phaseDeplacement(Joueur j1){
		try {
			Affichage.afficheGrille(j1.getGrille());
			int choix = choixBateau(j1);
			for (int i = 0; i < NB_DEPLACEMENT; i++) {
				deplacement(j1,j1.getBateau(choix));
				Affichage.afficheGrille(j1.getGrille());
			}
		}catch (InputMismatchException e) {
			System.out.println("Le paramètre entré n'était pas bon, veuillez réessayer.");
			phaseDeplacement(j1);
		}catch (ExceptionChoixBateau e) {
			System.out.println(e.getMessage());
			phaseDeplacement(j1);
		}



	}

	/*deplacer de une case le bateau precedament selectione*/
	public void deplacement(Joueur j, Bateau boat){
		in = new Scanner(System.in);
		System.out.print("Direction (haut,bas,gauche,droite,rien) : ");
		String y = in.next();
		try {
			switch (y) {
			case "haut":
				j.moveUp(boat);
				break;
			case "bas":
				j.moveDown(boat);
				break;
			case "gauche":
				j.moveLeft(boat);
				break;
			case "droite":
				j.moveRight(boat);
				break;
			case "rien":break;

			default:
				System.out.println("Mauvaise commande, reessayez.");
				deplacement(j,boat);
				break;
			}
		} catch (ExceptionMouvement | ExceptionPlacement e) {
			System.out.println(e.getMessage());
			deplacement(j,boat);
		}
	}

	/*Permet presenter les bateau dispo et recoit la reponse du joueur*/
	public int choixBateau(Joueur j) {
		in = new Scanner(System.in);
		String dispo = "";
		for (int i = 0; i < j.getBateauList().size(); i++) {
			dispo += j.getBateauList().get(i).getId() + ", ";
		}
		System.out.print("Choix du Bateau à utiliser (" + dispo + ") : ");
		int choix = in.nextInt();
		return choix;
	}

	public void phaseDeTir(Joueur j1, Joueur j2) {
		try {
			int choix = choixBateau(j1);

			System.out.print("Coordonnée sur x :");
			int x = in.nextInt();

			System.out.print("Coordonnée sur y :");
			int y = in.nextInt();

			j1.tirer(j2, x, y, j1.getBateau(choix));	// tirer avec le bateau d'id 'choix'
		} catch (ExceptionTir | ExceptionChoixBateau e) {
			System.out.println(e.getMessage());
			phaseDeTir(j1, j2);
		}
		j2.updateBateau();	// mise a jour de la grille et du nb de bateau
	}

	private void demanderNouvellePartie() {
		String rejouer = "";
		while (!rejouer.equals("oui") && !rejouer.equals("non")) {
			System.out.println("Rejouer ?  (Taper 'oui' ou 'non')");
			rejouer = in.nextLine();
		}
		if (rejouer.equals("oui")) {
			this.run();		// on recommence la partie
		}
	}
}
