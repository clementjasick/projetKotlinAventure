package item
import jeu.TirageDes
import personnage.Personnage

class Arme( nom: String, description: String, val type: TypeArme, val qualite: Qualite,): Item(nom,description) {
    override fun utiliser(cible : Personnage){
        cible.equiperArme(this)
    }

    fun calculDegats(): Int{
        var degats = TirageDes(type.nombreDes,type.valeurDemax).lance()
        val tirage = TirageDes(1,20).lance()
        if (tirage>= type.actvationCrit){
            println("coup critique")
            degats = degats * type.mutliplicateurCritique

        }
        degats += qualite.bonusRarete
        return degats
    }
}