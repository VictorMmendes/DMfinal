package br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.exercicios

import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Exercicio

interface InserirAtualizarExercicioListener {
    fun onInserirAtualizarExercicioReturn(exercicio: Exercicio)

    fun onInserirAtualizarExercicioError(mensagem: String)
}