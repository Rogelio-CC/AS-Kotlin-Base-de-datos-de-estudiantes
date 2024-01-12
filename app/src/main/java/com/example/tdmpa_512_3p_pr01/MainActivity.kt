package com.example.tdmpa_512_3p_pr01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtNombre = findViewById<EditText>(R.id.txtNombreLogin)
        val txtApellidos = findViewById<EditText>(R.id.txtApellidosLogin)
        val txtPassword = findViewById<EditText>(R.id.txtPasswordLogin)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        btnEntrar.setOnClickListener{
            if(txtNombre.text.isNotEmpty() && txtApellidos.text.isNotEmpty() && txtPassword.text.isNotEmpty()){

                val verdad = entrar(txtNombre.text.toString(), txtApellidos.text.toString(), txtPassword.text.toString())
                var existencia = entrarComoAlumno(txtNombre.text.toString(), txtApellidos.text.toString(), txtPassword.text.toString())
                if(verdad){
                    val intento = Intent(this@MainActivity, AdministradorActivity::class.java);
                    intento.putExtra("nombreAdmin", txtNombre.text.toString())
                    startActivity(intento);
                }
                else{
                    if(existencia){
                        val intento2 = Intent(this@MainActivity, AlumnoActivity::class.java);
                        intento2.putExtra("nombreAlumno", txtNombre.text.toString())
                        intento2.putExtra("apellidoAlumno", txtApellidos.text.toString())
                        startActivity(intento2);
                    }
                }

            }
            else{
                Toast.makeText(this@MainActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun entrar(nombre: String, apellidos: String, password: String): Boolean{
        var verdadero = false
        //val dbLogin = DatabaseSalle(this)
        //val loginModel = LoginModel(0, "Rogelio","Ceballos Castillo", "123456789")
        //dbLogin.addLogin(loginModel)
        if(nombre == "Rogelio" && apellidos == "Ceballos Castillo"){
            if (password == "123456789"){
                verdadero = true
            }
            else{
                verdadero = false
                Toast.makeText(this@MainActivity, "Contraseña o nombre completo incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
        return verdadero
    }

    fun entrarComoAlumno(nombre: String, apellidos: String, password: String): Boolean{
        var existencia = false
        val dbStudent = DatabaseStudent(this)
        val retrivedStudent = dbStudent.getStudentByName(nombre)
        if(retrivedStudent != null){
            if(retrivedStudent.nombre.equals(nombre) && retrivedStudent.apellidos.equals(apellidos)){
                if(retrivedStudent.password.equals(password)){
                    existencia = true
                }
                else{
                    existencia = false
                    Toast.makeText(this@MainActivity, "Contraseña o nombre completo incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return existencia
    }
}