package item

import jeu.TirageDes
import personnage.Personnage


class Bombe(val armure: Armure,val personnage : Personnage,  nom : String, description : String): Item(nom,description) {
    override fun utiliser(cible : Personnage){
        val degat=TirageDes(3,8).lance()
        var degatTotal= degat - (armure.calculProtection()+ personnage.defense).toInt()
        if (degatTotal<1) {
            degatTotal = 1
        }
        println(" $cible prend $degatTotal de déagâts en recevant la bombe")
    }
}