package br.edu.ifpr.paranagua.tads_recyclerview.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import br.edu.ifpr.paranagua.tads_recyclerview.R
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Exercicio
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Modificacao
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.dao.ModificacaoDaoRemoto
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.exercicios.BuscaTodasModificacoesListener
import br.edu.ifpr.paranagua.tads_recyclerview.ui.ModificacoesAdapter
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import java.text.SimpleDateFormat

private const val ARG_EXERCICIO = "exercicio"

class DetailFragment : Fragment(), BuscaTodasModificacoesListener, ModificacoesAdapter.ModificacoesAdapterListener {
    override fun onModificacaoSelected(modificacao: Modificacao)
    {

    }

    override fun onBuscaTodasModificacoesReturn(modificacoes: List<Modificacao>)
    {
        listModificacoes?.adapter = ModificacoesAdapter(modificacoes, this)
    }

    override fun onBuscaTodasModificacoesError(mensagem: String)
    {
        Toast.makeText(context, "ERRO: $mensagem", Toast.LENGTH_SHORT).show()
    }

    private var exercicio: Exercicio? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            exercicio = it.getSerializable(ARG_EXERCICIO) as Exercicio
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        view.txtDescricao.text = exercicio?.descricao
        view.txtRepeticao.text = exercicio?.repeticao
        view.textPeso.text = "${exercicio?.peso.toString() + "Kg"}"

        val layout = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        view.listModificacoes.layoutManager = layout

        carregarModificacoes()

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(exercicio: Exercicio) =
                DetailFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_EXERCICIO, exercicio)
                    }
                }
    }

    private fun carregarModificacoes() {
        var dao = ModificacaoDaoRemoto()
        dao.buscaTodasModificacoesListener = this
        dao.buscarTodos(exercicio?.id)
    }
}
