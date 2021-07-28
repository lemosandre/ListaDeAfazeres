package com.example.listadeafazeres

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*


class ListaActivity : AppCompatActivity() {

    lateinit var textView: TextView

    private val afazerList = ArrayList<AfazerModel>()
    private lateinit var afazerAdapter: AfazerAdapter

    private lateinit var _db: DatabaseReference
    private var name: String? = ""
    private var autoNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        _db = FirebaseDatabase.getInstance().reference

        val pref = applicationContext.getSharedPreferences("MyPref", 0)

        textView = findViewById(R.id.textViewName)

        name = pref.getString("key_name", null);

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

            showCreateCategoryDialog()
        }


    }

    fun showCreateCategoryDialog() {
        val context = this
        val builder = AlertDialog.Builder(context)

        val view = layoutInflater.inflate(R.layout.alert_dialog, null)

        val titleEditText = view.findViewById<EditText>(R.id.txt_input_title)
        val bodyEditText = view.findViewById<EditText>(R.id.txt_input_body)

        builder.setView(view);
        // set up the ok button
        builder.setPositiveButton(android.R.string.ok) { dialog, p1 ->
            if (!titleEditText.text.toString().isNullOrEmpty() && !bodyEditText.text.toString().isNullOrEmpty()) {
                _db.child("Lista").child(autoNumber.toString()).setValue(AfazerModel(
                    titleEditText.text.toString(),
                    bodyEditText.text.toString(),
                    name!!
                ))
                autoNumber ++
                dialog.dismiss()
            }else{
                Snackbar.make(view, "Vaziu", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, p1 ->
            dialog.cancel()
        }

        builder.show();
    }



    private fun prepareMovieData(name: String) {
        _db.child("Lista").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (data in snapshot.children){
                        val lista = data.getValue(AfazerModel::class.java)
                        afazerList.add(lista!!)
                    }
                }
                afazerAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}