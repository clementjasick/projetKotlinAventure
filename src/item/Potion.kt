package item

import personnage.Personnage

/**
 * Classe représentant une Potion, un objet qui peut être utilisé pour soigner un personnage.
 * Hérite de la classe [Item].
 *
 * @param soin Quantité de points de vie restaurés par la potion.
 * @param nom Nom de la potion.
 * @param description Description de la potion.
 */
class Potion(
    val soin: Int,
    nom: String,
    description: String
) : Item(nom, description) {

    /**
     * Utilise la potion sur un personnage cible, augmentant ses points de vie.
     * Si les points de vie du personnage après utilisation dépassent les points de vie maximum,
     * ils sont ajustés à la valeur maximale.
     *
     * @param cible Le personnage qui reçoit l'effet de la potion.
     */
    override fun utiliser(cible: Personnage) {
        // Augmente les points de vie du personnage cible
        cible.pointDeVie += soin

        // Si les points de vie excèdent le maximum, ajuster à la valeur maximale
        if (cible.pointDeVie > cible.pointDeVieMax) {
            cible.pointDeVie = cible.pointDeVieMax
        }
    }
}
