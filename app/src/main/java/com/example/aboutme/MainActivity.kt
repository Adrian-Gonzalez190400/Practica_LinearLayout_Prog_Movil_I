package com.example.aboutme

import android.content.Context
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding





class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Adrian Gonzalez G")

    var mode: ActionMode? = null
    private var original: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        original = binding.bioText.text.toString()
        binding.myName = myName

        registerForContextMenu(binding.bioText) //asignar la referencia al widget

        /*Accion Contextual*/
        /*Asignar el long click listener al componente de imagen*/
        findViewById<ImageView>(R.id.image).setOnLongClickListener{ view ->
            // Called when the user long-clicks on someView
            when (mode) {

                null -> {
                    // Start the CAB using the ActionMode.Callback defined above
                    mode = startActionMode(actionModeCallback)
                    view.isSelected = true
                    true
                }
                else -> false
            }
        }

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

    /*Mostrar menu*/
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_contextual_bio,menu) //jerarquia de menus
    }


    /*Verificar que item del menu se seleccionó*/
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var numero = item.itemId
        when(numero){
            R.id.mayusculasBio -> binding.bioText.setText(binding.bioText.text.toString().uppercase())
            R.id.minusculasBio -> binding.bioText.setText(binding.bioText.text.toString().lowercase())
            R.id.originalBio -> binding.bioText.setText(original.toString())
        }

        return super.onContextItemSelected(item)
    }

    /*Accion contextual*/
    private val actionModeCallback = object : ActionMode.Callback {
        // Called when the action mode is created; startActionMode() was called
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.menu_contextual_action, menu)
            mode.setTitle("Seleccionar Acción")
            return true
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
             when (item.itemId) {
                R.id.menu_item_1 -> Toast.makeText(applicationContext,"Like",Toast.LENGTH_LONG).show()
                R.id.menu_item_2 -> Toast.makeText(applicationContext,"Share",Toast.LENGTH_LONG).show()
            }
            return false
        }

        // Called when the user exits the action mode
        override fun onDestroyActionMode(Mode: ActionMode) {
            mode = null
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