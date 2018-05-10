package br.edu.ifpr.paranagua.tads_recyclerview.entidades

import br.edu.ifpr.paranagua.tads_recyclerview.remoto.entidades.AnimalRemoto
import java.text.SimpleDateFormat
import java.util.*

data class Animal(var id: Int?,
                  var nome: String,
                  var especie: String,
                  var raca: String,
                  var peso: Float,
                  var nascimento: Date,
                  var porte: Tamanho) {

    enum class Tamanho {
        P, M, G
    }

    fun toAnimalRemoto(): AnimalRemoto {
        val size =
                when (porte) {
                    Animal.Tamanho.P -> 0
                    Animal.Tamanho.M -> 1
                    else -> 2
                }

        val f = SimpleDateFormat("yyyy-mm-dd")

        return AnimalRemoto(id, nome, especie, raca, peso, f.format(nascimento), size)
    }

}