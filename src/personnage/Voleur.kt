package personnage

import item.Arme
import item.Armure
import item.Item
import jeu.Combat

/**
 * Classe représentant un Voleur, un personnage spécialisé dans le vol d'objets aux autres personnages.
 * Hérite de la classe [Personnage].
 *
 * @param nom Nom du voleur.
 * @param pointDeVie Points de vie actuels du voleur.
 * @param pointDeVieMax Points de vie maximum du voleur.
 * @param attaque Points d'attaque du voleur.
 * @param defense Points de défense du voleur.
 * @param endurance Endurance du voleur.
 * @param vitesse Vitesse du voleur.
 * @param armurePrincipal Armure principale équipée par le voleur (peut être null).
 * @param armePrincipal Arme principale équipée par le voleur (peut être null).
 * @param inventaire Inventaire des objets détenus par le voleur.
 */
class Voleur(
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
) : Personnage(nom, pointDeVie, pointDeVieMax, attaque, defense, endurance, vitesse, armurePrincipal, armePrincipal, inventaire) {

    /**
     * Permet au voleur de voler un objet aléatoire à la cible.
     * Si l'objet volé est l'armure ou l'arme principale de la cible, cet objet est ajouté à l'inventaire du voleur
     * et retiré de la cible. Si l'inventaire de la cible est vide, le vol échoue.
     *
     * @param cible Le personnage victime du vol.
     */
    fun voler(cible: Personnage) {
        // Tirage d'un indice aléatoire dans l'inventaire de la cible
        val tirage = (cible.inventaire.indices).random()

        // Sélection de l'item dans l'inventaire
        val item = this.inventaire[tirage]

        // Vérification si l'objet volé est l'armure principale de la cible
        if (item == cible.armurePrincipal) {
            cible.armurePrincipal = null
            this.inventaire.add(item)
            println("${this.nom} a volé $item à ${cible.nom}")

            // Vérification si l'objet volé est l'arme principale de la cible
        } else if (item == cible.armePrincipal) {
            cible.armePrincipal = null
            this.inventaire.add(item)
            println("${this.nom} a volé $item à ${cible.nom}")

            // Cas où la cible n'a pas d'objets à voler
        } else {
            println("${cible.nom} n'a rien dans son inventaire.")
        }
    }
}
