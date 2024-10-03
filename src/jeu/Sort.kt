package jeu

import personnage.Personnage
// classe permettant de créer un sort
class Sort(var nom: String, var effet: (Personnage, Personnage)-> Unit) {

    }