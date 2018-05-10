package br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.animais

import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Animal

interface BuscaTodosAnimaisListener {
    fun onBuscaTodosAnimaisReturn(animais: List<Animal>)

    fun onBuscaTodosAnimaisError(mensagem: String)
}