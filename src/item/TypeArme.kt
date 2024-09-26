package item
class TypeArme(val nombreDes: Int, val valeurDemax: Int, val mutliplicateurCritique: Int, val actvationCrit: Int) {
    var nom: String = ""

    constructor(
        nom: String,
        nombreDes: Int,
        valeurDemax: Int,
        multiplicateurCritique: Int,
        activationCrit: Int,
    ) : this(nombreDes, valeurDemax, multiplicateurCritique, activationCrit) {
        this.nom = nom
    }
}