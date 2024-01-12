package com.example.tdmpa_512_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ConsultarAlumnosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_alumnos)

        val dbStudent = DatabaseStudent(this)
        val txtNombre = findViewById<EditText>(R.id.txtNombreAConsulta)
        val txtApellidos = findViewById<EditText>(R.id.txtApellidosAConsulta)
        val txtConsulta = findViewById<EditText>(R.id.txtConsultarAlumnos)
        val btnConsultar = findViewById<Button>(R.id.btnConsultarA)

        btnConsultar.setOnClickListener{
            if(txtNombre.text.isNotEmpty() && txtApellidos.text.isNotEmpty()){

                val retrivedStudent = dbStudent.getStudentByName(txtNombre.text.toString())
                if(retrivedStudent != null){
                    if(retrivedStudent.nombre.equals(txtNombre.text.toString()) && retrivedStudent.apellidos.equals(txtApellidos.text.toString())){
                        txtConsulta.setText(retrivedStudent.nombre + "      " + retrivedStudent.apellidos + "       " + retrivedStudent.password)
                    }
                    else{
                        Toast.makeText(this@ConsultarAlumnosActivity, "No coinciden nombre y apellidos", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            else{
                Toast.makeText(this@ConsultarAlumnosActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
            }
        }


    }
}