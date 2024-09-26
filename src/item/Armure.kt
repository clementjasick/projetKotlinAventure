package item

import personnage.Personnage

class Armure (nom: String,description: String,val typeArmure: TypeArmure,val qualite: Qualite): Item(nom,description){
    override fun utiliser(cible : Personnage){

    }
    fun calculProtection():Int{
        var defBonus:Int = typeArmure.bonusType + qualite.bonusRarete
        return defBonus
    }
}
