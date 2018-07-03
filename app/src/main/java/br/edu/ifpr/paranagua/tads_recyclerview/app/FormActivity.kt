package br.edu.ifpr.paranagua.tads_recyclerview.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.edu.ifpr.paranagua.tads_recyclerview.R
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Exercicio
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.dao.ExercicioDaoRemoto
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.exercicios.InserirAtualizarExercicioListener
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity(), InserirAtualizarExercicioListener {
    override fun onInserirAtualizarExercicioReturn(exercicio: Exercicio) {
        Toast.makeText(this, "SUCESSO", Toast.LENGTH_SHORT).show()
    }

    override fun onInserirAtualizarExercicioError(mensagem: String) {
        Toast.makeText(this, "ERRO", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        btSalvar.setOnClickListener {

            val exercicio = Exercicio(
                    null,
                    txtDescricao.text.toString(),
                    txtRepeticao.text.toString(),
                    txtPeso.text.toString().toInt()
            )

            val dao = ExercicioDaoRemoto()
            dao.inserirAtualizarExercicioListener = this
            dao.inserir(exercicio)
        }
    }
}
