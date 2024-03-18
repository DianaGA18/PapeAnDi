package com.tesji.papeandi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class OlvidePassword : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvide_password)
        firebaseAuth=Firebase.auth
        val btnCambiar : Button = findViewById(R.id.btnEnviar)
        val txtEmailcam : TextView = findViewById(R.id.emailRecupera)

        btnCambiar.setOnClickListener (){
            cambiarContra(txtEmailcam.text.toString())
        }

    }

    private fun cambiarContra(email: String){
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(){
            task ->
            if (task.isSuccessful){
                Toast.makeText(baseContext,"Se envio el código a tu correo...", Toast.LENGTH_SHORT).show()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }else{
                Toast.makeText(baseContext,"Error al cambiar la contraseña",Toast.LENGTH_SHORT).show()
            }
        }
    }
}