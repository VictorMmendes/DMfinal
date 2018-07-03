package br.edu.ifpr.paranagua.tads_recyclerview.remoto.dao

import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Exercicio
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.entidades.ExercicioRemoto
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.exercicios.BuscaTodosExerciciosListener
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.exercicios.ExercicioService
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.exercicios.InserirAtualizarExercicioListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExercicioDaoRemoto {
    var buscaTodosExerciciosListener: BuscaTodosExerciciosListener? = null
    var inserirAtualizarExercicioListener: InserirAtualizarExercicioListener? = null

    private var retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.110/slim/rest.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun buscarTodos() {
        var service =
                retrofit.create(ExercicioService::class.java)

        var call = service.buscaTodos()

        call.enqueue(object: Callback<List<ExercicioRemoto>> {
            override fun onFailure(call: Call<List<ExercicioRemoto>>?,
                                   t: Throwable?) {
                buscaTodosExerciciosListener?.onBuscaTodosExerciciosError("Não foi possível conectar com o WebService")
            }

            override fun onResponse(call: Call<List<ExercicioRemoto>>?,
                                    response: Response<List<ExercicioRemoto>>?) {
                var exerciciosRemotos:List<ExercicioRemoto> = response?.body()!!

                var exercicios = exerciciosRemotos.map { exercicioRemoto ->
                    exercicioRemoto.toExercicio()
                }

                buscaTodosExerciciosListener?.onBuscaTodosExerciciosReturn(exercicios)
            }

        })
    }

    fun inserir(exercicio: Exercicio) {
        var service =
                retrofit.create(ExercicioService::class.java)

        val exercicioRemoto = exercicio.toExercicioRemoto()

        var call = service.inserir(
                exercicioRemoto.description,
                exercicioRemoto.repeats,
                exercicioRemoto.weight
        )

        call.enqueue(object: Callback<ExercicioRemoto> {
            override fun onFailure(call: Call<ExercicioRemoto>?,
                                   t: Throwable?) {
                inserirAtualizarExercicioListener?.onInserirAtualizarExercicioError("Wasn't possible to connect to WebService")
            }

            override fun onResponse(call: Call<ExercicioRemoto>?,
                                    response: Response<ExercicioRemoto>?) {
                var exercicioRemoto: ExercicioRemoto = response?.body()!!
                inserirAtualizarExercicioListener?.onInserirAtualizarExercicioReturn(exercicioRemoto.toExercicio())
            }

        })
    }

    fun atualizar(exercicio: Exercicio) {
        var service =
                retrofit.create(ExercicioService::class.java)

        val exercicioRemoto = exercicio.toExercicioRemoto()

        var call = service.atualizar(
                exercicioRemoto.id!!,
                exercicioRemoto.description,
                exercicioRemoto.repeats,
                exercicioRemoto.weight
        )

        call.enqueue(object: Callback<ExercicioRemoto> {
            override fun onFailure(call: Call<ExercicioRemoto>?,
                                   t: Throwable?) {
                inserirAtualizarExercicioListener?.onInserirAtualizarExercicioError("Deu Ruim!")
            }

            override fun onResponse(call: Call<ExercicioRemoto>?,
                                    response: Response<ExercicioRemoto>?) {
                var exercicioRemoto: ExercicioRemoto = response?.body()!!
                inserirAtualizarExercicioListener?.onInserirAtualizarExercicioReturn(exercicioRemoto.toExercicio())
            }

        })
    }


}