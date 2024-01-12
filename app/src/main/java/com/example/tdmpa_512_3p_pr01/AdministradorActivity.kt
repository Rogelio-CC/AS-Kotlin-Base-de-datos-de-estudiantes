package com.example.tdmpa_512_3p_pr01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AdministradorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrador)

        val txtNombre = findViewById<TextView>(R.id.txtNombreAdmin)
        val btnConsultar = findViewById<Button>(R.id.btnConsultar)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnCambiarPassword = findViewById<Button>(R.id.btnCambiarPassword)
        val btnAsignarCreditos = findViewById<Button>(R.id.btnAsignarCreditos)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        val btnSalir = findViewById<Button>(R.id.btnRegresarInicioAdmin)

        val nombreAdmin = intent.getStringExtra("nombreAdmin")
        txtNombre.text = "¡Bienvenido, $nombreAdmin!"

        btnConsultar.setOnClickListener{
            val intento1 = Intent(this@AdministradorActivity, ConsultarAlumnosActivity::class.java);
            startActivity(intento1)
        }

        btnAgregar.setOnClickListener{
            val intento2 = Intent(this@AdministradorActivity, AgregarAlumnosActivity::class.java);
            startActivity(intento2)
        }

        btnCambiarPassword.setOnClickListener{
            val intento3 = Intent(this@AdministradorActivity, CambiarPasswordActivity::class.java);
            startActivity(intento3)
        }

        btnAsignarCreditos.setOnClickListener{
            val intento4 = Intent(this@AdministradorActivity, AsignarCreditosActivity::class.java);
            startActivity(intento4)
        }

        btnEliminar.setOnClickListener{
            val intento5 = Intent(this@AdministradorActivity, EliminarActivity::class.java);
            startActivity(intento5)
        }

        btnSalir.setOnClickListener{
            finish()
        }
    }
}