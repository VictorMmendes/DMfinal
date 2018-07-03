package br.edu.ifpr.paranagua.tads_recyclerview.app

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import br.edu.ifpr.paranagua.tads_recyclerview.R
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Exercicio
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.dao.ExercicioDaoRemoto
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.exercicios.BuscaTodosExerciciosListener
import br.edu.ifpr.paranagua.tads_recyclerview.ui.ExerciciosAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), BuscaTodosExerciciosListener, ExerciciosAdapter.ExerciciosAdapterListener {
    override fun onExercicioSelected(exercicio: Exercicio) {
        listener?.onExercicioSelected(exercicio)
    }

    override fun onBuscaTodosExerciciosReturn(exercicios: List<Exercicio>) {
        listExercicios?.adapter = ExerciciosAdapter(exercicios, this)
    }

    override fun onBuscaTodosExerciciosError(mensagem: String) {
        Toast.makeText(context, "ERRO: $mensagem", Toast.LENGTH_SHORT).show()
    }

    private var listener: MainFragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val layout = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        view.listExercicios.layoutManager = layout

        carregarAnimais()

        return view
    }

//    fun onButtonPressed(uri: Uri) {
//        listener?.onExercicioSelected(uri)
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement MainFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface MainFragmentListener {
        fun onExercicioSelected(exercicio: Exercicio)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private fun carregarAnimais() {
        var dao = ExercicioDaoRemoto()
        dao.buscaTodosExerciciosListener = this
        dao.buscarTodos()
    }
}