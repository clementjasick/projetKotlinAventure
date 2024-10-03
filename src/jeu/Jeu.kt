package jeu

import personnage.Personnage
import personnage.Guerrier
import personnage.Voleur
import personnage.Mage
import grossePopo
import petitePopo
import moyennePopo


class Jeu(monstres: List<Personnage>) {
    //Le personage du joueur
    lateinit var joueur: Personnage
    //La liste des monstre a combatre
     var combats: MutableList<Combat> = mutableListOf()
    //Le score
    var score: Int = 0

    // Corps du constructeur
    init {
        // Lancement de la création du personage du joueur
        this.creerPersonnage()
        // Pour chaque monstre dans la liste de monstres
        for (unMonstre in monstres){
            // On créer un combat
            val unCombat= Combat(this,unMonstre)
            combats.add(unCombat)
        }
    }

    fun lancerCombat() {
        for (unCombat in this.combats) {
            unCombat.executerCombat()
            // Mettre à jour le score en fonction du nombre de tours
            val tours = unCombat.nombreTours
            score += calculerScore(tours)
        }
        println("Score final du joueur: $score")
    }

    private fun calculerScore(tours: Int): Int {
        // Par exemple, vous pouvez attribuer plus de points pour moins de tours
        return 500 - tours * 10
    }

    /**
     *  Méthode pour créer le personnage du joueur en demandant les informations à l'utilisateur
     *
     */
    fun creerPersonnage(): Personnage {
        println("Création votre personnage:")
        println("Choisissez un nom :")
        val nom = readln()
        println("Choisissez un score d'attaque ,de defense, d'endurance  et de vitesse(total = 40  :")
        var scoreAttaque = readln().toInt()
        var scoreDefense = readln().toInt()
        var scoreEnd = readln().toInt()
        var scoreVitesse = readln().toInt()
        while (scoreEnd+scoreDefense+scoreAttaque+scoreVitesse>40){
            println("Choisissez un score d'attaque ,de defense ,d'endurance et de vitesse (total = 40  :")
            scoreAttaque = readln().toInt()
            scoreDefense = readln().toInt()
            scoreEnd = readln().toInt()
            scoreVitesse = readln().toInt()
        }
        var pv = 50+10*scoreEnd
        val pvMax = pv
        println("choisissez une classe: 1 => Guerrier, 2 => Voleur,3 => Mage")
        var perso = readln().toInt()
        val hero = when (perso) {
            1 -> Guerrier(nom,pv,pvMax,scoreAttaque,scoreDefense,scoreEnd,scoreVitesse,null,null,null,mutableListOf(grossePopo))
            2 -> Voleur(nom,pv,pvMax,scoreAttaque,scoreDefense,scoreEnd,scoreVitesse,null,null,mutableListOf(petitePopo))
            else -> Mage(nom,pv,pvMax,scoreAttaque,scoreDefense,scoreEnd,scoreVitesse,null,null,mutableListOf(moyennePopo),mutableListOf())
        }
        this.joueur= hero
        return hero
    }
    

}