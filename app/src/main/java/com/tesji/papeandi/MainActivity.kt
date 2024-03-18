package com.tesji.papeandi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnIngresar : Button = findViewById(R.id.btnAcep)
        val txtEmail : TextView = findViewById(R.id.tvCorreo)
        val txtContra : TextView = findViewById(R.id.tvContrasen)
        val btnCrearC : TextView = findViewById(R.id.creaCuenta)
        val btnOlvide : TextView = findViewById(R.id.olvideC)
        firebaseAuth = Firebase.auth

        val emailIngresado = txtEmail.text.toString()
        val passIngresado = txtContra.text.toString()

        btnIngresar.setOnClickListener() {

            signIn(txtEmail.text.toString(),txtContra.text.toString())
        }

        btnCrearC.setOnClickListener() {
            val i = Intent (this, CrearCuenta::class.java)
            startActivity(i)
        }

        btnOlvide.setOnClickListener (){
            val i = Intent (this, OlvidePassword::class.java)
            startActivity(i)
        }
    }
    private fun signIn(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password).
        addOnCompleteListener(this) { task ->
            if (task.isSuccessful){
                val user = firebaseAuth.currentUser
                val verifica = user?.isEmailVerified
                if(verifica==true){
                    val i = Intent(this, Inicio::class.java)
                    startActivity(i)
                    //AQUI VAMOS A IR A LA SEGUNDA ACTIVITY
                }else{
                    Toast.makeText(baseContext,"No está verificado el correo", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(baseContext,"Error de correo y/o contraseña",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}