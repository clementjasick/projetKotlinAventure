package personnage

import item.Arme
import item.Armure
import item.Bombe
import item.Item
import item.potion
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

    class Personnage(
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
     fun attaque(adversaire: Personnage)     {
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
    fun avoirPotion(potion: potion): Boolean{
        if (potion in inventaire){
            return true
        }else{
            return false
        }
    }
    fun avoirBombe(bombe: Bombe):Boolean{
        if (bombe in inventaire){
            return true
        }else{
            return false
        }
    }
    fun boirePotion(potion: potion,cible: Personnage){
        if (avoirPotion(potion)==true){
            potion.utiliser(cible)
        }
    }
    override fun toString(): String {
        return "$nom (PV: $pointDeVie/$pointDeVieMax, Attaque: $attaque, Défense: $defense, Endurance: $endurance, Vitesse: $vitesse)"
    }


}