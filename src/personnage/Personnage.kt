package personnage

import item.Arme
import item.Armure
import item.Bombe
import item.Item
import item.Potion
import kotlin.math.max

/**

Représente un personnage avec des attributs de combat tels que la vie, l'attaque, la défense, et la capacité à équiper

des armes, des armures, ainsi qu'à gérer un inventaire.

@property nom Le nom du personnage.

@property pointDeVie Les points de vie actuels du personnage.

@property pointDeVieMax Le nombre maximal de points de vie.

@property attaque Le niveau d'attaque du personnage.

@property defense Le niveau de défense du personnage.

@property endurance Le niveau d'endurance du personnage.

@property vitesse La vitesse du personnage.

@property armurePrincipal L'armure équipée actuellement par le personnage.

@property armePrincipal L'arme équipée actuellement par le personnage.

@property inventaire L'inventaire contenant des objets (armes, armures, potions, bombes, etc.). */

    open class Personnage(
    val nom: String,
    var pointDeVie: Int,
    val pointDeVieMax: Int,
    var attaque: Int,
    var defense: Int,
    var endurance: Int,
    var vitesse: Int,
    var armurePrincipal: Armure?=null,
    var armePrincipal: Arme?=null,
    var inventaire: MutableList<Item> =mutableListOf(),
) {
        //Équipe une arme si elle est présente dans l'inventaire du personnage.
        /**@param arme L'arme à équiper*/
    fun equiperArme(arme: Arme){
        for (item in inventaire) {
            if (item==arme){
                this.armePrincipal=arme
                println("${this.nom} equipe ${this?.armePrincipal?.nom}")
            }
        }

    }
    //Calcule la défense totale du personnage, prenant en compte la défense de base et l'armure équipée.
    fun calculeToTalDefense():Int {
       var resultat = this.defense/2
        if( this.armurePrincipal!=null){
            resultat += this.armurePrincipal!!.calculProtection()
        }
        return resultat
    }
    //Équipe une armure si elle est présente dans l'inventaire du personnage.
    /**@param armure L'armure à équiper. */

    fun equipArmure(armure: Armure){
        for (item in inventaire){
            if (item == armure){
                this.armurePrincipal = armure
                println("${this.nom} equipe ${this.armurePrincipal?.nom}")
            }
        }
    }

     // Méthode pour attaquer un adversaire
     open fun attaque(adversaire: Personnage)     {
        //TODO Mission 4.1
       var degats= (this.attaque/2)


         if (armePrincipal != null){
             degats+= + armePrincipal!!.calculDegats()
             println("$nom attaque ${adversaire.nom} avec $armePrincipal et inflige $degats points de dégats.")
         }
         else {
             println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degats points de dégâts.")
         }
         degats-=adversaire.calculeToTalDefense()
         degats= max(1,degats)
         adversaire.pointDeVie-=degats
    }
    /**
     * Vérifie si le personnage possède une potion dans son inventaire.
     *
     * @return `true` si une potion est trouvée dans l'inventaire, `false` sinon.
     */
    fun avoirPotion(): Boolean {
        for (item in this.inventaire) {
            if (item is Potion) {
                return true
            }
        }
        return false
    }

    /**
     * Vérifie si le personnage possède une bombe dans son inventaire.
     *
     * @return `true` si une bombe est trouvée dans l'inventaire, `false` sinon.
     */
    fun avoirBombe(): Boolean {
        for (item in this.inventaire) {
            if (item is Bombe) {
                return true
            }
        }
        return false
    }

    /**
     * Permet au personnage de boire une potion s'il en possède une.
     * Affiche la liste des potions disponibles dans l'inventaire et permet de choisir laquelle utiliser.
     * La potion choisie est ensuite utilisée pour restaurer les points de vie du personnage.
     */
    fun boirePotion() {
        if (avoirPotion()) {
            val potion: MutableList<Potion> = mutableListOf()
            for (item in this.inventaire) {
                if (item is Potion) {
                    potion.add(item)
                }
            }
            // Affiche la liste des potions disponibles
            println("Liste des potions")
            var i = 0
            for (unePotion in potion) {
                println("$i => ${unePotion.nom}")
                i++
            }
            // Sélection de la potion à utiliser
            println("Saisir la potion")
            val indexPotion = readln().toInt()
            val laPotion = potion[indexPotion]
            laPotion.utiliser(this)
        }
    }

    /**
     * Affiche l'inventaire complet du personnage avec une numérotation des objets.
     */
    fun afficherInventaire() {
        println("Inventaire de ${this.nom}")
        for (i in inventaire.indices) {
            println("$i => ${inventaire[i]}")
        }
    }

    /**
     * Permet au personnage de looter l'inventaire d'un adversaire vaincu.
     * Si les points de vie de la cible sont à 0 ou moins, son arme et armure principales sont retirées,
     * et tout son inventaire est transféré à celui du personnage.
     *
     * @param cible Le personnage vaincu dont l'inventaire est pillé.
     */
    fun loot(cible: Personnage) {
        if (cible.pointDeVie <= 0) {
            // Retire l'arme et l'armure principale de la cible
            cible.armePrincipal = null
            cible.armurePrincipal = null
        }
        // Transfert des objets de l'inventaire de la cible vers celui du personnage
        for (item in cible.inventaire) {
            this.inventaire.add(item)
        }
    }

    override fun toString(): String {
        return "$nom (PV: $pointDeVie/$pointDeVieMax, Attaque: $attaque, Défense: $defense, Endurance: $endurance, Vitesse: $vitesse)"
    }


}