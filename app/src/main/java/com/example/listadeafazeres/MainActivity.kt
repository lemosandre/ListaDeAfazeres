package com.example.listadeafazeres

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var editText: EditText
    lateinit var string: String
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        val pref = applicationContext.getSharedPreferences("MyPref", 0)

        val editor: SharedPreferences.Editor = pref.edit()

        button.setOnClickListener {
            editor.putString("key_name", editText.text.toString());
            editor.apply();
            val intent = Intent(this, ListaActivity::class.java)
            startActivity(intent)
        }
    }
}