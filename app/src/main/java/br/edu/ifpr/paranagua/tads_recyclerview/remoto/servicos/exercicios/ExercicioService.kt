package br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.exercicios

import br.edu.ifpr.paranagua.tads_recyclerview.remoto.entidades.ExercicioRemoto
import retrofit2.Call
import retrofit2.http.*

interface ExercicioService {
    @GET("exercicios.json")
    fun buscaTodos(): Call<List<ExercicioRemoto>>

    @POST("exercicios.json")
    fun inserir(
                @Query("animal[description]") description: String,
                @Query("animal[repeats]") repeats: String,
                @Query("animal[weight]") weight: Int): Call<ExercicioRemoto>

    @PATCH("exercicios/{id}.json")
    fun atualizar(
            @Path("id") id: Int,
            @Query("animal[description]") description: String,
            @Query("animal[repeats]") repeats: String,
            @Query("animal[weight]") weight: Int): Call<ExercicioRemoto>
}