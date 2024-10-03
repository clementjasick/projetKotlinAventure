package personnage

import jeu.Sort
import item.Arme
import item.Armure
import item.Item

/**
 * Classe représentant un Mage, un personnage qui peut utiliser des sorts à partir d'un grimoire.
 * Hérite de la classe [Personnage].
 *
 * @param nom Nom du mage.
 * @param pointDeVie Points de vie actuels du mage.
 * @param pointDeVieMax Points de vie maximum du mage.
 * @param attaque Points d'attaque du mage.
 * @param defense Points de défense du mage.
 * @param endurance Endurance du mage.
 * @param vitesse Vitesse du mage.
 * @param armurePrincipal Armure principale équipée par le mage (peut être null).
 * @param armePrincipal Arme principale équipée par le mage (peut être null).
 * @param inventaire Inventaire des objets détenus par le mage.
 * @param grimoire Liste des sorts que le mage peut lancer.
 */
class Mage(
    nom: String,
    pointDeVie: Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
    armurePrincipal: Armure? = null,
    armePrincipal: Arme? = null,
    inventaire: MutableList<Item> = mutableListOf(),
    val grimoire: MutableList<Sort> = mutableListOf<Sort>()
) : Personnage(nom, pointDeVie, pointDeVieMax, attaque, defense, endurance, vitesse, armurePrincipal, armePrincipal, inventaire) {

    /**
     * Affiche les sorts contenus dans le grimoire du mage.
     * Chaque sort est affiché avec son index dans la liste.
     */
    fun afficherGrimoire() {
        for (i in grimoire.indices) {
            println("$i => ${grimoire[i]}")
        }
    }

    /**
     * Permet de choisir et de lancer un sort sur une cible donnée.
     * Affiche d'abord les sorts disponibles, puis permet de choisir un sort et une cible.
     * Si le grimoire est vide, il affiche un message d'erreur.
     *
     * @param cible Le personnage cible du sort.
     */
    fun choisirEtLancerSort(cible: Personnage) {
        afficherGrimoire()

        if (grimoire.isEmpty()) {
            println("Le grimoire est vide. Vous ne pouvez pas lancer de sort.")
        } else {
            println("Choisissez un sort")
            var i = 0
            val res = readln().toInt()

            // Vérifie si l'index du sort est valide
            if (res in grimoire.indices) {
                println("Cibles possibles : 0 => ${this.nom}, 1 => ${cible.nom}")
                println("Choisissez la cible")
                val sort = this.grimoire[res]
                val choixCible = readln().toInt()

                // Vérifie si le choix de cible est valide
                if (choixCible != 1 && choixCible != 0) {
                    println("Choix de cible invalide")
                } else if (choixCible == 0) {
                    // Lance le sort sur le mage lui-même
                    grimoire[res].effet(this, this)
                    println("${this.nom} lance le sort ${this.grimoire[res]} sur lui-même")
                } else {
                    // Lance le sort sur la cible sélectionnée
                    grimoire[i].effet(this, cible)
                    println("${this.nom} lance le sort ${this.grimoire[res]} sur ${cible.nom}")
                }
            }
        }
    }
}
