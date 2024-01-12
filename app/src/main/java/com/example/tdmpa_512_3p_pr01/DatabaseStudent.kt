package com.example.tdmpa_512_3p_pr01

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseStudent(context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "StudentDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "StudentTable"
        private const val KEY_ID = "_id"
        private const val KEY_NOMBRE_ESTUDIANTE = "nombre"
        private const val KEY_APELLIDOS_ESTUDIANTE = "apellidos"
        private const val KEY_CARRERA = "carrera"
        private const val KEY_CREDITOS_CULTURALES = "culturales"
        private const val KEY_CREDITOS_DEPORTIVOS = "deportivos"
        private const val KEY_PASSWORD_ESTUDIANTE = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE ${TABLE_NAME} (${KEY_ID} INTEGER PRIMARY KEY, ${KEY_NOMBRE_ESTUDIANTE} TEXT, ${KEY_APELLIDOS_ESTUDIANTE} TEXT, ${KEY_CARRERA} TEXT, ${KEY_CREDITOS_CULTURALES} INTEGER, ${KEY_CREDITOS_DEPORTIVOS} INTEGER, ${KEY_PASSWORD_ESTUDIANTE} TEXT);")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${TABLE_NAME}")
        onCreate(db)
    }

    fun addStudent(StudentModel: StudentModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NOMBRE_ESTUDIANTE, StudentModel.nombre)
        values.put(KEY_APELLIDOS_ESTUDIANTE, StudentModel.apellidos)
        values.put(KEY_CARRERA, StudentModel.carrera)
        values.put(KEY_CREDITOS_CULTURALES, StudentModel.creditos_culturales)
        values.put(KEY_CREDITOS_DEPORTIVOS, StudentModel.creditos_deportivos)
        values.put(KEY_PASSWORD_ESTUDIANTE, StudentModel.password)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getStudentByName(nombreE: String): StudentModel?{
        val db = this.readableDatabase//consultar base de datos
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(
                KEY_ID,
                KEY_NOMBRE_ESTUDIANTE,
                KEY_APELLIDOS_ESTUDIANTE,
                KEY_CARRERA,
                KEY_CREDITOS_CULTURALES,
                KEY_CREDITOS_DEPORTIVOS,
                KEY_PASSWORD_ESTUDIANTE
            ),
            "${KEY_NOMBRE_ESTUDIANTE}=?",
            arrayOf(nombreE),
            null,
            null,
            null
        )
        return if(cursor.moveToFirst()){
            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val apellidosE = cursor.getString(cursor.getColumnIndex(KEY_APELLIDOS_ESTUDIANTE))
            val carrera = cursor.getString(cursor.getColumnIndex(KEY_CARRERA))
            val creditos_c = cursor.getInt(cursor.getColumnIndex(KEY_CREDITOS_CULTURALES))
            val creditos_d = cursor.getInt(cursor.getColumnIndex(KEY_CREDITOS_DEPORTIVOS))
            val password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD_ESTUDIANTE))
            StudentModel(id, nombreE, apellidosE, carrera, creditos_c, creditos_d, password)
        }
        else{
            null
        }
    }
    fun UpdateStudent(studentModel: StudentModel){
        val db = this.writableDatabase//escribir en la base de datos
        val values = ContentValues();
        values.put(KEY_NOMBRE_ESTUDIANTE, studentModel.nombre)
        values.put(KEY_APELLIDOS_ESTUDIANTE, studentModel.apellidos)
        values.put(KEY_CARRERA, studentModel.carrera)
        values.put(KEY_CREDITOS_CULTURALES, studentModel.creditos_culturales)
        values.put(KEY_CREDITOS_DEPORTIVOS, studentModel.creditos_deportivos)
        values.put(KEY_PASSWORD_ESTUDIANTE, studentModel.password)
        db.update(TABLE_NAME, values, "${KEY_ID}=?", arrayOf(studentModel.id.toString()))

    }


    fun deleteStudent(id: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id.toString()))
        return result > 0
    }



}
data class StudentModel
    (val id: Int,
     val nombre: String,
     val apellidos: String,
     val carrera: String,
     val creditos_culturales: Int,
     val creditos_deportivos: Int,
     val password: String)