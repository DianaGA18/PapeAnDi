package com.tesji.papeandi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class ActualizaElimina : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualiza_elimina)

        var db = Firebase.firestore
        firebaseAuth = Firebase.auth

        val btnRecuperar : Button = findViewById(R.id.btnRecuperaae)
        val btnActualizar : Button = findViewById(R.id.btnActualizaae)
        val btnEliminar : Button = findViewById(R.id.btnEliminarae)
        val btnLimpiar : Button = findViewById(R.id.btnLimpiaae)
        val txtIdP : TextView = findViewById(R.id.tvIdProductoae)
        val txtNombreProd : TextView = findViewById(R.id.tvNombreProductoae)
        val txtDescrip : TextView = findViewById(R.id.txtDescProdae)
        val txtPrecios : TextView = findViewById(R.id.tvPrecioPae)

        val Prodid = intent.getStringExtra("ID")
        txtIdP.setText(Prodid)

        btnRecuperar.setOnClickListener() {
            if (txtIdP.text.toString().trim().isEmpty()){
                txtIdP.setError("Ingrese el id")

                return@setOnClickListener
        }else{
            db = FirebaseFirestore.getInstance()
                db.collection("producto").document(txtIdP.text.toString()).get().addOnSuccessListener { documentSnapshot ->
                    val documentId = documentSnapshot.id

                    Toast.makeText(this,"ID del documento: $documentId", Toast.LENGTH_SHORT).show()

                    txtNombreProd.setText(documentSnapshot.get("NombreProducto") as String?)
                    txtDescrip.setText(documentSnapshot.get("DescripProducto") as String?)
                    txtPrecios.setText(documentSnapshot.get("Precio") as String?)

                }
            }
    }
        btnActualizar.setOnClickListener {
            if (txtIdP.text.toString().trim().isEmpty() || txtNombreProd.text.toString().trim().isEmpty() || txtDescrip.text.toString().trim().isEmpty()
                || txtPrecios.text.toString().trim().isEmpty()){
                txtIdP.setError("Ingrese el id")
                txtNombreProd.setError("Ingrese el nombre")
                txtDescrip.setError("Ingrese la descripción")
                txtPrecios.setError("Ingrese el precio")

                return@setOnClickListener
            }else{
                db.collection("producto").document(txtIdP.text.toString()).set(
                    hashMapOf(
                        "NombreProducto" to txtNombreProd.text.toString(),
                        "DescripProducto" to txtDescrip.text.toString(),
                        "Precio" to txtPrecios.text.toString()
                    )
                )
            }
        }
        btnEliminar.setOnClickListener {
                db.collection("producto").document(txtIdP.text.toString()).delete()
        }

        btnLimpiar.setOnClickListener {
            txtIdP.setText(" ")
            txtDescrip.setText(" ")
            txtPrecios.setText(" ")
            txtNombreProd.setText(" ")
        }

}}