
import item.Arme
import item.Armure
import item.Bombe
import item.Potion
import item.Qualite
import item.TypeArme
import item.TypeArmure
import jeu.Jeu
import jeu.Sort
import jeu.TirageDes
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
// création de potion
val petitePopo = Potion(30,"Petite potion","une potion pour les petits bobos")
val moyennePopo = Potion(30,"Moyenne potion","une potion pour les bobos")
val grossePopo = Potion(30,"Grosse Potion","une potion pour les gros bobos")

// création de bombe
val dynamite = Bombe("dynamite","ca fait boom",2,8)
val c4 = Bombe("c4","ca fait boom",3,6)
val nuke = Bombe("nuke","une grosse explosion",3,8)
val bombe = Bombe("bombe","a utilisé avec précaution",5,15)


val projectionAcide= Sort("sort de projection d'acide",{perso,cible->
    run{
        val tirageDes = TirageDes(1,10)
        var compteur = 0
        var degat = 0
        while (compteur<perso.attaque/2){
            degat += tirageDes.lance()
            cible.pointDeVie -= degat
            compteur+=1
            println("missile magique inflige $degat degats")
        }
    }
})

val bouledefeu = Sort("boule de feu",{perso,cible->
    run{
        val tirageDes = TirageDes(3,6)
        var degats = 0
        degats += tirageDes.lance()
        degats-=cible.defense
        cible.pointDeVie-=degats
        println("boule de feu inflige $degats degats")
    }
})

val missileMagique= Sort("missileMagique",{perso,cible->
    run{
        val tirageDes = TirageDes(1,6)
        var compteur = 0
        var degat = 0
        while (compteur<perso.attaque/2){
            degat += tirageDes.lance()
            cible.pointDeVie -= degat
            compteur+=1
            println("missile magique inflige $degat degats")
        }
    }
})
val sortDeSoin = Sort("sort de soin", {perso,personne->
    run{
        val tirageDes = TirageDes(1,6)
        var soin = tirageDes.lance()
        soin += perso.attaque/2
        personne.pointDeVie+=soin
        if (personne.pointDeVie>perso.pointDeVieMax){
            soin-=perso.pointDeVie-perso.pointDeVieMax
            perso.pointDeVie=perso.pointDeVieMax
        }
        println("sort de soin a soigne $soin pv")
    }
})

val invocationArmure = Sort("InvocationArmureMagique" ,{perso,cible->
    run {
        val lancerD =TirageDes(1,20).lance()
        var qualite: Qualite= Item.qualite
        if (lancerD < 5 ){
            qualite = qualiteCommun
        }
        else if (lancerD>=5 and lancerD < 10){
            qualite=qualiteRare
        }else if (lancerD>=10 and lancerD<15) {
            qualite = qualiteRare
        }else{
            qualite=qualiteLegendaire

        }

})

fun main(args: Array<String>) {
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
        inventaire = mutableListOf(baton, petitePopo,c4,dynamite)
    )

    val golem = Personnage(
        "Bob le golem",
        pointDeVie = 35,
        pointDeVieMax = 35,
        attaque = 3,
        defense = 7,
        endurance = 10,
        vitesse = 6,
        inventaire = mutableListOf(baton, moyennePopo, grossePopo)
    )

    val ameEnPeine = Personnage(
        "âme en peine",
        pointDeVie = 67,
        pointDeVieMax = 67,
        attaque = 10,
        defense = 12,
        endurance = 0,
        vitesse = 14,
        inventaire = mutableListOf(dague,moyennePopo,bombe)
    )

    val armureAnimee = Personnage(
        "armure animée",
        pointDeVie = 33,
        pointDeVieMax = 33,
        attaque = 8,
        defense = 15,
        endurance = 8,
        vitesse = 6,
        inventaire = mutableListOf(luckyWeapon, grossePopo)
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
