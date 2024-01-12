package com.example.tdmpa_512_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AgregarAlumnosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_alumnos)

        val dbStudent = DatabaseStudent(this)
        val txtNombreAlumno = findViewById<EditText>(R.id.txtNombreAlumno)
        val txtApellidosAlumnos = findViewById<EditText>(R.id.txtApellidosAlumno)
        val txtPasswordAlumno = findViewById<EditText>(R.id.txtPasswordAlumno)
        val txtCarreraAlumno = findViewById<EditText>(R.id.txtCarreraAlumno)
        val txtCreditosC_Alumno = findViewById<EditText>(R.id.txtCreditosCulturalesAlumno)
        val txtCreditosD_Alumno = findViewById<EditText>(R.id.txtCreditosDeportivosAlumno)
        val btnAgregarA = findViewById<Button>(R.id.btnAgregarAlumno)

        btnAgregarA.setOnClickListener{
            if(txtNombreAlumno.text.isNotEmpty() && txtApellidosAlumnos.text.isNotEmpty() && txtPasswordAlumno.text.isNotEmpty()
                && txtCarreraAlumno.text.isNotEmpty() && txtCreditosC_Alumno.text.isNotEmpty() && txtCreditosD_Alumno.text.isNotEmpty()){

                var studentModel = StudentModel(
                    0,
                    txtNombreAlumno.text.toString(),
                    txtApellidosAlumnos.text.toString(),
                    txtCarreraAlumno.text.toString(),
                    txtCreditosC_Alumno.text.toString().toInt(),
                    txtCreditosD_Alumno.text.toString().toInt(),
                    txtPasswordAlumno.text.toString()
                )
                dbStudent.addStudent(studentModel);
                Toast.makeText(this@AgregarAlumnosActivity, "Alumno agregado", Toast.LENGTH_SHORT).show()
                finish()

            }
            else{
                Toast.makeText(this@AgregarAlumnosActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
            }

        }
    }
}