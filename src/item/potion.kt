package item

import personnage.Personnage

class potion (soin:Int,nom:String,description: String): Item(nom,description){
    val soin = soin
    override fun utiliser(cible : Personnage ){
        var pv=cible.pointDeVie
        pv += soin
        if (pv>cible.pointDeVieMax){
            pv=cible.pointDeVieMax
        }
    }
}