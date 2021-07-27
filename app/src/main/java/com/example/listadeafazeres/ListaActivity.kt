package com.example.listadeafazeres

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ListaActivity : AppCompatActivity() {

    lateinit var textView: TextView

    private val afazerList = ArrayList<AfazerModel>()
    private lateinit var afazerAdapter: AfazerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val pref = applicationContext.getSharedPreferences("MyPref", 0)

        textView = findViewById(R.id.textViewName)

        val name = pref.getString("key_name", null);

        textView.text = name

        val actionbar = supportActionBar

        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        afazerAdapter = AfazerAdapter(afazerList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = afazerAdapter
        name?.let { prepareMovieData(it) }

        val floatingActionButton: FloatingActionButton = findViewById(R.id.floatingButton)

        floatingActionButton.setOnClickListener{

            Snackbar.make(it, "Item Added to List", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    private fun prepareMovieData(name: String) {
        var movie = AfazerModel("Mad Max: Fury Road", "Action & Adventure", name)
        afazerList.add(movie)
        movie = AfazerModel("Inside Out", "Animation, Kids & Family", name)
        afazerList.add(movie)
        movie = AfazerModel("Inside Out", "Animation, Kids & Family", name)
        afazerList.add(movie)
        movie = AfazerModel("Inside Out", "Animation, Kids & Family", name)
        afazerList.add(movie)
        movie = AfazerModel("Inside Out", "Animation, Kids & Family", name)
        afazerList.add(movie)
        movie = AfazerModel("Inside Out", "Animation, Kids & Family", name)
        afazerList.add(movie)
        movie = AfazerModel("Inside Out", "Animation, Kids & Family", name)
        afazerList.add(movie)
        movie = AfazerModel("Inside Out", "Animation, Kids & Family", name)
        afazerList.add(movie)
        movie = AfazerModel("Inside Out", "Animation, Kids & Family", name)
        afazerList.add(movie)
        movie = AfazerModel("Inside Out", "Animation, Kids & Family", name)
        afazerList.add(movie)
        afazerAdapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}