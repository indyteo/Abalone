import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Jeu de test des {@link Bille billes}.
 * 
 * @author Théo SZANTO
 */
class BilleTest {
	/**
	 * Test de la méthode {@link Bille#setRepr(int, char)}.
	 */
	@Test
	void testSetRepr() {
		Bille b1 = new Bille(0), b2 = new Bille(1);
		
		Bille.setRepr(0, 'n');
		assertEquals("n", b1.toString(), "Vérification de la modification de la représentation des billes noires");

		Bille.setRepr(1, 'b');
		assertEquals("b", b2.toString(), "Vérification de la modification de la représentation des billes blanches");
		
		assertThrows(InvalidColorException.class, () -> {
			Bille.setRepr(-1, ' ');
		});
		
		Bille.setRepr(0, 'B');
		Bille.setRepr(1, 'W');
	}

	/**
	 * Test de la méthode {@link Bille#toString()}.
	 */
	@Test
	void testToString() {
		Bille b1 = new Bille(0), b2 = new Bille(1);
		
		assertEquals("B", b1.toString(), "Vérification de la représentation des billes noires");
		
		assertEquals("W", b2.toString(), "Vérification de la représentation des billes blanches");
	}

	/**
	 * Test de la méthode {@link Bille#convertirPos(String)}.
	 */
	@Test
	void testConvertirPos() {
		int[] pos1 = {1, 5}, pos2 = {5, 7}, pos3 = {4, 2}, pos4 = {8, 0};
		
		assertArrayEquals(pos1, Bille.convertirPos("h9"));
		
		assertArrayEquals(pos2, Bille.convertirPos("d8"));
		
		assertArrayEquals(pos3, Bille.convertirPos("e3"));
		
		assertArrayEquals(pos4, Bille.convertirPos("a1"));
		
		assertThrows(InvalidPositionException.class, () -> {
			Bille.convertirPos("a8");
		});
	}
}