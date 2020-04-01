/**
 * Une exception {@code InvalidColorException} est levée
 * lorsqu'on essaye de créer une nouvelle {@link Bille bille}
 * avec une {@link Bille#couleur couleur} qui n'existe pas ou
 * modifier la représentation d'une couleur qui n'existe pas.
 * 
 * <p>Cette exception hérite de {@link RuntimeException} et
 * n'a, par conséquent, pas besoin d'être déclarée dans
 * le bloc {@code throws} ou entourée d'un bloc
 * <code>try {...} catch (...) {...}</code>.
 * 
 * @author	Théo SZANTO
 * @see		Bille
 * @see		Bille#couleur
 */
public class InvalidColorException extends RuntimeException {
	private static final long serialVersionUID = 6760543443212858348L;
	
	/**
	 * Crée une nouvelle {@code InvalidColorException} avec un message par defaut.
	 * 
	 * @see		RuntimeException#RuntimeException(String)
	 */
	public InvalidColorException() {
		super("Erreur : Couleur invalide");
	}
	
	/**
	 * Crée une nouvelle {@code InvalidColorException} avec comme message {@code s}.
	 * 
	 * @param s
	 * 			Le message d'erreur de l'exception.
	 * @see		RuntimeException#RuntimeException(String)
	 */
	public InvalidColorException(String s) {
		super(s);
	}
}