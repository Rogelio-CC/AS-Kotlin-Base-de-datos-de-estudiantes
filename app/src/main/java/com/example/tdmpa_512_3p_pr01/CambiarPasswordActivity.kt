package com.example.tdmpa_512_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CambiarPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_password)

        val dbStudent = DatabaseStudent(this)
        val txtNombreA = findViewById<EditText>(R.id.txtNombreAlumnoPassword)
        val txtApellidosA = findViewById<EditText>(R.id.txtApellidosAlumnoPassword)
        val txtNuevoPassword = findViewById<EditText>(R.id.txtNuevaPassword)
        val txtConfirmarPassword = findViewById<EditText>(R.id.txtConfirmarPassword)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarCambioPassword)



        btnGuardar.setOnClickListener{
            if(txtNombreA.text.isNotEmpty() && txtApellidosA.text.isNotEmpty() && txtNuevoPassword.text.isNotEmpty() && txtConfirmarPassword.text.isNotEmpty()){

                val retrivedStudent = dbStudent.getStudentByName(txtNombreA.text.toString())
                if(retrivedStudent != null){
                    if(retrivedStudent.nombre.equals(txtNombreA.text.toString()) && retrivedStudent.apellidos.equals(txtApellidosA.text.toString())){
                        if(txtNuevoPassword.text.toString().equals(txtConfirmarPassword.text.toString())){

                            var studentModel = StudentModel(
                                retrivedStudent.id.toString().toInt(),
                                retrivedStudent.nombre,
                                retrivedStudent.apellidos,
                                retrivedStudent.carrera,
                                retrivedStudent.creditos_culturales.toString().toInt(),
                                retrivedStudent.creditos_deportivos.toString().toInt(),
                                txtNuevoPassword.text.toString()
                            )
                            dbStudent.UpdateStudent(studentModel)
                            Toast.makeText(this@CambiarPasswordActivity, "Se cambió la contraseña para ${retrivedStudent.nombre}", Toast.LENGTH_SHORT).show()
                            finish()

                        }
                        else{
                            Toast.makeText(this@CambiarPasswordActivity, "Confirme contraseña", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this@CambiarPasswordActivity, "No coinciden nombre y apellidos", Toast.LENGTH_SHORT).show()
                    }

                }

            }
            else{
                Toast.makeText(this@CambiarPasswordActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
            }

        }




    }
}