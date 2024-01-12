package com.example.tdmpa_512_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AlumnoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumno)

        val dbStudent = DatabaseStudent(this)
        val txtNombre = findViewById<TextView>(R.id.txtNombreA)
        val txtApellidos = findViewById<TextView>(R.id.txtApellidosA)
        val txtCarrera = findViewById<TextView>(R.id.txtCarreraA)
        val txtCreditosC = findViewById<TextView>(R.id.txtCreditosCA)
        val txtCreditosD = findViewById<TextView>(R.id.txtCreditosDA)
        val btnSalir = findViewById<Button>(R.id.btnRegresarInicioAlumno)

        var nombreA = intent.getStringExtra("nombreAlumno")
        var apellidosA = intent.getStringExtra("apellidoAlumno")
        val retrivedStudent = dbStudent.getStudentByName(nombreA.toString())
        if(retrivedStudent != null){
            txtNombre.text = "Nombre: ${nombreA.toString()}"
            txtApellidos.text = "Apellidos: ${apellidosA.toString()}"
            txtCarrera.text = "Carrera: ${retrivedStudent.carrera}"
            txtCreditosC.text = "Creditos Culturales: ${retrivedStudent.creditos_culturales}"
            txtCreditosD.text = "Creditos Deportivos: ${retrivedStudent.creditos_deportivos}"
        }

        btnSalir.setOnClickListener{
            finish();
        }


    }
}