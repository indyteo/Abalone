import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Jeu de test du {@link Plateau plateau}.
 * 
 * @author Théo SZANTO
 */
class PlateauTest {
	/**
	 * Test de la méthode {@link Plateau#nettoyerPlateau()}.
	 */
	@Test
	void testNettoyerPlateau() {
		Plateau p = new Plateau();
		p.initialiserPlateau();
		
		p.nettoyerPlateau();
		for (int l = 0; l < p.sizeL(); l++)
			for (int c = 0; c < p.sizeC(l); c++)
				assertNull(p.getBilleAt(l, c), "Vérification du nettoyage");
	}

	/**
	 * Test de la méthode {@link Plateau#initialiserPlateau()}.
	 */
	@Test
	void testInitialiserPlateau() {
		Plateau p = new Plateau();
		String repr1, repr2;
		
		p.initialiserPlateau();
		repr1 = p.toString();
		p.initialiserPlateau(Plateau.PLACEMENT_DEFAULT);
		repr2 = p.toString();
		
		assertEquals(repr2, repr1, "Vérification du placement utilisé par défault");
	}

	/**
	 * Test de la méthode {@link Plateau#initialiserPlateau(int[][])}.
	 */
	@Test
	void testInitialiserPlateauIntArrayArray() {
		Plateau p = new Plateau();
		
		p.initialiserPlateau(Plateau.PLACEMENT_DEFAULT);
		vérifierPlacement(Plateau.PLACEMENT_DEFAULT, p, "PLACEMENT_DEFAULT");
		
		p.initialiserPlateau(Plateau.PLACEMENT_ALIEN);
		vérifierPlacement(Plateau.PLACEMENT_ALIEN, p, "PLACEMENT_ALIEN");
		
		p.initialiserPlateau(Plateau.PLACEMENT_FUJIYAMA);
		vérifierPlacement(Plateau.PLACEMENT_FUJIYAMA, p, "PLACEMENT_FUJIYAMA");
		
		p.initialiserPlateau(Plateau.PLACEMENT_CUSTOM);
		vérifierPlacement(Plateau.PLACEMENT_CUSTOM, p, "PLACEMENT_CUSTOM");
	}
	
	public void vérifierPlacement(int[][] placement, Plateau p, String nom_placement) {
		for (int l = 0; l < p.sizeL(); l++)
			for (int c = 0; c < p.sizeC(l); c++)
				assertEquals(placement[l][c], p.getBilleAt(l, c) == null ? -1 : p.getBilleAt(l, c).getCouleur(), "Vérification du placement des billes avec " + nom_placement);
	}

	/**
	 * Test de la méthode {@link Plateau#viderBilleEjectées()}.
	 */
	@Test
	void testViderBilleEjectées() {
		Plateau p = new Plateau();
		p.initialiserPlateau(Plateau.PLACEMENT_ALIEN);
		
		p.jouer("c3b3", 0);
		if (p.getNbBilleEjectées(1) == 0) {
			fail("Erreur relative à l'état actuel du plateau");
		}
		else {
			p.viderBilleEjectées();
			assertEquals(0, p.getNbBilleEjectées(1), "Vérification du vidage des billes éjectées");
		}
	}

	/**
	 * Test de la méthode {@link Plateau#getBilleAt(String)}.
	 */
	@Test
	void testGetBilleAtString() {
		Plateau p = new Plateau();
		p.initialiserPlateau();
		
		assertEquals(1, p.getBilleAt("h7").getCouleur(), "Vérification bille blanche");
		
		assertEquals(0, p.getBilleAt("a2").getCouleur(), "Vérification bille noire");
		
		assertNull(p.getBilleAt("e5"), "Vérification case vide");
		
		assertThrows(InvalidPositionException.class, () -> {
			p.getBilleAt("b9");
		}, "Vérification case inexistante");
	}

	/**
	 * Test de la méthode {@link Plateau#getBilleAt(int[])}.
	 */
	@Test
	void testGetBilleAtIntArray() {
		Plateau p = new Plateau();
		p.initialiserPlateau();
		int[] pos1 = {1, 3}, pos2 = {8, 0}, pos3 = {4, 6}, pos4 = {-2, 5};
		
		assertEquals(1, p.getBilleAt(pos1).getCouleur(), "Vérification bille blanche");
		
		assertEquals(0, p.getBilleAt(pos2).getCouleur(), "Vérification bille noire");
		
		assertNull(p.getBilleAt(pos3), "Vérification case vide");
		
		assertThrows(InvalidPositionException.class, () -> {
			p.getBilleAt(pos4);
		}, "Vérification case inexistante");
	}

	/**
	 * Test de la méthode {@link Plateau#getBilleAt(int, int)}.
	 */
	@Test
	void testGetBilleAtIntInt() {
		Plateau p = new Plateau();
		p.initialiserPlateau();
		
		assertEquals(1, p.getBilleAt(2, 4).getCouleur(), "Vérification bille blanche");
		
		assertEquals(0, p.getBilleAt(7, 2).getCouleur(), "Vérification bille noire");
		
		assertNull(p.getBilleAt(3, 6), "Vérification case vide");
		
		assertThrows(InvalidPositionException.class, () -> {
			p.getBilleAt(6, -1);
		}, "Vérification case inexistante");
	}

	/**
	 * Test de la méthode {@link Plateau#getNbBilleEjectées(int)}.
	 */
	@Test
	void testGetNbBilleEjectées() {
		Plateau p = new Plateau();
		p.initialiserPlateau(Plateau.PLACEMENT_ALIEN);
		
		p.jouer("c3b3", 0);
		p.jouer("b3b4", 0);
		p.jouer("b4b5", 0);
		p.jouer("g5h6", 1);
		
		assertEquals(1, p.getNbBilleEjectées(0), "Vérification 1 bille noire");
		
		assertEquals(2, p.getNbBilleEjectées(1), "Vérification 2 billes blanches");
	}

	/**
	 * Test de la méthode {@link Plateau#jouer(String, int)}.
	 */
	@Test
	void testJouer() {
		Plateau p = new Plateau();
		p.initialiserPlateau(Plateau.PLACEMENT_ALIEN);
		
		assertTrue(p.jouer("h6g5", 1), "Vérification coup valide en ligne");
		
		assertTrue(p.jouer("f5f6e4", 0), "Vérification coup valide latéral");
		
		assertTrue(p.jouer("c3b3", 0), "Vérification coup valide élimination");
		
		assertFalse(p.jouer("e1e2", 1), "Vérification coup invalide vide");
		
		assertFalse(p.jouer("a1b1", 0), "Vérification coup invalide adversaire");
		
		assertFalse(p.jouer("g8g7", 0), "Vérification coup invalide en ligne");
		
		assertFalse(p.jouer("h7g7i8", 1), "Vérification coup invalide latéral");
		
		assertFalse(p.jouer("i7h7", 0), "Vérification coup invalide infériorité numérique");
		
		assertFalse(p.jouer("b2a1", 1), "Vérification coup invalide auto-élimination en ligne");
		
		assertFalse(p.jouer("b5a5b6", 1), "Vérification coup invalide auto-élimination latéral");
		
		assertThrows(InvalidCommandException.class, () -> {
			p.jouer("d6", 0);
		}, "Vérification commande fausse");
	}

	/**
	 * Test de la méthode {@link Plateau#toString()}.
	 */
	@Test
	void testToString() {
		Plateau p = new Plateau();
		p.initialiserPlateau();
		
		String expected =
				"           /-\\-/-\\-/-\\-/-\\-/-\\\n" + 
				"        I / W | W | W | W | W \\\n" + 
				"         /-\\-/-\\-/-\\-/-\\-/-\\-/-\\\n" + 
				"      H / W | W | W | W | W | W \\\n" + 
				"       /-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\\n" + 
				"    G /   |   | W | W | W |   |   \\\n" + 
				"     /-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\\n" + 
				"  F /   |   |   |   |   |   |   |   \\\n" + 
				"   /-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\\n" + 
				"E {   |   |   |   |   |   |   |   |   }\n" + 
				"   \\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/\n" + 
				"  D \\   |   |   |   |   |   |   |   / 9\n" + 
				"     \\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/\n" + 
				"    C \\   |   | B | B | B |   |   / 8\n" + 
				"       \\-/-\\-/-\\-/-\\-/-\\-/-\\-/-\\-/\n" + 
				"      B \\ B | B | B | B | B | B / 7\n" + 
				"         \\-/-\\-/-\\-/-\\-/-\\-/-\\-/\n" + 
				"        A \\ B | B | B | B | B / 6\n" + 
				"           \\-/-\\-/-\\-/-\\-/-\\-/\n" + 
				"              1   2   3   4   5";
		
		assertEquals(expected, p.toString(), "Vérification de l'affichage");
	}

	/**
	 * Test de la méthode {@link Plateau#mul(String, int)}.
	 */
	@Test
	void testMul() {
		assertEquals("foofoofoofoofoo", Plateau.mul("foo", 5), "Vérification classique 1");
		
		assertEquals("foobarfoobarfoobar", Plateau.mul("foobar", 3), "Vérification classique 2");
		
		assertEquals("", Plateau.mul("foo", 0), "Vérification nombre nul");
		
		assertEquals("", Plateau.mul("", 2), "Vérification 2 chaînes vides");
		
		assertEquals("", Plateau.mul("bar", -7), "Vérification nombre négatif");
	}
}