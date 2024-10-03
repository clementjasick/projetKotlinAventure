package jeu

import item.Arme
import item.Armure
import personnage.Personnage
import personnage.Voleur
import personnage.Mage
import kotlin.reflect.typeOf

class Combat(
    val jeu :Jeu,
    val monstre: Personnage
) {
    var nombreTours: Int = 1

    // Méthode pour simuler un tour de combat du joueur
    fun tourDeJoueur() {
        println("\u001B[34m ---Tour de ${this.jeu.joueur.nom} (pv: ${this.jeu.joueur.pointDeVie}) ---")
        println("choisir une action: 1=>Attaquer, 2=>Boire une potion 3 => choisir un objet 4 => lancer un sort 5 => voler 6 => passer le tour")
        // le joeur rentre une valeur qui va décider de son action
        var res = readln().toInt()
        var hero=this.jeu.joueur
        if (res == 1) {
            this.jeu.joueur.attaque(monstre)
        }
        else if (res == 2){
            this.jeu.joueur.boirePotion()
        }else if (res == 3){
            this.jeu.joueur.afficherInventaire()
            println("qu'elle est l'index de l'item que vous voulez utiliser")
            var choixInventaire = readln().toInt()
            while (choixInventaire in jeu.joueur.inventaire.indices == false){
                println("choississez un item correct")
                choixInventaire = readln().toInt()
            }
            var item =jeu.joueur.inventaire[choixInventaire]
            if (item is Armure){
                jeu.joueur.armurePrincipal=item
            }
            else if (item is Arme){
                jeu.joueur.armePrincipal=item
            }
            println(jeu.joueur.inventaire[choixInventaire])

        }
        else if (res == 4 && hero is Voleur){
            hero.voler(monstre)
        }
        else if (res == 4 && hero is Mage){
            hero.choisirEtLancerSort(monstre)
        }
        else{
            println("${this.jeu.joueur.nom} passe le tour")
        }
        println("\u001b[0m")
    }

    // Méthode pour simuler un tour de combat du monstre
    fun tourDeMonstre() {
        println("\u001B[31m---Tour de ${monstre.nom} (pv: ${monstre.pointDeVie}) ---")
        // on génère un nombre random entre 1 et 100 qui va décider de l'action du monstre
        var aleatoire = (1..100).random()
        if (monstre.pointDeVie < monstre.pointDeVieMax/2){
            if (aleatoire <= 70) {
                this.monstre.attaque(this.jeu.joueur)
            }
            else if (aleatoire <= 80){
                this.monstre.boirePotion()
            }
            else {
                println("le ${this.monstre} passe son tour")
            }
        }
        else {
            if ((1..100).random() <= 70) {
                this.monstre.attaque(this.jeu.joueur)
            }
            else {
                println("le ${this.monstre} passe son tour")
            }
        }
        println("\u001b[0m")
    }

    // Méthode pour exécuter le combat complet
    fun executerCombat() {
        println("Début du combat : ${this.jeu.joueur.nom} vs ${monstre.nom}")
        //La vitesse indique qui commence
        var tourJoueur = this.jeu.joueur.vitesse >= this.monstre.vitesse
        //Tant que le joueur et le monstre sont vivants
        while (this.jeu.joueur.pointDeVie > 0 && monstre.pointDeVie > 0) {
            println("Tours de jeu : ${nombreTours}")
            if (tourJoueur) {
                tourDeJoueur()
            } else {
                tourDeMonstre()
            }
            nombreTours++
            tourJoueur = !tourJoueur // Alternance des tours entre le joueur et le monstre
            println("${this.jeu.joueur.nom}: ${this.jeu.joueur.pointDeVie} points de vie | ${monstre.nom}: ${monstre.pointDeVie} points de vie")
            println("")
        }

        if (this.jeu.joueur.pointDeVie <= 0) {
            println("Game over ! ${this.jeu.joueur.nom} a été vaincu !")
            println("Le combat recommence")

            this.jeu.joueur.pointDeVie = this.jeu.joueur.pointDeVieMax
            this.monstre.pointDeVie = this.monstre.pointDeVieMax
            this.executerCombat()
        } else {
            println("BRAVO ! ${monstre.nom} a été vaincu !")
        }
    }
}