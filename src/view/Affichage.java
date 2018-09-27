package view;

public class Affichage {

	
	
	public static void afficheGrille(int [][] tab) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				System.out.print("|"+tab[i][j]);
			}
			System.out.print("|\n");
		}
		System.out.println();
	}
}
