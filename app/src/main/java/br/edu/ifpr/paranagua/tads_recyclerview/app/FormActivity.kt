package br.edu.ifpr.paranagua.tads_recyclerview.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.edu.ifpr.paranagua.tads_recyclerview.R
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Animal
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.dao.AnimalDaoRemoto
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.animais.InserirAtualizarAnimalListener
import kotlinx.android.synthetic.main.activity_form.*
import java.text.SimpleDateFormat

class FormActivity : AppCompatActivity(), InserirAtualizarAnimalListener {
    override fun onInserirAtualizarAnimalReturn(animal: Animal) {
        Toast.makeText(this, "SUCESSO", Toast.LENGTH_SHORT).show()
    }

    override fun onInserirAtualizarAnimalError(mensagem: String) {
        Toast.makeText(this, "ERRO", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        btSalvar.setOnClickListener {

            val porte = when {
                txtPorte.text.toString().toInt() == 0 -> Animal.Tamanho.P
                txtPorte.text.toString().toInt() == 1 -> Animal.Tamanho.M
                else -> Animal.Tamanho.G
            }

            val f = SimpleDateFormat("yyyy-mm-dd")

            val animal = Animal(
                    null,
                    txtNome.text.toString(),
                    txtEspecie.text.toString(),
                    txtRaca.text.toString(),
                    txtPeso.text.toString().toFloat(),
                    f.parse(txtNascimento.text.toString()),
                    porte
            )

            val dao = AnimalDaoRemoto()
            dao.inserirAtualizarAnimalListener = this
            dao.inserir(animal)
        }
    }
}
