/**
 * Une exception {@code InvalidCommandException} est levée
 * lorsqu'on essaye de {@link Plateau#jouer(String, int) jouer un coup}
 * dont la syntaxe est erronée.
 * 
 * <p>Cette exception hérite de {@link RuntimeException} et
 * n'a, par conséquent, pas besoin d'être déclarée dans
 * le bloc {@code throws} ou entourée d'un bloc
 * <code>try {...} catch (...) {...}</code>.
 * 
 * @author	Théo SZANTO
 * @see		Plateau#jouer(String, int)
 */
public class InvalidCommandException extends RuntimeException {
	private static final long serialVersionUID = -4144885971958219488L;
	
	/**
	 * Crée une nouvelle {@code InvalidCommandException} avec un message par defaut.
	 * 
	 * @see		RuntimeException#RuntimeException(String)
	 */
	public InvalidCommandException() {
		super("Erreur : Commande invalide");
	}
	
	/**
	 * Crée une nouvelle {@code InvalidCommandException} avec comme message {@code s}.
	 * 
	 * @param s
	 * 			Le message d'erreur de l'exception.
	 * @see		RuntimeException#RuntimeException(String)
	 */
	public InvalidCommandException(String s) {
		super(s);
	}
}
