package com.example.tdmpa_512_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EliminarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar)

        val dbStudent = DatabaseStudent(this)
        val txtNombre = findViewById<EditText>(R.id.txtNombreEliminar)
        val txtApellidos = findViewById<EditText>(R.id.txtApellidosEliminar)
        val btnEliminar = findViewById<Button>(R.id.btnEliminarA)

        btnEliminar.setOnClickListener{
           if(txtNombre.text.isNotEmpty() && txtApellidos.text.isNotEmpty()){

               val retrivedStudent = dbStudent.getStudentByName(txtNombre.text.toString())
               if(retrivedStudent != null){
                   if(retrivedStudent.nombre.equals(txtNombre.text.toString()) && retrivedStudent.apellidos.equals(txtApellidos.text.toString())){
                       dbStudent.deleteStudent(retrivedStudent.id)
                       Toast.makeText(this@EliminarActivity, "Se ha eliminado el alumno", Toast.LENGTH_SHORT).show()
                       finish()
                   }
                   else{
                       Toast.makeText(this@EliminarActivity, "No coinciden nombre y apellidos", Toast.LENGTH_SHORT).show()
                   }
               }

           }
            else{
               Toast.makeText(this@EliminarActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
           }
        }

    }
}