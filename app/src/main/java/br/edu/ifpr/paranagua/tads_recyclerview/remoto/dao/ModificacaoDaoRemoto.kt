package br.edu.ifpr.paranagua.tads_recyclerview.remoto.dao

import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Exercicio
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Modificacao
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.ModificacaoRemota
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.exercicios.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ModificacaoDaoRemoto {
    var buscaTodasModificacoesListener: BuscaTodasModificacoesListener? = null
    var inserirAtualizarModificacaoListener: InserirAtualizarModificacaoListener? = null

    private var retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.110/slim/rest.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun buscarTodos(id: Int?) {
        var service =
                retrofit.create(ModificacaoService::class.java)

        var call = service.buscaTodos(id)

        call.enqueue(object: Callback<List<ModificacaoRemota>> {
            override fun onFailure(call: Call<List<ModificacaoRemota>>?,
                                   t: Throwable?) {
                buscaTodasModificacoesListener?.onBuscaTodasModificacoesError("Não foi possível conectar com o WebService")
            }

            override fun onResponse(call: Call<List<ModificacaoRemota>>?,
                                    response: Response<List<ModificacaoRemota>>?) {
                var modificacoesRemotas:List<ModificacaoRemota> = response?.body()!!

                var modificacoes = modificacoesRemotas.map { modificacaoRemoto ->
                    modificacaoRemoto.toModificacao()
                }

                buscaTodasModificacoesListener?.onBuscaTodasModificacoesReturn(modificacoes)
            }

        })
    }

    fun inserir(modificacao: Modificacao) {
        var service =
                retrofit.create(ModificacaoService::class.java)

        val modificacaoRemoto = modificacao.toModificacaoRemoto()

        var call = service.inserir(
                modificacaoRemoto.progressdate,
                modificacaoRemoto.id_exercise,
                modificacaoRemoto.weight
        )

        call.enqueue(object: Callback<ModificacaoRemota> {
            override fun onFailure(call: Call<ModificacaoRemota>?,
                                   t: Throwable?) {
                inserirAtualizarModificacaoListener?.onInserirAtualizarModificacaoError("Wasn't possible to connect to WebService")
            }

            override fun onResponse(call: Call<ModificacaoRemota>?,
                                    response: Response<ModificacaoRemota>?) {
                var modificacaoRemoto: ModificacaoRemota = response?.body()!!
                inserirAtualizarModificacaoListener?.onInserirAtualizarModificacaoReturn(modificacaoRemoto.toModificacao())
            }

        })
    }

    fun atualizar(exercicio: Exercicio) {
//        var service =
//                retrofit.create(ModificacaoService::class.java)
//
//        val animalRemoto = exercicio.toExercicioRemoto()
//
//        var call = service.atualizar(
//                animalRemoto.id!!,
//                animalRemoto.description,
//                animalRemoto.repeats,
//                animalRemoto.weight
//        )
//
//        call.enqueue(object: Callback<ExercicioRemoto> {
//            override fun onFailure(call: Call<ExercicioRemoto>?,
//                                   t: Throwable?) {
//                inserirAtualizarExercicioListener?.onInserirAtualizarExercicioError("Deu Ruim!")
//            }
//
//            override fun onResponse(call: Call<ExercicioRemoto>?,
//                                    response: Response<ExercicioRemoto>?) {
//                var exercicioRemoto: ExercicioRemoto = response?.body()!!
//                inserirAtualizarExercicioListener?.onInserirAtualizarExercicioReturn(exercicioRemoto.toExercicio())
//            }
//
//        })
    }


}