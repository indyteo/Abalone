import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Jeu de test de {@link Abalone#estDupliqué(String[], int) estDupliqué(String[], int)}.
 * 
 * @author Théo SZANTO
 */
class AbaloneTest {
	/**
	 * Test de la méthode {@link Abalone#estDupliqué(String[], int)}.
	 */
	@Test
	void testEstDupliqué() {
		String[] tab1 = {"1", "2", "1", "3"}, tab2 = {"1", "2", "3"}, tab3 = {"1", "1", "2", "1", "3"};
		
		assertTrue(Abalone.estDupliqué(tab1, 0), "Test cas vrai classique");
		
		assertFalse(Abalone.estDupliqué(tab2, 0), "Test cas faux classique");
		
		assertTrue(Abalone.estDupliqué(tab3, 0), "Test cas vrai triple");
		
		assertFalse(Abalone.estDupliqué(tab1, 1), "Test cas faux mais autre doublon");
		
		assertThrows(IllegalArgumentException.class, () -> {
			Abalone.estDupliqué(tab1, -1);
		}, "Test cas d'erreur");
	}
}