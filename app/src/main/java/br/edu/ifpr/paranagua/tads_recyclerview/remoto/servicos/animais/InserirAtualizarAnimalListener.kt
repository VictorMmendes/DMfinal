package br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.animais

import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Animal

interface InserirAtualizarAnimalListener {
    fun onInserirAtualizarAnimalReturn(animal: Animal)

    fun onInserirAtualizarAnimalError(mensagem: String)
}