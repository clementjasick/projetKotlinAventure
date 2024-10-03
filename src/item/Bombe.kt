package item

import jeu.TirageDes
import personnage.Personnage


class Bombe(nom: String, description: String,var nombreDeDes: Int, var maxDe: Int): Item(nom,description) {
    override fun utiliser(cible : Personnage){
        val degat=TirageDes(nombreDeDes,maxDe).lance()
        var degatTotal= degat - (cible.calculeToTalDefense()).toInt()
        if (degatTotal<1) {
            degatTotal = 1
        }
        cible.pointDeVie -= degatTotal
        println(" $cible prend $degatTotal de déagâts en recevant la bombe")
    }
}