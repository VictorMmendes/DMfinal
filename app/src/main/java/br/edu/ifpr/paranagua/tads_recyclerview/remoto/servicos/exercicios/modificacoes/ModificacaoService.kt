package br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.exercicios

import br.edu.ifpr.paranagua.tads_recyclerview.entidades.ModificacaoRemota
import retrofit2.Call
import retrofit2.http.*

interface ModificacaoService {
    @GET("modificacoes.json/{id}")
    fun buscaTodos(@Path("id") id: Int?): Call<List<ModificacaoRemota>>

    @POST("modificacoes.json")
    fun inserir(
            @Query("modificacao[progressdate]") progressdate: String,
            @Query("modificacao[id_exercise]") id_exercise: Int,
            @Query("modificacao[weight]") size: Int): Call<ModificacaoRemota>
//
//    @PATCH("modificacoes/{id}.json")
//    fun atualizar(
//            @Path("id") id: Int,
//            @Query("modificacao[progressdate]") progressdate: String,
//            @Query("modificacao[weight]") weight: Int): Call<ModificacaoRemota>
}