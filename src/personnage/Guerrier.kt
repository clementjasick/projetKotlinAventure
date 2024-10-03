package personnage

import item.Arme
import item.Armure
import item.Item
import kotlin.math.max

/**
 * Classe représentant un Guerrier, un type de personnage spécialisé dans l'utilisation des armes.
 * Hérite de la classe [Personnage].
 *
 * @param nom Nom du guerrier.
 * @param pointDeVie Points de vie actuels du guerrier.
 * @param pointDeVieMax Points de vie maximum du guerrier.
 * @param attaque Points d'attaque du guerrier.
 * @param defense Points de défense du guerrier.
 * @param endurance Endurance du guerrier.
 * @param vitesse Vitesse du guerrier.
 * @param armurePrincipal Armure principale équipée par le guerrier.
 * @param armePrincipal Arme principale équipée par le guerrier.
 * @param armeSecondaire Arme secondaire équipée par le guerrier (peut être null si aucune n'est équipée).
 * @param inventaire Inventaire des objets détenus par le guerrier.
 */
class Guerrier(
    nom: String,
    pointDeVie: Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
    armurePrincipal: Armure? = null,
    armePrincipal: Arme? = null,
    val armeSecondaire: Arme? = null,
    inventaire: MutableList<Item> = mutableListOf(),
) : Personnage(nom, pointDeVie, pointDeVieMax, attaque, defense, endurance, vitesse, armurePrincipal, armePrincipal, inventaire) {

    /**
     * Affiche toutes les armes dans l'inventaire du guerrier.
     * Parcourt l'inventaire pour lister et afficher les objets de type [Arme].
     */
    fun afficherArme() {
        val listeArme = mutableListOf<Item>()
        for (item in this.inventaire) {
            if (item is Arme) {
                listeArme.add(item)
            }
        }
        println("Arme de ${this.nom}")
        for (item in listeArme) {
            println(item)
        }
    }

    /**
     * Effectue une attaque contre un autre personnage. Les dégâts sont calculés en fonction
     * de l'attaque du guerrier et des dégâts infligés par ses armes (s'il en possède).
     * Si le guerrier possède une arme principale et une arme secondaire, les deux sont utilisées
     * pour infliger des dégâts supplémentaires.
     *
     * @param adversaire Le personnage cible de l'attaque.
     */
    override fun attaque(adversaire: Personnage) {
        var degats = 0
        // Dégâts de base calculés comme la moitié des points d'attaque du guerrier
        degats += (this.attaque / 2)

        // Calcul de la défense totale de l'adversaire
        var def = adversaire.calculeToTalDefense()

        // Si une arme principale est équipée, elle inflige des dégâts supplémentaires
        if (armePrincipal != null) {
            degats += armePrincipal!!.calculDegats()
            println("$nom attaque ${adversaire.nom} avec $armePrincipal et inflige $degats points de dégâts.")
        }

        // Si une arme secondaire est équipée, elle inflige également des dégâts supplémentaires
        if (armeSecondaire != null) {
            degats += armeSecondaire!!.calculDegats()
            println("$nom attaque ${adversaire.nom} avec $armePrincipal et $armeSecondaire et inflige $degats points de dégâts.")
        }

        // Réduction des dégâts en fonction de la défense de l'adversaire
        degats -= def

        // S'assurer que les dégâts minimum infligés sont d'au moins 1 point
        degats = max(1, degats)

        // Appliquer les dégâts aux points de vie de l'adversaire
        adversaire.pointDeVie -= degats
    }
}
