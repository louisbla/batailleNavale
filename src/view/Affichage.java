package view;

import Model.*;

public class Affichage {

	public static void afficheGrille(int [][] tab) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				System.out.print("|" + tab[i][j]);
			}
			System.out.print("|\n");
		}
		System.out.println();
	}
	
	public static void afficheBateauEnVie(Joueur j) {	// affiche chaque bateau en vie avec son id et sa vie
		System.out.println("----Bateau Disponible----");
		for (int i = 0; i < j.getBateauList().size(); i++) {
			Bateau b = j.getBateauList().get(i);
			System.out.println(b.getName() + "(" + b.getId() + ")  :    Vie = " + b.getVie());
		}
	}
}
