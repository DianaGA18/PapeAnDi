package com.tesji.papeandi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Inicio : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        firebaseAuth=Firebase.auth

        val btnCerrarS : Button = findViewById(R.id.btnSalir)
        val btnInsertar : Button = findViewById(R.id.btnInsertarp)
        val btnVisualizar : Button = findViewById(R.id.btnVisualiza)
        val btnModifica : Button = findViewById(R.id.btnModificar)

        btnInsertar.setOnClickListener {
            productos()
        }

        btnModifica.setOnClickListener {
            val i = Intent(this,ActualizaElimina::class.java)
            startActivity(i)
        }
        btnCerrarS.setOnClickListener() {
            signOut()
        }
        btnVisualizar.setOnClickListener() {
            val i = Intent(this,MostrarProductos::class.java)
            startActivity(i)
        }
    }

    private fun signOut(){
        firebaseAuth.signOut()
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        return
    }
    private fun productos(){
        val i = Intent(this,InsertarProductos::class.java)
        startActivity(i)
    }
}