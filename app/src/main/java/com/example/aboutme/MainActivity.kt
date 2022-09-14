package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

lateinit var btnGuardar: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        fun addNickname(view: View){
            val editText=findViewById<EditText>(R.id.nickname_edit) //Ingresar nombre
            val nicknameTextView=findViewById<TextView>(R.id.nickname_text) //Mostrar nombre

            nicknameTextView.text=editText.text //Establecer nombre obtenido del usuario
            editText.visibility=View.GONE //Ocultar textInput
            view.visibility=View.GONE //Ocultar boton
            nicknameTextView.visibility=View.VISIBLE
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        findViewById<Button>(R.id.done_button).setOnClickListener{
           addNickname(it)

        findViewById<TextView>(R.id.nickname_text).setOnClickListener{
            updateNickname(it)
        }

        }


    }
    private fun updateNickname (view: View){
        val editText=findViewById<EditText>(R.id.nickname_edit) //Ingresar nombre
        val doneButton=findViewById<Button>(R.id.done_button) //Referencia la boton

        editText.visibility=View.VISIBLE
        doneButton.visibility=View.VISIBLE
        view.visibility=View.GONE
        editText.requestFocus() //Dar el foco al elemento

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0) //Mostrar teclado
    }
}