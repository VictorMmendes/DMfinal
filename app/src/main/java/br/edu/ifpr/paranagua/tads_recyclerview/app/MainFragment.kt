package br.edu.ifpr.paranagua.tads_recyclerview.app

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import br.edu.ifpr.paranagua.tads_recyclerview.R
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Animal
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.dao.AnimalDaoRemoto
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.animais.BuscaTodosAnimaisListener
import br.edu.ifpr.paranagua.tads_recyclerview.ui.AnimaisAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), BuscaTodosAnimaisListener, AnimaisAdapter.AnimaisAdapterListener {
    override fun onAnimalSelected(animal: Animal) {
        listener?.onAnimalSelected(animal)
    }

    override fun onBuscaTodosAnimaisReturn(animais: List<Animal>) {
        listAnimais?.adapter = AnimaisAdapter(animais, this)
    }

    override fun onBuscaTodosAnimaisError(mensagem: String) {
        Toast.makeText(context, "ERRO: $mensagem", Toast.LENGTH_SHORT).show()
    }

    private var listener: MainFragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val layout = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        view.listAnimais.layoutManager = layout

        carregarAnimais()

        return view
    }

//    fun onButtonPressed(uri: Uri) {
//        listener?.onAnimalSelected(uri)
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
        fun onAnimalSelected(animal: Animal)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private fun carregarAnimais() {
        var dao = AnimalDaoRemoto()
        dao.buscaTodosAnimaisListener = this
        dao.buscarTodos()
    }
}