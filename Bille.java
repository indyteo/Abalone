/**
 * La classe {@code Bille} représente une bille du {@link Plateau plateau}.
 * 
 * <p>Chaque bille possède une {@link Bille#couleur couleur} qui définie
 * la façon dont elle est affichée. Cette couleur est requise pour la
 * création de la bille et ne peut être modifiée par la suite.</p>
 * 
 * <p>La classe possède également {@link Bille#convertirPos(String) une méthode de convertion de position}
 * entre une position sous la forme d'un {@code String} et les coordonnées
 * réelles de la case du plateau, ainsi qu'{@link Bille#POS_REGEX une expression régulière}
 * permettant de vérifier la validité d'une position sous la forme d'un
 * {@code String}.</p>
 * 
 * @author	Théo SZANTO
 * @see		Plateau
 * @see		Bille#couleur
 * @see		Bille#convertirPos(String)
 * @see		Bille#POS_REGEX
 */
public class Bille {
	/**
	 * L'expression régulière utilisée pour vérifier que la
	 * position saisie par l'utilisateur est correcte.
	 * 
	 * <p>La position doit être composée d'une lettre en
	 * majuscule (le {@code String} passé en paramètre de
	 * {@link Bille#convertirPos(String)} est automatiquement
	 * passé en majuscule avant la vérification) correspondant
	 * à la ligne du plateau, ainsi que d'un chiffre,
	 * correspondant à la colonne.</p>
	 * 
	 * <p>L'expression utilisée est la suivante :
	 * <blockquote><pre>
	 * public static final String POS_REGEX = {@value};
	 * </pre></blockquote></p>
	 * 
	 * @see		Bille#convertirPos(String)
	 */
	public static final String POS_REGEX = "^"
			+ "(A[1-5])"
			+ "|(B[1-6])"
			+ "|(C[1-7])"
			+ "|(D[1-8])"
			+ "|(E[1-9])"
			+ "|(F[2-9])"
			+ "|(G[3-9])"
			+ "|(H[4-9])"
			+ "|(I[5-9])"
			+ "$";
	
	/**
	 * Caractères représentant les couleurs des billes.
	 * 
	 * <p>Pour obtenir le caractère correspondant, il faut
	 * utiliser la {@link Bille#couleur couleur} en indice.</p>
	 * 
	 * @see		Bille#couleur
	 * @see		Bille#toString()
	 */
	private static char[] repr = {'B', 'W'};
	
	/**
	 * Couleur de la bille.
	 * 
	 * <p>La codification utilisée est la suivante :
	 * <blockquote><table>
	 * <tr><td>Couleur</td><td>Valeur</td></tr>
	 * <tr><td>Noir</td><td>{@code 0}</td></tr>
	 * <tr><td>Blanc</td><td>{@code 1}</td></tr>
	 * </table></blockquote></p>
	 */
	private int couleur;
	
	/**
	 * Crée une bille de coouleur donnée.
	 * 
	 * @param couleur
	 * 			La couleur de la bille, selon la codification
	 * 			de {@link Bille#couleur}.
	 * @throws InvalidColorException
	 * 			Si la couleur reçue en paramètre ne correspond
	 * 			pas à une couleur correcte.
	 * @see		Bille#couleur
	 * @see		InvalidColorException
	 */
	public Bille(int couleur) {
		if (couleur < 0 || couleur > 1)
			throw new InvalidColorException();
		this.couleur = couleur;
	}
	
	/**
	 * Retourne la couleur de la bille.
	 * 
	 * @return	La couleur de la bille, selon la codification
	 * 			de {@link Bille#couleur}.
	 * @see		Bille#couleur
	 */
	public int getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Permet de modifier la {@link Bille#repr représentation}
	 * d'une couleur.
	 * 
	 * @param couleur
	 * 			La couleur à modifier.
	 * @param repr
	 * 			La nouvelle représentation.
	 * @throws InvalidColorException
	 * 			Si la couleur dont on essaye de modifier
	 * 			la représentation n'existe pas.
	 * @see		Bille#repr
	 * @see		Bille#couleur
	 */
	public static void setRepr(int couleur, char repr) {
		if (couleur < 0 || couleur >= Bille.repr.length)
			throw new InvalidColorException("Erreur : La couleur n'existe pas !");
		Bille.repr[couleur] = repr;
	}

	/**
	 * Retourne une lettre représentant la couleur de la bille.
	 * 
	 * <p>La représentation de la couleur est définie par
	 * la variable {@link Bille#repr}.</p>
	 * 
	 * @return	Un {@code String} contenant la lettre représentant
	 * 			la couleur de la bille.
	 * @see		Bille#couleur
	 * @see		Bille#repr
	 */
	public String toString() {
		return Character.toString(Bille.repr[this.couleur]);
	}
	
	/**
	 * Converti un {@code String} contenant une position en un
	 * tableau d'entiers contenant les coordonnées réelles de
	 * la case du plateau.
	 * 
	 * <p>Le {@code String} est analysé et si il ne représente
	 * pas une position valide, l'exception {@link InvalidPositionException}
	 * est levée.</p>
	 * 
	 * <p>La vérification de la validité de la position est la suivante :
	 * <blockquote><pre>
	 * pos = pos.toUpperCase();
	 * if (! pos.matches({@link Bille#POS_REGEX POX_REGEX}))
	 * 	throw new {@link InvalidPositionException}();
	 * </pre></blockquote></p>
	 * 
	 * @param pos
	 * 			Le {@code String} contenant la position à convertir.
	 * @return	Un tableau d'entiers <code>{l, c}</code> avec {@code l}
	 * 			la ligne et {@code c} la colonne.
	 * @throws InvalidPositionException
	 * 			Si la position ne correspond pas à une case existante.
	 * @see		InvalidPositionException
	 * @see		Bille#POS_REGEX
	 */
	public static int[] convertirPos(String pos) {
		pos = pos.toUpperCase();
		if (! pos.matches(POS_REGEX))
			throw new InvalidPositionException();
		
		int l = 8 - pos.charAt(0) + 'A';
		int c = Integer.parseInt("" + pos.charAt(1)) - 1 - (l < 4 ? 4 - l : 0);
		
		int[] p = {l, c};
		return p;
	}
}
