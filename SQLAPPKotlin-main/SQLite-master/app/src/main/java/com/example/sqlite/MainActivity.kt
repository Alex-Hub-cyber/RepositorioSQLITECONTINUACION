package com.example.sqlite

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.contentValuesOf

class MainActivity : AppCompatActivity() {

    private lateinit var edt_Codigo:EditText
    private lateinit var edt_Descricion:EditText
    private lateinit var edt_Precio:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt_Codigo = findViewById(R.id.edt_Codigo)
        edt_Descricion = findViewById(R.id.edt_Descripcion)
        edt_Precio = findViewById(R.id.edt_Precio)
    }

    //funcion para registrar precio
    fun registrar(V: View){
        // se crea la conecion a la base de datos
        val admin = AdminSQLLite(this,"Tienda",null,1)
        val baseDeDatos:SQLiteDatabase= admin.writableDatabase

        val codigo = edt_Codigo.text.toString()
        val descripcion = edt_Descricion.text.toString()
        val precio= edt_Precio.text.toString()

        if(!codigo.isEmpty()&&!descripcion.isEmpty()&&!precio.isEmpty()){
            val registro = ContentValues()
            registro.put("codigo",codigo)
            registro.put("descripcion",descripcion)
            registro.put("precio",precio)

            baseDeDatos.insert("articulos",null,registro)
            baseDeDatos.close()
            edt_Codigo.setText("")
            edt_Descricion.setText("")
            edt_Precio.setText("")

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
      fun Buscar(v:View){
     // se crea la conecion a la base de datos
     val admin = AdminSQLLite(this,"Tienda",null,1)
     val baseDeDatos:SQLiteDatabase= admin.writableDatabase

     val codigo = edt_Codigo.text.toString()
     if (!codigo.isEmpty()){

         val fila = baseDeDatos.rawQuery("select description, precio from articlos  where codigo = $codigo", null)
         if (fila.moveToFirst()){

             edt_Descricion.setText(fila.getString(0))

             edt_Precio.setText((fila.getString(1)))

             baseDeDatos.close()

         }else {
             Toast.makeText(this,"No existe el articulo",Toast.LENGTH_SHORT).show()
             baseDeDatos.close()
         }
     }else{
         Toast.makeText(this, "Datos ingresar un codigo", Toast.LENGTH_SHORT).show()
     }
 }
     fun Modificar (Vista:View){
         // se crea la conecion a la base de datos
         val admin = AdminSQLLite(this,"Tienda",null,1)
         val baseDeDatos:SQLiteDatabase= admin.writableDatabase

         val codigo = edt_Codigo.text.toString()
         val descripcion = edt_Descricion.text.toString()
         val precio= edt_Precio.text.toString()
         if(!codigo.isEmpty()&&!descripcion.isEmpty()&&!precio.isEmpty()){
             val registro = ContentValues()
            // registro.put("codigo",codigo)
             registro.put("descripcion",descripcion)
             registro.put("precio",precio)

             val cantidad :Int = baseDeDatos.update("articulos",registro,"codigo= $codigo",null)
             baseDeDatos.close()

             if (cantidad==1){

                 Toast.makeText(this,"Articulo modificado correctamente",Toast.LENGTH_SHORT).show()
             }else{
                 Toast.makeText(this,"No existe el articulo", Toast.LENGTH_SHORT).show()
                 baseDeDatos.close()
             }
             edt_Codigo.setText("")
             edt_Descricion.setText("")
             edt_Precio.setText("")
             Toast.makeText(this,"registro exitoso", Toast.LENGTH_SHORT).show()
         }
else{

    Toast.makeText(this,"Debes de rellenar todos los campos", Toast.LENGTH_SHORT).show()
}

     }

    fun eliminar(Vista: View){
        // se crea la conecion a la base de datos
        val admin = AdminSQLLite(this,"Tienda",null,1)
        val baseDeDatos:SQLiteDatabase= admin.writableDatabase

        val codigo = edt_Codigo.text.toString()
        if(!codigo.isEmpty()){
            val cantidad:Int = baseDeDatos.delete("articulos", "codigo= ${codigo}",null)

            baseDeDatos.close()
            edt_Codigo.setText("")
            edt_Descricion.setText("")
            edt_Precio.setText("")


        if (cantidad==1) {
            Toast.makeText(this,"Articulo eliminado",Toast.LENGTH_SHORT).show()
            baseDeDatos.close()
        }
            else{
                Toast.makeText(this,"No existe el articulo",Toast.LENGTH_SHORT).show()
            baseDeDatos.close()
            }

        } else{
            Toast.makeText(this,"Debes de ingresar un codigo",Toast.LENGTH_SHORT).show()
           baseDeDatos.close()
        }
}

    fun siguiente(Vista: View){
        val ventana : Intent=Intent(applicationContext,SharedPreferenceApp::class.java)
        startActivity(ventana)
    }
    }

