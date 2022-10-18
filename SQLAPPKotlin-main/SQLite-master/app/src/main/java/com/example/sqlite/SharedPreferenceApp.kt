package com.example.sqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class SharedPreferenceApp : AppCompatActivity() {
    lateinit var etd_Nombre:EditText
    lateinit var edt_Apellido:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference_app)

        etd_Nombre= findViewById(R.id.etd_Nombre)
        edt_Apellido= findViewById(R.id.edt_Apellido)

        var pref = getSharedPreferences("datosPersonas", Context.MODE_PRIVATE)
        var nombre= pref.getString("nombre", "")
        var apellido = pref.getString("apellido","")
        etd_Nombre.setText(nombre)
        edt_Apellido.setText(apellido)

    }
    fun guardar (vista:View){
        var pref = getSharedPreferences("datosPersonas", Context.MODE_PRIVATE)
        var editor = pref.edit()

        editor.putString("nombre",etd_Nombre.text.toString())
        editor.putString("apellido",edt_Apellido.text.toString())
        editor.commit()
            Toast.makeText(this,"se ah guardado exitosamente",Toast.LENGTH_SHORT).show()
    }
}