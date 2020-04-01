/**
 * Une exception {@code InvalidPositionException} est levée
 * lorsqu'on essaye de {@link Bille#convertirPos(String) convertir}
 * un {@code String} contenant une position qui n'existe
 * pas sur le {@link Plateau plateau}, ou bien lorsqu'on
 * essaye de {@link Plateau#vecteur(int[], int[]) calculer un vecteur}
 * entre deux positions qui ne sont pas voisines, ou encore
 * lorsqu'on essaye de manipuler une position qui n'existe pas.
 * 
 * <p>Cette exception hérite de {@link RuntimeException} et
 * n'a, par conséquent, pas besoin d'être déclarée dans
 * le bloc {@code throws} ou entourée d'un bloc
 * <code>try {...} catch (...) {...}</code>.
 * 
 * @author	Théo SZANTO
 * @see		Bille#convertirPos(String)
 * @see		Plateau
 * @see		Plateau#vecteur(int[], int[])
 */
public class InvalidPositionException extends RuntimeException {
	private static final long serialVersionUID = 8668924764798074579L;
	
	/**
	 * Crée une nouvelle {@code InvalidPositionException} avec un message par defaut.
	 * 
	 * @see		RuntimeException#RuntimeException(String)
	 */
	public InvalidPositionException() {
		super("Erreur : Position invalide");
	}
	
	/**
	 * Crée une nouvelle {@code InvalidPositionException} avec comme message {@code s}.
	 * 
	 * @param s
	 * 			Le message d'erreur de l'exception.
	 * @see		RuntimeException#RuntimeException(String)
	 */
	public InvalidPositionException(String s) {
		super(s);
	}
}