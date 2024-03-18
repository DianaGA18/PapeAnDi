package com.tesji.papeandi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class InsertarProductos : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_productos)

        val btnGuardar: Button = findViewById(R.id.btnGuardar)
        val txtIdp : TextView = findViewById(R.id.tvIdProducto)
        val txtNombre : TextView = findViewById(R.id.tvNombreProducto)
        val txtDescripcion : TextView = findViewById(R.id.txtDescProd)
        val txtPrecio : TextView = findViewById(R.id.tvPrecioP)

        btnGuardar.setOnClickListener (){
            if (txtIdp.text.toString().trim().isEmpty() || txtNombre.text.toString().trim().isEmpty() || txtDescripcion.text.toString().trim().isEmpty()
                || txtPrecio.text.toString().trim().isEmpty()){
                txtIdp.setError("Ingrese el id")
                txtNombre.setError("Ingrese el nombre")
                txtDescripcion.setError("Ingrese la descripción")
                txtPrecio.setError("Ingrese el precio")

                return@setOnClickListener
            }else{
                db.collection("producto").document(txtIdp.text.toString()).set(
                    hashMapOf(
                    "ID" to txtIdp.text.toString(),
                        "NombreProducto" to txtNombre.text.toString(),
                        "DescripProducto" to txtDescripcion.text.toString(),
                        "Precio" to txtPrecio.text.toString(),
                )).addOnSuccessListener {
                    Toast.makeText(this, "Guardado con Éxito", Toast.LENGTH_SHORT).show()
                    txtIdp.setText("")
                    txtNombre.setText("")
                    txtDescripcion.setText("")
                    txtPrecio.setText("")
                }
                    .addOnFailureListener{
                        Toast.makeText(this,"Hubo un error",Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}