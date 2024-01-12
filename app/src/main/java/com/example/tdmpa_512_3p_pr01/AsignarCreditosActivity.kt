package com.example.tdmpa_512_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AsignarCreditosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asignar_creditos)

        val dbStudent = DatabaseStudent(this)
        val txtNombre = findViewById<EditText>(R.id.txtNombreAlumnoCreditos)
        val txtApellidos = findViewById<EditText>(R.id.txtApellidosAlumnoCreditos)
        val txtCreditosC = findViewById<EditText>(R.id.txtModificarCreditosCulturales)
        val txtCreditosD = findViewById<EditText>(R.id.txtModificarCreditosDeportivos)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarCambioCreditos)

        btnGuardar.setOnClickListener {
            if(txtNombre.text.isNotEmpty() && txtApellidos.text.isNotEmpty() && txtCreditosC.text.isNotEmpty() && txtCreditosD.text.isNotEmpty()){

                val retrivedStudent = dbStudent.getStudentByName(txtNombre.text.toString())
                if (retrivedStudent != null) {
                    if(retrivedStudent.nombre.equals(txtNombre.text.toString()) && retrivedStudent.apellidos.equals(txtApellidos.text.toString())){
                        var studentModel = StudentModel(
                            retrivedStudent.id.toString().toInt(),
                            retrivedStudent.nombre,
                            retrivedStudent.apellidos,
                            retrivedStudent.carrera,
                            txtCreditosC.text.toString().toInt(),
                            txtCreditosD.text.toString().toInt(),
                            retrivedStudent.password
                        )
                        dbStudent.UpdateStudent(studentModel)
                        Toast.makeText(this@AsignarCreditosActivity, "Se modificaron los creditos para ${retrivedStudent.nombre}", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else{
                        Toast.makeText(this@AsignarCreditosActivity, "No coinciden nombre y apellidos", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            else{
                Toast.makeText(this@AsignarCreditosActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}