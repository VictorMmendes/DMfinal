package br.edu.ifpr.paranagua.tads_recyclerview.remoto.dao

import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Animal
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.entidades.AnimalRemoto
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.animais.BuscaTodosAnimaisListener
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.animais.AnimalService
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.animais.InserirAtualizarAnimalListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnimalDaoRemoto {
    var buscaTodosAnimaisListener: BuscaTodosAnimaisListener? = null
    var inserirAtualizarAnimalListener: InserirAtualizarAnimalListener? = null

    private var retrofit = Retrofit.Builder()
            .baseUrl("http://10.20.23.189:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun buscarTodos() {
        var service =
                retrofit.create(AnimalService::class.java)

        var call = service.buscaTodos()

        call.enqueue(object: Callback<List<AnimalRemoto>> {
            override fun onFailure(call: Call<List<AnimalRemoto>>?,
                                   t: Throwable?) {
                buscaTodosAnimaisListener?.onBuscaTodosAnimaisError("Deu Ruim!")
            }

            override fun onResponse(call: Call<List<AnimalRemoto>>?,
                                    response: Response<List<AnimalRemoto>>?) {
                var animaisRemotos:List<AnimalRemoto> = response?.body()!!

                var animais = animaisRemotos.map { animalRemoto ->
                    animalRemoto.toAnimal()
                }

                buscaTodosAnimaisListener?.onBuscaTodosAnimaisReturn(animais)
            }

        })
    }

    fun inserir(animal: Animal) {
        var service =
                retrofit.create(AnimalService::class.java)

        val animalRemoto = animal.toAnimalRemoto()

        var call = service.inserir(
                animalRemoto.name,
                animalRemoto.species,
                animalRemoto.breed,
                animalRemoto.weight,
                animalRemoto.birthdate,
                animalRemoto.size
        )

        call.enqueue(object: Callback<AnimalRemoto> {
            override fun onFailure(call: Call<AnimalRemoto>?,
                                   t: Throwable?) {
                inserirAtualizarAnimalListener?.onInserirAtualizarAnimalError("Deu Ruim!")
            }

            override fun onResponse(call: Call<AnimalRemoto>?,
                                    response: Response<AnimalRemoto>?) {
                var animalRemoto:AnimalRemoto = response?.body()!!
                inserirAtualizarAnimalListener?.onInserirAtualizarAnimalReturn(animalRemoto.toAnimal())
            }

        })
    }

    fun atualizar(animal: Animal) {
        var service =
                retrofit.create(AnimalService::class.java)

        val animalRemoto = animal.toAnimalRemoto()

        var call = service.atualizar(
                animalRemoto.id!!,
                animalRemoto.name,
                animalRemoto.species,
                animalRemoto.breed,
                animalRemoto.weight,
                animalRemoto.birthdate,
                animalRemoto.size
        )

        call.enqueue(object: Callback<AnimalRemoto> {
            override fun onFailure(call: Call<AnimalRemoto>?,
                                   t: Throwable?) {
                inserirAtualizarAnimalListener?.onInserirAtualizarAnimalError("Deu Ruim!")
            }

            override fun onResponse(call: Call<AnimalRemoto>?,
                                    response: Response<AnimalRemoto>?) {
                var animalRemoto:AnimalRemoto = response?.body()!!
                inserirAtualizarAnimalListener?.onInserirAtualizarAnimalReturn(animalRemoto.toAnimal())
            }

        })
    }


}