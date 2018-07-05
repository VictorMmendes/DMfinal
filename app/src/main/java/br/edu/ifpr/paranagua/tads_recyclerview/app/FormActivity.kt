package br.edu.ifpr.paranagua.tads_recyclerview.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.edu.ifpr.paranagua.tads_recyclerview.R
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Exercicio
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.dao.ExercicioDaoRemoto
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.exercicios.InserirAtualizarExercicioListener
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity(), InserirAtualizarExercicioListener {
    override fun onInserirAtualizarExercicioReturn(str: String) {
        Toast.makeText(this, "$str", Toast.LENGTH_SHORT).show()
        backHome()
    }

    private fun backHome()
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onInserirAtualizarExercicioError(mensagem: String) {
        Toast.makeText(this, "$mensagem", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        btSalvar.setOnClickListener({
            if(txtDescricao.text.toString().isBlank())
            {
                Toast.makeText(this, "Preencha o campo descrição", Toast.LENGTH_SHORT).show()
            } else if(txtPeso.text.toString().isBlank()){
                Toast.makeText(this, "Preencha o campo Peso", Toast.LENGTH_SHORT).show()
            } else {
                salvar()
            }
        })

        cancelarBt.setOnClickListener{
            backHome()
        }
    }

    fun salvar()
    {
        var repeticao = "${txtSerie.selectedItem.toString() + "x" + txtRepeticao.selectedItem.toString()}"

        val exercicio = Exercicio(
                null,
                txtDescricao.text.toString(),
                repeticao,
                txtPeso.text.toString().toInt()
        )

        val dao = ExercicioDaoRemoto()
        dao.inserirAtualizarExercicioListener = this
        dao.inserir(exercicio)
    }
}
