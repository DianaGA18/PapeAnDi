package com.tesji.papeandi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.auth

class CrearCuenta : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        val txtNombreU: TextView = findViewById(R.id.tvNombre)
        val txtEmailNuevo : TextView = findViewById(R.id.txtCorreoc)
        val txtContras : TextView = findViewById(R.id.tvContrasenc)
        val txtConfirmacont : TextView = findViewById(R.id.tvConfirmaContra)
        val btnCrearC : Button = findViewById(R.id.btnCrear)
        firebaseAuth= Firebase.auth

        btnCrearC.setOnClickListener(){
            var pass1 = txtContras.text.toString()
            var pass2 = txtConfirmacont.text.toString()

            if (pass1.equals(pass2)){
                crearCuenta(txtEmailNuevo.text.toString(), txtContras.text.toString())

            }else{
                Toast.makeText(baseContext,"No coinciden las contraseñas",Toast.LENGTH_SHORT).show()
                txtContras.requestFocus()
            }
        }

    }
    private fun crearCuenta(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    verificarEmail()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                } else{
                    Toast.makeText(baseContext, "Error al crear la cuenta",Toast.LENGTH_SHORT).show()

                }
            }
    }
    private fun verificarEmail(){
        val user = firebaseAuth.currentUser!!
        user.sendEmailVerification().addOnCompleteListener(this){ task ->
            if(task.isSuccessful){
                Toast.makeText(baseContext, "Se envio un código a tu correo", Toast.LENGTH_SHORT).show()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }else{
                Toast.makeText(baseContext, "Error al verificar la cuenta",Toast.LENGTH_SHORT).show()
            }
        }
    }

}