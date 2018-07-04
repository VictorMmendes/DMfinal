package br.edu.ifpr.paranagua.tads_recyclerview.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifpr.paranagua.tads_recyclerview.R
import br.edu.ifpr.paranagua.tads_recyclerview.entidades.Exercicio

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentListener {
    override fun onAddBtClicked()
    {
        val intent = Intent(this, FormActivity::class.java)
//        intent.putExtra("cod", 2)
        startActivity(intent)
    }

    override fun onExercicioSelected(exercicio: Exercicio) {
        val fragment = DetailFragment.newInstance(exercicio)
        val t = supportFragmentManager.beginTransaction()

        if (isLandscape())
            t.replace(R.id.frameDetail, fragment)
        else {
            t.replace(R.id.frameMain, fragment)
            t.addToBackStack(null)
        }

        t.commit()
    }

    override fun onBackPressed()
    {
        moveTaskToBack(true)
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