import java.util.Scanner;

public class Abalone {
	public static final int NB_BILLES_EJECTEES_MAX = 6;
	
	public static void main(String[] args) {
		Plateau p = new Plateau();
		Scanner sc = new Scanner(System.in);
		
		final int[] NEXT = {1, 0};
		int joueur;
		String saisie;
		String[] pseudos = new String[2];
		boolean jouer, coupJoué;
		int gagnant;
		
		System.out.println("Les pseudos peuvent est composés de lettres (minuscules ou majuscules),\n"
				+ "de chiffres ou bien du symbole underscore (\"_\"), et comporter entre 3\n"
				+ "et 32 caractères compris. Ils doivent être différents.\n");
		for (int i = 0; i < pseudos.length; i++) {
			do {
				System.out.print("Pseudo du joueur n°" + (i + 1) + " : ");
				pseudos[i] = sc.nextLine();
			}
			while (! pseudos[i].matches("^[a-zA-Z0-9_]{3,32}$") || estDupliqué(pseudos, i));
		}
		
		jouer = true;
		while (jouer) {
			p.initialiserPlateau(Plateau.PLACEMENT_DEFAULT);
			joueur = 0;
			System.out.println("\n" + Plateau.HELP_MSG + "\n\nDébut de la partie, puisse le sort vous être favorable !");
			
			gagnant = -1;
			while (gagnant == -1) {
				System.out.println("\n" + p + "\n"
						+ "Billes noires éjectées : " + p.getNbBilleEjectées(0) + "\n"
						+ "Billes blanches éjectées : " + p.getNbBilleEjectées(1) + "\n\n"
						+ "Aux " + (joueur == 0 ? "noirs" : "blancs") + " de jouer.");
				
				coupJoué = false;
				while (! coupJoué) {
					System.out.print(pseudos[joueur] + " > ");
					saisie = sc.nextLine();
					
					if (saisie.equalsIgnoreCase("q")) {
						System.out.println(pseudos[joueur] + " abandonne !");
						gagnant = NEXT[joueur];
						coupJoué = true;
					}
					else if (saisie.equalsIgnoreCase("p") && pseudos[joueur].matches("^Test_.*$")) {
						System.out.println(pseudos[joueur] + " passe son tour !");
						coupJoué = true;
					}
					else if (saisie.equalsIgnoreCase("h")) {
						System.out.println("\n" + Plateau.HELP_MSG + "\n");
					}
					else if (saisie.equalsIgnoreCase("b")) {
						System.out.println("\n" + p + "\n"
								+ "Billes noires éjectées : " + p.getNbBilleEjectées(0) + "\n"
								+ "Billes blanches éjectées : " + p.getNbBilleEjectées(1) + "\n");
					}
					else {	
						try {
							if (p.jouer(saisie, joueur))
								coupJoué = true;
							else
								System.out.println("Coup impossible !");
						}
						catch (InvalidCommandException e) {
							System.out.println("Saisie invalide !");
						}
					}
				}
				
				joueur = NEXT[joueur];
				if (p.getNbBilleEjectées(joueur) >= NB_BILLES_EJECTEES_MAX)
					gagnant = NEXT[joueur];
			}
			
			System.out.println("\n" + p + "\n"
					+ "Billes noires éjectées : " + p.getNbBilleEjectées(0) + "\n"
					+ "Billes blanches éjectées : " + p.getNbBilleEjectées(1) + "\n\n"
					+ "Victoire de " + pseudos[gagnant] + " !\n");
			
			do {
				System.out.print("Voulez-vous rejouer ? (o : oui, par défaut | n : non) : ");
				saisie = sc.nextLine().toLowerCase();
			}
			while (! (saisie.equals("") || saisie.equals("o") || saisie.equals("n")));
			
			jouer = ! saisie.equals("n");
		}
		
		System.out.println("Au revoir !");
		
		sc.close();
	}

	/**
	 * Test si une valeur est présente 2 fois dans un tableau.
	 * 
	 * @param tab
	 * 			Le tableau dans lequel chercher.
	 * @param index
	 * 			L'indice de l'élément à vérifier.
	 * @return	{@code true} si l'élément à l'indice {@code index}
	 * 			est présent une autre fois dans le tableau {@code tab},
	 * 			{@code false} sinon.
	 * @throws IllegalArgumentException
	 * 			Si l'indice n'est pas dans les limites du tableau.
	 */
	public static boolean estDupliqué(String[] tab, int index) {
		if (index < 0 || index >= tab.length)
			throw new IllegalArgumentException("L'indice n'est pas dans les limites du tableau");
		for (int i = 0; i < tab.length; i++)
			if (tab[index].equals(tab[i]) && index != i)
				return true;
		return false;
	}
}