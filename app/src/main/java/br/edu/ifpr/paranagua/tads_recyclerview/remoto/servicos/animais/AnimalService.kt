package br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.animais

import android.support.annotation.AnimRes
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.entidades.AnimalRemoto
import retrofit2.Call
import retrofit2.http.*

interface AnimalService {
    @GET("animals.json")
    fun buscaTodos(): Call<List<AnimalRemoto>>

    @POST("animals.json")
    fun inserir(
                @Query("animal[name]") name: String,
                @Query("animal[species]") species: String,
                @Query("animal[breed]") breed: String,
                @Query("animal[weight]") weight: Float,
                @Query("animal[birthdate]") birthdate: String,
                @Query("animal[size]") size: Int): Call<AnimalRemoto>

    @PATCH("animals/{id}.json")
    fun atualizar(
            @Path("id") id: Int,
            @Query("animal[name]") name: String,
            @Query("animal[species]") species: String,
            @Query("animal[breed]") breed: String,
            @Query("animal[weight]") weight: Float,
            @Query("animal[birthdate]") birthdate: String,
            @Query("animal[size]") size: Int): Call<AnimalRemoto>
}