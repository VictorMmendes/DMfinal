package br.edu.ifpr.paranagua.tads_recyclerview.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifpr.paranagua.tads_recyclerview.R
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Exercicio
import kotlinx.android.synthetic.main.exercicio_item.view.*

class ExerciciosAdapter(val exercicios: List<Exercicio>, val listener: ExerciciosAdapterListener?) :
        RecyclerView.Adapter<ExerciciosAdapter.ViewHolder>() {
    private var context: Context? = null

    interface ExerciciosAdapterListener {
        fun onExercicioSelected(exercicio: Exercicio)
    }

    override fun onCreateViewHolder(
            parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.exercicio_item,
                        parent, false)
        context = parent?.context
        return ViewHolder(view)
    }

    override fun getItemCount() = exercicios.size

    override fun onBindViewHolder(
            holder: ViewHolder?, position: Int) {
        val exercicio = exercicios[position]
        holder?.let {
            it.preencherView(exercicio)
        }
    }

    inner class ViewHolder(view: View) :
            RecyclerView.ViewHolder(view) {

        fun preencherView(exercicio: Exercicio) {

            itemView.txtDescricao.text = exercicio.descricao
            itemView.txtRepeticao.text = exercicio.repeticao
            itemView.txtPeso.text = "${exercicio?.peso.toString() + "Kg"}"

            itemView.setOnClickListener {
//                val intent = Intent(context, FormActivity::class.java)
//                intent.putExtra("id", exercicio.id)
//                TODO("TERMINAR")
//                context?.startActivity(intent)

                listener?.onExercicioSelected(exercicio)
            }
        }
    }
}

