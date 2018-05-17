package br.edu.ifpr.paranagua.tads_recyclerview.app

import android.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.edu.ifpr.paranagua.tads_recyclerview.R
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Animal
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.dao.AnimalDaoRemoto
import br.edu.ifpr.paranagua.tads_recyclerview.remoto.servicos.animais.BuscaTodosAnimaisListener
import br.edu.ifpr.paranagua.tads_recyclerview.ui.AnimaisAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentListener {
    override fun onAnimalSelected(animal: Animal) {
        val fragment = DetailFragment.newInstance(animal)
        val t = supportFragmentManager.beginTransaction()

        if (isLandscape())
            t.replace(R.id.frameDetail, fragment)
        else {
            t.replace(R.id.frameMain, fragment)
            t.addToBackStack(null)
        }

        t.commit()
    }

    private fun isLandscape() = resources.getBoolean(R.bool.landscape)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.frameMain, fragment)
                .commit()

    }
}