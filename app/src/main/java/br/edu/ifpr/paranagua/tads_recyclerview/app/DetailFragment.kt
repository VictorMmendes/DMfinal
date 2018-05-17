package br.edu.ifpr.paranagua.tads_recyclerview.app

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.edu.ifpr.paranagua.tads_recyclerview.R
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Animal
import kotlinx.android.synthetic.main.fragment_detail.view.*
import java.text.SimpleDateFormat

private const val ARG_ANIMAL = "animal"

class DetailFragment : Fragment() {
    private var animal: Animal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            animal = it.getSerializable(ARG_ANIMAL) as Animal
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val f = SimpleDateFormat("dd/mm/yyyy")

        view.txtNome.text = animal?.nome
        view.txtEspecie.text = animal?.especie
        view.txtRaca.text = animal?.raca
        view.txtPeso.text = animal?.peso.toString()
        view.txtNascimento.text = f.format(animal?.nascimento)
        view.txtPorte.text = animal?.porte.toString()

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(animal: Animal) =
                DetailFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_ANIMAL, animal)
                    }
                }
    }
}
