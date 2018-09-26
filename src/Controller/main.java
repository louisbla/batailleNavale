package Controller;
import java.util.Random;
import java.util.Scanner;

import Model.*;

public class main {
	
	//fonction main, executee au demarrage
	public static void main(String[] args) {
		
		Game partie = new Game();
		partie.run();

	
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
