package Controller;
import java.util.Random;
import java.util.Scanner;

import Model.*;

public class main {
	
	public static void main(String[] args) {

		Joueur J1 = new Joueur();
		J1.placerBateau(J1.getBateauList().get(0), 5, 5, true);
		J1.placerBateau(J1.getBateauList().get(1), 6,0, false);
		afficheGrille(J1.getGrille());
		J1.moveDown(J1.getBateauList().get(1));
		System.out.println();
		afficheGrille(J1.getGrille());
		
		/*methode jeu*/
	
	}
	
	public static void afficheGrille(int [][] tab) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				System.out.print("|"+tab[i][j]);
			}
			System.out.print("|\n");
		}
	}
}
