import java.util.ArrayList;

/**
 * La classe {@code Plateau} représente un plateau de jeu.
 * 
 * <p>Chaque plateau est matérialisé par un tableau de
 * {@link Bille billes} à 2 dimensions. Le plateau est
 * hexagonal, de dimensions fixes. Chaque côté mesure
 * 5 cases, soit un total de 61 cases.</p>
 * 
 * <p>Avant de commencer une partie, il est nécessaire
 * d'initialiser le plateau avec {@link Plateau#initialiserPlateau(int[][])}.
 * Exemple d'utilisation correcte :
 * <blockquote><pre>
 * Plateau p = new Plateau();
 * p.{@link Plateau#initialiserPlateau(int[][]) initialiserPlateau}({@link Plateau#PLACEMENT_DEFAULT});
 * </pre></blockquote></p>
 * 
 * @author	Théo SZANTO
 * @see		Bille
 * @see		Plateau#initialiserPlateau(int[][])
 * @see		Plateau#PLACEMENT_DEFAULT
 */
public class Plateau {
	/**
	 * Placements de départ prédéfinis, servant à initialiser le plateau.<br />
	 * Le placement utilisé par défaut est {@link Plateau#PLACEMENT_DEFAULT}.
	 * 
	 * <p>Le format utilisé pour représenter un placement est le suivant :
	 * <blockquote><pre>
	 * PLACEMENT_DEFAULT = {
	 * 	{1, 1, 1, 1, 1},
	 *	{1, 1, 1, 1, 1, 1},
	 *	{-1, -1, 1, 1, 1, -1, -1},
	 *	{-1, -1, -1, -1, -1, -1, -1, -1},
	 *	{-1, -1, -1, -1, -1, -1, -1, -1, -1},
	 *	{-1, -1, -1, -1, -1, -1, -1, -1},
	 *	{-1, -1, 0, 0, 0, -1, -1},
	 *	{0, 0, 0, 0, 0, 0},
	 *	{0, 0, 0, 0, 0}
	 * };
	 * </pre></blockquote>
	 * Où {@code -1} représente une case vide, {@code 0} un emplacement noir et {@code 1} un emplacement blanc.</p>
	 * 
	 * @see		Plateau#initialiserPlateau(int[][])
	 */
	public static final int[][]
			PLACEMENT_DEFAULT = {
					{1, 1, 1, 1, 1},
					{1, 1, 1, 1, 1, 1},
					{-1, -1, 1, 1, 1, -1, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1},
					{-1, -1, 0, 0, 0, -1, -1},
					{0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0}
			},
			PLACEMENT_MARGUERITE_BELGE = {
					{1, 1, -1, 0, 0},
					{1, 1, 1, 0, 0, 0},
					{-1, 1, 1, -1, 0, 0, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1},
					{-1, 0, 0, -1, 1, 1, -1},
					{0, 0, 0, 1, 1, 1},
					{0, 0, -1, 1, 1}
			},
			PLACEMENT_MARGUERITE_ALLEMANDE = {
					{-1, -1, -1, -1, -1},
					{1, 1, -1, -1, 0, 0},
					{1, 1, 1, -1, 0, 0, 0},
					{-1, 1, 1, -1, -1, 0, 0, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1, -1},
					{-1, 0, 0, -1, -1, 1, 1, -1},
					{0, 0, 0, -1, 1, 1, 1},
					{0, 0, -1, -1, 1, 1},
					{-1, -1, -1, -1, -1}
			},
			PLACEMENT_MARGUERITE_HOLLANDAISE = {
					{1, 1, -1, 0, 0},
					{1, 0, 1, 0, 1, 0},
					{-1, 1, 1, -1, 0, 0, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1},
					{-1, 0, 0, -1, 1, 1, -1},
					{0, 1, 0, 1, 0, 1},
					{0, 0, -1, 1, 1}
			},
			PLACEMENT_MARGUERITE_SUISSE = {
					{-1, -1, -1, -1, -1},
					{1, 1, -1, -1, 0, 0},
					{1, 0, 1, -1, 0, 1, 0},
					{-1, 1, 1, -1, -1, 0, 0, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1, -1},
					{-1, 0, 0, -1, -1, 1, 1, -1},
					{0, 1, 0, -1, 1, 0, 1},
					{0, 0, -1, -1, 1, 1},
					{-1, -1, -1, -1, -1}
			},
			PLACEMENT_ALIEN = {
					{0, -1, 0, -1, 0},
					{-1, 0, 1, 1, 0, -1},
					{-1, 0, 1, 0, 1, 0, -1},
					{-1, -1, -1, 0, 0, -1, -1, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1, -1},
					{-1, -1, -1, 1, 1, -1, -1, -1},
					{-1, 1, 0, 1, 0, 1, -1},
					{-1, 1, 0, 0, 1, -1},
					{1, -1, 1, -1, 1}
			},
			PLACEMENT_DOMINATION = {
					{-1, -1, -1, -1, -1},
					{1, -1, -1, -1, -1, 0},
					{1, 1, -1, -1, -1, 0, 0},
					{1, 1, 1, 1, -1, 0, 0, 0},
					{-1, -1, -1, 0, -1, 0, -1, -1, -1},
					{0, 0, 0, -1, 1, 1, 1, 1},
					{0, 0, -1, -1, -1, 1, 1},
					{0, -1, -1, -1, -1, 1},
					{-1, -1, -1, -1, -1}
			},
			PLACEMENT_INFILTRATION = {
					{-1, 0, 1, 0, -1},
					{-1, 0, 0, 0, 0, -1},
					{-1, 0, 1, 0, 1, 0, -1},
					{-1, 0, -1, -1, -1, -1, 0, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1, -1},
					{-1, 1, -1, -1, -1, -1, 1, -1},
					{-1, 1, 0, 1, 0, 1, -1},
					{-1, 1, 1, 1, 1, -1},
					{-1, 1, 0, 1, -1}
			},
			PLACEMENT_THE_WALL = {
					{-1, -1, 1, -1, -1},
					{-1, -1, -1, -1, -1, -1},
					{-1, 1, 1, 1, 1, 1, -1},
					{1, 1, 1, 1, 1, 1, 1, 1},
					{-1, -1, -1, -1, -1, -1, -1, -1, -1},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{-1, 0, 0, 0, 0, 0, -1},
					{-1, -1, -1, -1, -1, -1},
					{-1, -1, 0, -1, -1}
			},
			PLACEMENT_FUJIYAMA = {
					{1, 1, 1, 1, 1},
					{-1, 1, 0, 0, 1, -1},
					{-1, -1, 1, 0, 1, -1, -1},
					{-1, -1, -1, 1, 1, -1, -1, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1, -1},
					{-1, -1, -1, 0, 0, -1, -1, -1},
					{-1, -1, 0, 1, 0, -1, -1},
					{-1, 0, 1, 1, 0, -1},
					{0, 0, 0, 0, 0}
			},
			PLACEMENT_SNAKES = {
					{0, 0, 0, 0, 0},
					{0, -1, -1, -1, -1, -1},
					{0, -1, -1, -1, -1, -1, -1},
					{0, -1, -1, 0, 0, 1, 1, -1},
					{-1, 0, -1, 0, -1, 1, -1, 1, -1},
					{-1, 0, 0, 1, 1, -1, -1, 1},
					{-1, -1, -1, -1, -1, -1, 1},
					{-1, -1, -1, -1, -1, 1},
					{1, 1, 1, 1, 1}
			},
			PLACEMENT_SNAKES_VARIANTE = {
					{0, 0, 0, 0, 0},
					{0, -1, -1, -1, -1, -1},
					{0, -1, -1, -1, -1, -1, -1},
					{0, -1, 1, 1, 1, 1, 1, -1},
					{-1, 0, -1, -1, -1, -1, -1, 1, -1},
					{-1, 0, 0, 0, 0, 0, -1, 1},
					{-1, -1, -1, -1, -1, -1, 1},
					{-1, -1, -1, -1, -1, 1},
					{1, 1, 1, 1, 1}
			},
			PLACEMENT_FACE_A_FACE = {
					{-1, -1, -1, -1, -1},
					{-1, -1, -1, -1, -1, -1},
					{1, 1, -1, -1, -1, 0, 0},
					{1, 1, 1, -1, -1, 0, 0, 0},
					{1, 1, 1, 1, -1, 0, 0, 0, 0},
					{1, 1, 1, -1, -1, 0, 0, 0},
					{1, 1, -1, -1, -1, 0, 0},
					{-1, -1, -1, -1, -1, -1},
					{-1, -1, -1, -1, -1}
			},
			PLACEMENT_PYRAMIDE = {
					{1, -1, -1, -1, -1},
					{1, 1, -1, -1, -1, -1},
					{1, 1, 1, -1, -1, -1, -1},
					{1, 1, 1, 1, -1, -1, -1, -1},
					{1, 1, 1, 1, -1, 0, 0, 0, 0},
					{-1, -1, -1, -1, 0, 0, 0, 0},
					{-1, -1, -1, -1, 0, 0, 0},
					{-1, -1, -1, -1, 0, 0},
					{-1, -1, -1, -1, 0}
			},
			PLACEMENT_CUSTOM = {
					{-1, 1, 1, 1, -1},
					{1, 1, -1, -1, 1, 1},
					{1, -1, 1, 1, 1, -1, 1},
					{-1, -1, 1, -1, -1, 1, -1, -1},
					{-1, -1, -1, -1, -1, -1, -1, -1, -1},
					{-1, -1, 0, -1, -1, 0, -1, -1},
					{0, -1, 0, 0, 0, -1, 0},
					{0, 0, -1, -1, 0, 0},
					{-1, 0, 0, 0, -1}
			};
	
	/**
	 * Message d'aide qui explique comment jouer.
	 * 
	 * @see		Plateau#jouer(String, int)
	 */
	public static final String HELP_MSG = "\t\tComment jouer ?\n"
			+ "\n"
				+ "\tPrincipe du jeu\n"
			+ "Déplacer une, deux ou trois billes en même temps, d'une case.\n"
			+ "Les déplacements sont en ligne (poussée) ou latéralement.\n"
			+ "On peut pousser une bille adverse en ligne, à :\n"
			+ "- 3 contre 2 ;\n"
			+ "- 3 contre 1 ;\n"
			+ "- 2 contre 1.\n"
			+ "Une bille est éjectée lorsqu'elle est poussée hors du plateau.\n"
			+ "\n"
				+ "\tBut du jeu\n"
			+ "La partie est gagnée lorsqu'on a éjecté 6 billes adverses.\n"
			+ "\n"
				+ "\tNotation des cases\n"
			+ "Les lignes sont représentées par des lettres de A à I.\n"
			+ "Les \"colonnes\" en diagonale sont représentées par des chiffres de 1 à 9.\n"
			+ "On note d'abord la ligne, puis la colonne : \"A3\", \"E8\" ou encore \"G5\"...\n"
			+ "\n"
				+ "\tNotation des coups\n"
			+ "Le système de notation utilisé est \"Aba-Pro\" :\n"
			+ "- On représente un déplacement en ligne par la case de la\n"
			+ "  bille qui réalise la poussée, suivi de sa case d'arrivée.\n"
			+ "- Pour un déplacement latéral, on note la position des deux\n"
			+ "  extrémités, suivi de la case d'arrivée de la première\n"
			+ "  extrémité notée. Il y a donc 2 représentations pour un\n"
			+ "  unique coup latéral avec ce système de notation.\n"
			+ "\n"
				+ "\tCommandes\n"
			+ "Les commandes disponibles sont les suivantes :\n"
			+ "- h : Affiche ce message ;\n"
			+ "- b : Affiche le plateau ;\n"
			+ "- q : Abandonner ;\n"
			+ "- p : Passer son tour ;\n"
			+ "- coup : Jouer le coup.";
	
	/**
	 * Tableau modélisant le plateau de jeu.
	 * 
	 * <p>Il contient les {@link Bille billes} ou {@code null}
	 * lorsque la case est vide.</p>
	 * 
	 * <p>Les actions possibles sont :<ul>
	 * <li>Obtenir le contenu d'une case donnée sous la forme de :<ul>
	 * <li>Un tableau <code>{l, c}</code></li>
	 * <li>Un {@code String} correspondant à une position</li>
	 * <li>Les deux coordonnées {@code l} et {@code c}</li>
	 * </ul></li>
	 * <em><li>Définir le contenu d'une case donnée sous la forme de :<ul>
	 * <li>Un tableau <code>{l, c}</code></li>
	 * <li>Les deux coordonnées {@code l} et {@code c}</li>
	 * </ul></li>
	 * <li>Vider le contenu d'une case donnée sous la forme de :<ul>
	 * <li>Un tableau <code>{l, c}</code></li>
	 * <li>Les deux coordonnées {@code l} et {@code c}</li>
	 * </ul></li></em>
	 * </ul>
	 * Les actions en italique ne sont pas possibles de l'extérieur.</p>
	 * 
	 * @see		Plateau#getBilleAt(int[])
	 * @see		Plateau#getBilleAt(String)
	 * @see		Plateau#getBilleAt(int, int)
	 * @see		Plateau#setBilleAt(int[], Bille)
	 * @see		Plateau#setBilleAt(int, int, Bille)
	 * @see		Plateau#viderCase(int[])
	 * @see		Plateau#viderCase(int, int)
	 */
	private Bille[][] plateau;
	
	/**
	 * Liste contenant les {@link Bille billes} éjectées du {@link Plateau#plateau plateau}.
	 * 
	 * <p>Les actions possibles sont :<ul>
	 * <li>Vider cette liste</li>
	 * <li>Obtenir le nombre de billes éjectées selon une couleur</li>
	 * <em><li>Ajouter une bille à cette liste depuis le plateau, à partir
	 * d'une case donnée sous la forme de :<ul>
	 * <li>Un tableau <code>{l, c}</code></li>
	 * <li>Les deux coordonnées {@code l} et {@code c}</li>
	 * </ul></li></em>
	 * </ul>
	 * Les actions en italique ne sont pas possibles de l'extérieur.</p>
	 * 
	 * @see		Plateau#viderBilleEjectées()
	 * @see		Plateau#getNbBilleEjectées(int)
	 * @see		Plateau#éjecterBilleAt(int[])
	 * @see		Plateau#éjecterBilleAt(int, int)
	 */
	private ArrayList<Bille> billes_éjectées;
	
	/**
	 * Crée le plateau de jeu, vide.
	 * 
	 * <p>Avant de pouvoir commencer une partie, il est nécessaire de
	 * choisir un placement et de l'utiliser pour initialiser le plateau.
	 * Il n'est pas nécessaire de vider le plateau avant de l'initialiser.</p>
	 * 
	 * @see		Plateau#initialiserPlateau(int[][])
	 */
	public Plateau() {
		this.plateau = new Bille[9][];
		for (int i = 0; i < this.plateau.length; i++)
			this.plateau[i] = new Bille[9 - Math.abs(4 - i)];
		this.billes_éjectées = new ArrayList<Bille>();
	}
	
	/**
	 * Retourne le nombre de lignes du plateau.
	 * 
	 * @return	Le nombre de lignes du plateau.
	 * @see		Plateau#plateau
	 */
	public int sizeL() {
		return this.plateau.length;
	}
	
	/**
	 * Retourne le nombre de colonnes du plateau selon une ligne.
	 * 
	 * @param l
	 * 			La ligne sur laquelle compter les colonnes.
	 * @return	Le nombre de colonnes du plateau à la ligne {@code l}.
	 * @see		Plateau#plateau
	 */
	public int sizeC(int l) {
		return this.plateau[l].length;
	}
	
	/**
	 * Vide le plateau de jeu.
	 * 
	 * <p>Cette méthode est appelée avant chaque initialisation de plateau.
	 * Toutes les cases du plateau sont mises à {@code null}, quelle que
	 * soit leur valeur de départ. Cette méthode vide également la liste
	 * des billes éjectées.</p>
	 * 
	 * @see		Plateau#initialiserPlateau(int[][])
	 * @see		Plateau#viderBilleEjectées()
	 */
	public void nettoyerPlateau() {
		this.viderBilleEjectées();
		for (int i = 0; i < this.plateau.length; i++)
			for (int j = 0; j < this.plateau[i].length; j++)
				this.plateau[i][j] = null;
	}
	
	/**
	 * Place les billes sur le plateau selon le placement par défault.<br />
	 * Le placement par defaut est {@link Plateau#PLACEMENT_DEFAULT}.
	 * 
	 * @see		Plateau#PLACEMENT_DEFAULT
	 * @see		Plateau#initialiserPlateau(int[][])
	 */
	public void initialiserPlateau() {
		this.initialiserPlateau(PLACEMENT_DEFAULT);
	}
	
	/**
	 * Place les billes sur le plateau selon un placement donné.<br />
	 * Vide le plateau (et les billes éjectées) avant de placer les billes.
	 * 
	 * @param placement
	 * 			Le placement initial des billes.
	 * @see		Plateau#nettoyerPlateau()
	 * @see		Plateau#viderBilleEjectées()
	 * @see		Plateau#PLACEMENT_DEFAULT
	 */
	public void initialiserPlateau(int[][] placement) {
		this.nettoyerPlateau();
		for (int l = 0; l < this.plateau.length; l++)
			for (int c = 0; c < this.plateau[l].length; c++)
				if (placement[l][c] != -1)
					this.plateau[l][c] = new Bille(placement[l][c]);
	}
	
	/**
	 * Vide la liste de billes éjectées.
	 * 
	 * <p>Cette méthode est appelée avant chaque initialisation
	 * du plateau, lors du nettoyage de ce dernier.</p>
	 * 
	 * @see		Plateau#nettoyerPlateau()
	 * @see		Plateau#initialiserPlateau(int[][])
	 */
	public void viderBilleEjectées() {
		this.billes_éjectées.clear();
	}

	/**
	 * Retourne la {@link Bille bille} à la position donnée
	 * si elle existe, {@code null} sinon.
	 * 
	 * @param pos
	 * 			La position de la case à lire, conforme au
	 * 			{@link Bille#POS_REGEX format des positions}.
	 * @return	La bille à la position {@code pos} si elle
	 * 			existe, {@code null} sinon.
	 * @throws InvalidPositionException
	 * 			Si la case n'existe pas.
	 * @see		Plateau#getBilleAt(int[])
	 * @see		Plateau#getBilleAt(int, int)
	 */
	public Bille getBilleAt(String pos) {
		return this.getBilleAt(Bille.convertirPos(pos));
	}
	
	/**
	 * Retourne la {@link Bille bille} à la position donnée
	 * si elle existe, {@code null} sinon.
	 * 
	 * @param pos
	 * 			La position de la case à lire, sous la forme
	 * 			d'un tableau <code>{l, c}</code>.
	 * @return	La bille à la position <code>{l, c}</code> si
	 * 			elle existe, {@code null} sinon.
	 * @throws InvalidPositionException
	 * 			Si la case n'existe pas.
	 * @see		Plateau#getBilleAt(String)
	 * @see		Plateau#getBilleAt(int, int)
	 */
	public Bille getBilleAt(int[] pos) {
		return this.getBilleAt(pos[0], pos[1]);
	}
	
	/**
	 * Retourne la {@link Bille bille} aux coordonnées
	 * données si elle existe, {@code null} sinon.
	 * 
	 * @param l
	 * 			La ligne de la case à lire.
	 * @param c
	 * 			La colonne de la case à lire.
	 * @return	La bille aux coordonnées {@code l}, {@code c}
	 * 			si elle existe, {@code null} sinon.
	 * @throws InvalidPositionException
	 * 			Si la case n'existe pas.
	 * @see		Plateau#getBilleAt(int[])
	 * @see		Plateau#getBilleAt(String)
	 */
	public Bille getBilleAt(int l, int c) {
		if (l < 0 || l >= this.sizeL() || c < 0 || c >= this.sizeC(l))
			throw new InvalidPositionException();
		return this.plateau[l][c];
	}
	
	/**
	 * Place la {@link Bille bille} {@code b} à la position
	 * donnée.
	 * 
	 * <p>Cette méthode ne supprime pas la bille de son emplacement
	 * d'origine !</p>
	 * 
	 * @param pos
	 * 			La position de la case où placer la bille,
	 * 			sous la forme d'un tableau <code>{l, c}</code>.
	 * @param b
	 * 			La {@link Bille bille} à placer.
	 * @throws InvalidPositionException
	 * 			Si la case n'existe pas.
	 * @see		Plateau#setBilleAt(int, int, Bille)
	 */
	private void setBilleAt(int[] pos, Bille b) {
		this.setBilleAt(pos[0], pos[1], b);
	}
	
	/**
	 * Place la {@link Bille bille} {@code b} aux coordonnées
	 * données.
	 * 
	 * <p>Cette méthode ne supprime pas la bille de son emplacement
	 * d'origine !</p>
	 * 
	 * @param l
	 * 			La ligne de la case où placer la bille.
	 * @param c
	 * 			La colonne de la case où placer la bille.
	 * @param b
	 * 			La {@link Bille bille} à placer.
	 * @throws InvalidPositionException
	 * 			Si la case n'existe pas.
	 * @see		Plateau#setBilleAt(int[], Bille)
	 */
	private void setBilleAt(int l, int c, Bille b) {
		if (l < 0 || l >= this.sizeL() || c < 0 || c >= this.sizeC(l))
			throw new InvalidPositionException();
		this.plateau[l][c] = b;
	}
	
	/**
	 * Retourne le nombre de {@link Bille billes} éjectées
	 * de la couleur {@code couleur}.
	 * 
	 * @param couleur
	 * 			La couleur des billes dont on recherche le nombre éjectées, conforme à
	 * 			{@link Bille#couleur la codification des couleurs de billes}.
	 * @return	Le nombre de billes éjectées de la couleur reçue.
	 * @see		Bille
	 * @see		Plateau#billes_éjectées
	 * @see		Bille#couleur
	 */
	public int getNbBilleEjectées(int couleur) {
		int nbBilleEjectées = 0;
		for (int i = 0; i < this.billes_éjectées.size(); i++)
			if (this.billes_éjectées.get(i).getCouleur() == couleur)
				nbBilleEjectées++;
		return nbBilleEjectées;
	}
	
	/**
	 * Place la {@link Bille bille} qui se trouve à la position donnée
	 * dans la liste des {@link Plateau#billes_éjectées billes éjectées}.
	 * 
	 * @param pos
	 * 			La position de la bille à éjecter, sous
	 * 			la forme d'un tableau <code>{l, c}</code>.
	 * @throws InvalidPositionException
	 * 			Si la case n'existe pas.
	 * @see		Plateau#billes_éjectées
	 * @see		Plateau#éjecterBilleAt(int, int)
	 */
	private void éjecterBilleAt(int[] pos) {
		this.éjecterBilleAt(pos[0], pos[1]);
	}
	
	/**
	 * Place la {@link Bille bille} qui se trouve aux coordonnées données
	 * dans la liste des {@link Plateau#billes_éjectées billes éjectées}.
	 * 
	 * @param l
	 * 			La ligne de la case de la bille à éjecter.
	 * @param c
	 * 			La colonne de la case de la bille à éjecter.
	 * @throws InvalidPositionException
	 * 			Si la case n'existe pas.
	 * @see		Plateau#billes_éjectées
	 * @see		Plateau#éjecterBilleAt(int[])
	 */
	private void éjecterBilleAt(int l, int c) {
		this.billes_éjectées.add(this.getBilleAt(l, c));
		this.viderCase(l, c);
	}
	
	/**
	 * Vide la case à la position donnée.
	 * 
	 * <p>Une case vide contient {@code null}.</p>
	 * 
	 * @param pos
	 * 			La position de la case à vider, sous
	 * 			la forme d'un tableau <code>{l, c}</code>.
	 * @throws InvalidPositionException
	 * 			Si la case n'existe pas.
	 * @see		Plateau#viderCase(int, int)
	 */
	private void viderCase(int[] pos) {
		this.viderCase(pos[0], pos[1]);
	}
	
	/**
	 * Vide la case aux coordonnées données.
	 * 
	 * <p>Une case vide contient {@code null}.</p>
	 * 
	 * @param l
	 * 			La ligne de la case à vider.
	 * @param c
	 * 			La colonne de la case à vider.
	 * @throws InvalidPositionException
	 * 			Si la case n'existe pas.
	 * @see		Plateau#viderCase(int[])
	 */
	private void viderCase(int l, int c) {
		if (l < 0 || l >= this.sizeL() || c < 0 || c >= this.sizeC(l))
			throw new InvalidPositionException();
		this.plateau[l][c] = null;
	}
	
	/**
	 * Permet de jouer un coup.
	 * 
	 * <p>Cette méthode réalise 3 opérations afin de jouer
	 * un coup :<ol>
	 * <li>Une analyse de la {@code commande} et le calcul du
	 * {@link Plateau#vecteur(int[], int[]) vecteur} de
	 * {@link Plateau#translation(int[], int[][]) translation}
	 * à utiliser</li>
	 * <li>Une vérification de la validité du coup ainsi qu'une
	 * préparation de ce dernier</li>
	 * <li>La réalisation du coup si il est possible</li></ol>
	 * Dans le cas où le coup va à l'encontre d'une règle du jeu,
	 * la méthode renvoie {@code false} et le coup n'est pas joué :
	 * le traitement s'arrête à la 2<sup>ème</sup> étape.</p>
	 * 
	 * <p>Le format utilisé pour noter les commandes est
	 * <a href="https://fr.wikipedia.org/wiki/Abalone_(jeu)#Aba-Pro">Aba-Pro</a> :
	 * <ul><li>Une poussée en ligne est représentée par la case de la
	 * bille qui réalise la poussée, suivi de sa case d'arrivée.</li>
	 * <li>Pour un déplacement latéral, on note la position des deux
	 * extrêmitées, suivi de la case d'arrivée de la première
	 * extrêmitée notée. Il y a donc 2 représentation pour un
	 * unique coup latéral avec ce système de notation.</li></ul></p>
	 * 
	 * @param commande
	 * 			La commande représentant le coup à jouer.
	 * @param joueur
	 * 			Le joueur qui joue le coup.
	 * @return	{@code true} si le coup est réglementaire,
	 * 			{@code false} sinon.
	 * @throws InvalidCommandException
	 * 			Si la commande ne peut pas être interprêtée.
	 * @see		Plateau#HELP_MSG
	 */
	public boolean jouer(String commande, int joueur) {
		if (commande.length() == 4) {
			// Calcul du vecteur de translation
			int[] posD, posA;
			try {
				posD = Bille.convertirPos(commande.substring(0, 2));
				posA = Bille.convertirPos(commande.substring(2));
			}
			catch (InvalidPositionException e) {
				throw new InvalidCommandException();
			}
			
			int[][] vect;
			try {
				vect = Plateau.vecteur(posD, posA);
			}
			catch (InvalidPositionException e) {
				return false;
			}
			
			// Vérification et préparation du coup
			int nbBilleJoueur = 0, nbBilleAdverse = 0;
			ArrayList<int[]> posABouger = new ArrayList<int[]>();
			boolean coupPossible = this.getBilleAt(posD) != null && this.getBilleAt(posD).getCouleur() == joueur;
			while (posD != null && this.getBilleAt(posD) != null && coupPossible) {
				if (this.getBilleAt(posD).getCouleur() == joueur) {
					if (nbBilleAdverse != 0)
						coupPossible = false;
					nbBilleJoueur++;
				}
				else
					nbBilleAdverse++;
				if (nbBilleJoueur > 3 || nbBilleAdverse >= nbBilleJoueur)
					coupPossible = false;
				
				posABouger.add(0, posD);
				posD = posA;
				if (posA != null)
					posA = Plateau.translation(posA, vect);
			}
			if (! coupPossible)
				return false;
			
			// Réalisation du coup
			posA = Plateau.translation(posABouger.get(0), vect);
			if (posA == null) {
				if (this.getBilleAt(posABouger.get(0)).getCouleur() == joueur)
					return false;
				
				this.éjecterBilleAt(posABouger.get(0));
				posA = posABouger.remove(0);
			}
			while (! posABouger.isEmpty()) {
				this.setBilleAt(posA, this.getBilleAt(posABouger.get(0)));
				this.viderCase(posABouger.get(0));
				posA = posABouger.remove(0);
			}
		}
		else if (commande.length() == 6) {
			// Calcul du détecteur de translation
			int[] posD1, posD2, posA;
			try {
				posD1 = Bille.convertirPos(commande.substring(0, 2));
				posD2 = Bille.convertirPos(commande.substring(2, 4));
				posA = Bille.convertirPos(commande.substring(4));
			}
			catch (InvalidPositionException e) {
				throw new InvalidCommandException();
			}
			
			int[][] vect;
			try {
				vect = Plateau.vecteur(posD1, posA);
			}
			catch (InvalidPositionException e) {
				return false;
			}
			
			// Vérification et préparation du coup
			ArrayList<int[]> posABouger = new ArrayList<int[]>();
			ArrayList<int[]> arrivées = new ArrayList<int[]>();
			int[] pos;
			posABouger.add(posD1);
			if (Plateau.voisins(posD1, posD2, 2))
				posABouger.add(Plateau.milieu(posD1, posD2));
			posABouger.add(posD2);
			boolean coupPossible = true;
			int i = 0;
			while (coupPossible && i < posABouger.size()) {
				if (this.getBilleAt(posABouger.get(i)) == null || this.getBilleAt(posABouger.get(i)).getCouleur() != joueur) {
					coupPossible = false;
				}
				else {
					pos = Plateau.translation(posABouger.get(i), vect);
					
					if (pos == null || this.getBilleAt(pos) != null)
						coupPossible = false;
					else
						arrivées.add(pos);
				}
				i++;
			}
			if (! coupPossible)
				return false;
			
			// Réalisation du coup
			while (! posABouger.isEmpty()) {
				pos = posABouger.remove(0);
				this.setBilleAt(arrivées.remove(0), this.getBilleAt(pos));
				this.viderCase(pos);
			}
		}
		else {
			throw new InvalidCommandException();
		}
		
		return true;
	}
	
	/**
	 * Test si deux cases sont directement voisines ou non.
	 * 
	 * <p>L'ordre des positions envoyées en paramètre n'a
	 * pas d'importance. Un appel à cette méthode équivaut à un
	 * appel à {@link Plateau#voisins(int[], int[], int)} avec
	 * une distance de {@code 1}.</p>
	 * 
	 * <p>L'expression a d'abord été écrite de manière développée
	 * dans le but de n'oublier aucun cas, puis représentée à l'aide
	 * de prédicats, et enfin factorisée en utilisant les propriétés
	 * mathématiques des prédicats.</p>
	 * 
	 * @param pos1
	 * 			La première position.
	 * @param pos2
	 * 			La deuxième position.
	 * @return	{@code true} si les deux cases sont côte-à-côte,
	 * 			{@code false} sinon.
	 * @see		Plateau#voisins(int[], int[], int)
	 */
	private static boolean voisins(int[] pos1, int[] pos2) {
		return Plateau.voisins(pos1, pos2, 1);
	}
	
	/**
	 * Test si deux cases sont voisines avec {@code dist} cases
	 * qui les séparent ou non.
	 * 
	 * <p>L'ordre des positions envoyées en paramètre n'a
	 * pas d'importance.</p>
	 * 
	 * <p>L'expression a d'abord été écrite de manière développée
	 * dans le but de n'oublier aucun cas, puis représentée à l'aide
	 * de prédicats, et enfin factorisée en utilisant les propriétés
	 * mathématiques des prédicats.</p>
	 * 
	 * @param pos1
	 * 			La première position.
	 * @param pos2
	 * 			La deuxième position.
	 * @param dist
	 * 			La distance attendue entre les deux cases.
	 * @return	{@code true} si les deux cases sont sur la même ligne,
	 * 			éloignées de {@code dist} cases, {@code false} sinon.
	 * @see		Plateau#voisins(int[], int[])
	 */
	private static boolean voisins(int[] pos1, int[] pos2, int dist) {
		boolean a = pos2[0] <= 4 - dist,
				b = pos2[0] > 4 - dist && pos2[0] < 4 + dist,
				c = pos2[0] >= 4 - dist,
				d = pos2[0] == pos1[0] - dist,
				e = pos2[0] == pos1[0],
				f = pos2[0] == pos1[0] + dist,
				g = pos2[1] == pos1[1] - dist,
				h = pos2[1] == pos1[1] + (b && !e && ((d && pos2[0] < 4) || (f && pos2[0] > 4)) ? 4 - pos2[0] : 0),
				i = pos2[1] == pos1[1] + dist + (b && !e && ((d && pos2[0] < 4) || (f && pos2[0] > 4)) ? 4 - pos2[0] : 0);
		
		return (e && (g || i))
				|| ((((a || b) && f) || ((b || c) && d)) && (h || i))
				|| (((a && d) || (c && f)) && (g || h));
	}
	
	/**
	 * Calcule les vecteurs permettant la translation entre
	 * {@code pos1} et {@code pos2} selon le point de départ
	 * de la translation sur le plateau (1ère ou 2ème moitié).
	 * 
	 * @param pos1
	 * 			Le point de départ de la translation dont calculer
	 * 			le vecteur.
	 * @param pos2
	 * 			Le point d'arrivé de la translation dont calculer
	 * 			le vecteur.
	 * @return	Un tableau contenant les deux vecteurs de translation.
	 * 			Le premier sous-tableau correspond au vecteur de
	 * 			translation à utiliser lorsque le point de départ se
	 * 			trouve dans la 1ère moitié du plateau, et le second
	 * 			pour la 2ème moitié.
	 * @throws InvalidPositionException
	 * 			Si les cases {@code pos1} et {@code pos2} ne sont pas
	 * 			voisines.
	 * @see		Plateau#translation(int[], int[][])
	 */
	private static int[][] vecteur(int[] pos1, int[] pos2) {
		if (! Plateau.voisins(pos1, pos2))
			throw new InvalidPositionException("Erreur : Les deux positions ne sont pas voisines");
		int[][] vect = new int[2][2];
		
		if (pos1[0] < 4 || (pos1[0] == 4 && pos2[0] <= pos1[0])) {
			vect[0][0] = pos2[0] - pos1[0];
			vect[0][1] = pos2[1] - pos1[1];
			
			vect[1][0] = vect[0][0];
			vect[1][1] = (vect[0][0] == 0 ? vect[0][1] : (vect[0][0] == 1 ? vect[0][1] - 1 : vect[0][1] + 1));
		}
		else {
			vect[1][0] = pos2[0] - pos1[0];
			vect[1][1] = pos2[1] - pos1[1];
			
			vect[0][0] = vect[1][0];
			vect[0][1] = (vect[1][0] == 0 ? vect[1][1] : (vect[1][0] == 1 ? vect[1][1] + 1 : vect[1][1] - 1));
		}
		
		return vect;
	}
	
	/**
	 * Calcule la nouvelle position d'une case après une translation
	 * et la retourne.
	 * 
	 * <p>Cette méthode retourne {@code null} lorsque la case après
	 * la translation ne fait plus partie du plateau.</p>
	 * 
	 * @param pos
	 * 			La position de départ de la translation à effectuer.
	 * @param vect
	 * 			Le vecteur de translation à utiliser.
	 * @return	La nouvelle position de la case, après translation,
	 * 			si elle est sur le plateau, {@code null} sinon.
	 * @see		Plateau#vecteur(int[], int[])
	 */
	private static int[] translation(int[] pos, int[][] vect) {
		int[] pos2 = new int[2];
		
		if (vect[0][0] == 0) {
			pos2[0] = pos[0];
			pos2[1] = pos[1] + vect[0][1];
		}
		else {
			int i = pos[0] < 4 || (pos[0] == 4 && vect[0][0] == -1) ? 0 : 1;
			pos2[0] = pos[0] + vect[i][0];
			pos2[1] = pos[1] + vect[i][1];
		}
		
		return pos2[0] < 0 || pos2[0] > 8 || pos2[1] < 0 || pos2[1] > 8 - Math.abs(4 - pos2[0]) ? null : pos2;
	}
	
	/**
	 * Retourne la position qui se trouve exactement entre
	 * deux positions espacées d'une case.
	 * 
	 * <p>Les deux positions doivent être exactement séparées
	 * d'une case pour pouvoir la déterminer. L'ordre des
	 * positions n'a pas d'importance.</p>
	 * 
	 * @param pos1
	 * 			La première position.
	 * @param pos2
	 * 			La deuxième position.
	 * @return	La position de la case au milieu des deux
	 * 			positions reçues.
	 * @throws InvalidPositionException
	 * 			Si les cases {@code pos1} et {@code pos2} ne sont pas
	 * 			séparées d'une case exactement.
	 * @see		Plateau#voisins(int[], int[], int)
	 */
	private static int[] milieu(int[] pos1, int[] pos2) {
		if (! Plateau.voisins(pos1, pos2, 2))
			throw new InvalidPositionException("Erreur : Les deux positions ne sont pas séparées d'une case exactement");
		
		int[] pos3 = new int[2];
		
		pos3[0] = (pos1[0] + pos2[0]) / 2;
		pos3[1] = (int) Math.ceil((pos1[1] + pos2[1]) / 2);
		
		return pos3;
	}
	
	/**
	 * Retourne le plateau, formatté pour l'affichage en console.
	 * 
	 * <p>Utilise le format renvoyé par {@link Plateau#ligne(int, String, String)}
	 * pour assembler l'entièreté du plateau.<br />
	 * Le résultat pour un plateau correspondant au placement
	 * {@link Plateau#PLACEMENT_DEFAULT} est :
	 * <blockquote><pre>
	 *            /-\-/-\-/-\-/-\-/-\
	 *         I / W | W | W | W | W \
	 *          /-\-/-\-/-\-/-\-/-\-/-\
	 *       H / W | W | W | W | W | W \
	 *        /-\-/-\-/-\-/-\-/-\-/-\-/-\
	 *     G /   |   | W | W | W |   |   \
	 *      /-\-/-\-/-\-/-\-/-\-/-\-/-\-/-\
	 *   F /   |   |   |   |   |   |   |   \
	 *    /-\-/-\-/-\-/-\-/-\-/-\-/-\-/-\-/-\
	 * E {   |   |   |   |   |   |   |   |   }
	 *    \-/-\-/-\-/-\-/-\-/-\-/-\-/-\-/-\-/
	 *   D \   |   |   |   |   |   |   |   / 9
	 *      \-/-\-/-\-/-\-/-\-/-\-/-\-/-\-/
	 *     C \   |   | B | B | B |   |   / 8
	 *        \-/-\-/-\-/-\-/-\-/-\-/-\-/
	 *       B \ B | B | B | B | B | B / 7
	 *          \-/-\-/-\-/-\-/-\-/-\-/
	 *         A \ B | B | B | B | B / 6
	 *            \-/-\-/-\-/-\-/-\-/
	 *               1   2   3   4   5
	 * </pre></blockquote></p>
	 * 
	 * @return	Un {@code String} contenant l'affichage du plateau.
	 * @see		Plateau#ligne(int, String, String)
	 * @see		Plateau#PLACEMENT_DEFAULT
	 */
	public String toString() {
		String str = "";
		for (int l = 0; l < 4; l++) {
			str += mul("  ", 5 - l) + " /-\\" + mul("-/-\\", l + 4) + "\n"
					+ this.ligne(l, mul("  ", 4 - l) + (char) ('I' - l) + " " + "/", "\\") + "\n";
		}
		
		str += "   /-\\" + mul("-/-\\", 8) + "\n"
				+ this.ligne(4, "E {", "}") + "\n"
				+ "   \\-/" + mul("-\\-/", 8) + "\n";
		
		for (int l = 0; l < 4; l++) {
			str += this.ligne(5 + l, mul("  ", l + 1) + (char) ('D' - l) + " " + "\\", "/" + " " + (9 - l)) + "\n"
					+ mul("  ", l + 2) + " \\-/" + mul("-\\-/", 7 - l) + "\n";
		}
		return str + "              1   2   3   4   5";
	}
	
	/**
	 * Retourne le résultat de la concaténation de {@code nb} répétition(s) de {@code pattern}.
	 * 
	 * <p>Par exemple :
	 * <blockquote><pre>
	 * String result = Plateau.mul("foo", 5);
	 * </pre></blockquote>
	 * est équivalant à :
	 * <blockquote><pre>
	 * String result = "foo" + "foo" + "foo" + "foo" + "foo";
	 * </pre></blockquote></p>
	 * 
	 * <p>Un nombre négatif de répétitions équivaut à 0.</p>
	 * 
	 * @param pattern
	 * 			Le motif à répéter.
	 * @param nb
	 * 			Le nombre de répétition du motif.
	 * @return	Un {@code String} contenant {@code nb} répétitions de {@code pattern}.
	 */
	public static String mul(String pattern, int nb) {
		String str = "";
		for (int i = 0; i < nb; i++)
			str += pattern;
		return str;
	}
	
	/**
	 * Retourne une ligne, formatté pour l'affichage en console.
	 * 
	 * <p>Le format utilisé est conforme pour générer l'affichage du
	 * plateau dans {@link Plateau#toString()}.<br />
	 * Le résultat pour une ligne correspondant à 1 case vide, 2 billes blanches,
	 * une bille noire et 2 cases vides, avec {@code start} valant {@code "/"}
	 * et {@code end} valant {@code "\\"} (simple backslash, échappé) est :
	 * <blockquote><pre>
	 * /   | W | W | B |   |   \
	 * </pre></blockquote></p>
	 * 
	 * @param l
	 * 			L'indice de la ligne souhaitée.
	 * @param start
	 * 			Le {@code String} à ajouter au début.
	 * @param end
	 * 			Le {@code String} à ajouter à la fin.
	 * @return	Un {@code String} contenant l'affichage de la ligne.
	 * @see 	Plateau#toString()
	 */
	private String ligne(int l, String start, String end) {
		String str = start;
		for (int c = 0; c < this.plateau[l].length; c++)
			str += (c == 0 ? "" : "|") + " " + (this.plateau[l][c] == null ? " " : this.plateau[l][c]) + " ";
		return str + end;
	}
}
