package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding


lateinit var btnGuardar: Button


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Adrian Gonzalez G")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

        fun addNickname(view: View) {
            binding.apply {
                myName?.nickname = binding.nicknameEdit.text.toString() //Establecer nombre obtenido del usuario
                invalidateAll()
                binding.nicknameEdit.visibility = View.GONE //Ocultar textInput
                binding.doneButton.visibility = View.GONE //Ocultar boton
                binding.nicknameText.visibility = View.VISIBLE
            }

            // Hide the keyboard.

            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        binding.doneButton.setOnClickListener {
            addNickname(it)

            binding.nicknameText.setOnClickListener {
                updateNickname(it)
            }

        }


    }

    private fun updateNickname(view: View) {
        val editText = findViewById<EditText>(R.id.nickname_edit) //Ingresar nombre
        val doneButton = findViewById<Button>(R.id.done_button) //Referencia la boton

        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE
        editText.requestFocus() //Dar el foco al elemento

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0) //Mostrar teclado
    }
}