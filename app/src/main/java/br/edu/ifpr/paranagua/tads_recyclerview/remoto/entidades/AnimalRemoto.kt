package br.edu.ifpr.paranagua.tads_recyclerview.remoto.entidades

import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Animal
import java.text.SimpleDateFormat
import java.util.*

data class AnimalRemoto(var id: Int?,
                        var name: String,
                        var species: String,
                        var breed: String,
                        var weight: Float,
                        var birthdate: String,
                        var size: Int) {

    fun toAnimal(): Animal {
        val porte =
                when (size) {
                    0 -> Animal.Tamanho.P
                    1 -> Animal.Tamanho.M
                    else -> Animal.Tamanho.G
                }

        val f = SimpleDateFormat("yyyy-mm-dd")

        return Animal(id, name, species, breed, weight, f.parse(birthdate), porte)
    }

}