import item.Arme
import item.Armure
import item.Bombe
import item.Item
import item.Qualite
import item.TypeArme
import item.TypeArmure
import item.potion
import jeu.Jeu
import personnage.Personnage

/**
 * Déclaration de diverses qualités d'objets avec leurs caractéristiques.
 * Chaque qualité a un nom, un niveau de rareté et une couleur associée (code ANSI).
 */
val qualiteCommun = Qualite("commun", 0, "\u001B[32m")
val qualiteRare = Qualite("rare", 1, couleur = "\u001B[34m")
val qualiteEpic = Qualite("epic", 2, "\u001B[35m")
val qualiteLegendaire = Qualite("legendaire", 3, "\u001B[33m")

/**
 * TODO Sprint 1 Mission 2A et 2B : Ajout des types d'armes et d'armures spécifiques.
 * Les types d'armes et d'armures sont représentés par la classe TypeArme et TypeArmure.
 */

/**
 * TODO Sprint 2 : Ajout des sorts au jeu. Ces sorts auront leurs propres effets et
 * devront être implémentés ultérieurement.
 */

fun main(args: Array<String>) {
    /**
     * Création d'armes avec leurs caractéristiques.
     * Chaque arme a un nom, une description, un type d'arme (TypeArme), et une qualité (Qualite).
     */
    val dague = Arme(
        "Flame of Hell",
        "une dague qui tient chaud",
        TypeArme("dague", 1, 6, 4, 12),  // Dégâts, portée, vitesse, précision
        qualiteLegendaire
    )
    val dragonslayer = Arme(
        "DragonSlayer",
        "Une épée dite capable de tuer un dragon",
        TypeArme("grosse épée", 20, 20, 2, 20),
        qualiteLegendaire
    )
    val luckyWeapon = Arme(
        "luck",
        "Tout ou rien",
        TypeArme("grosse épée", 1, 1, 1000, 19),
        qualiteCommun
    )
    val baton = Arme(
        "matraque",
        "juste une grosse matraque",
        TypeArme("matraque", 2, 8, 2, 10),
        qualiteCommun
    )

    /**
     * Création d'armures avec leurs caractéristiques.
     * Chaque armure a un nom, une description, un type d'armure (TypeArmure), et une qualité (Qualite).
     */
    val armureEnMaille = Armure(
        "armure en côte de maille",
        "une armure qui protège contre des coups légers",
        TypeArmure("Plastron", 2),  // Type d'armure et protection
        qualiteRare
    )
    val armureEnOr = Armure(
        "armure en or",
        "une armure qui a du style",
        TypeArmure("Plastron", 2),
        qualiteRare
    )

    /**
     * Création des personnages (monstres).
     * Chaque personnage est défini par un nom, des points de vie, des caractéristiques de combat (attaque, défense, etc.)
     * ainsi qu'un inventaire contenant des objets comme des armes.
     */
    val gobelin = Personnage(
        "Gob le gobelin",
        pointDeVie = 20,
        pointDeVieMax = 20,
        attaque = 5,
        defense = 4,
        endurance = 6,
        vitesse = 11,
        inventaire = mutableListOf(baton)
    )

    val golem = Personnage(
        "Bob le golem",
        pointDeVie = 35,
        pointDeVieMax = 35,
        attaque = 3,
        defense = 7,
        endurance = 10,
        vitesse = 6,
        inventaire = mutableListOf(baton)
    )

    val ameEnPeine = Personnage(
        "âme en peine",
        pointDeVie = 67,
        pointDeVieMax = 67,
        attaque = 10,
        defense = 12,
        endurance = 0,
        vitesse = 14,
        inventaire = mutableListOf(dague)
    )

    val armureAnimee = Personnage(
        "armure animée",
        pointDeVie = 33,
        pointDeVieMax = 33,
        attaque = 8,
        defense = 15,
        endurance = 8,
        vitesse = 6,
        inventaire = mutableListOf(luckyWeapon)
    )

    /**
     * Création d'une instance du jeu avec une liste de personnages (monstres).
     * Le jeu gère les combats entre les différents personnages.
     */
    val jeu = Jeu(listOf(gobelin, golem, armureAnimee, ameEnPeine))

    /**
     * Lancement du jeu en débutant les combats entre les personnages.
     */
    jeu.lancerCombat()
}
