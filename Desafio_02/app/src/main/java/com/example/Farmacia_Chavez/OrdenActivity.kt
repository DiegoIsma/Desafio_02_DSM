package com.example.Farmacia_Chavez

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.desafio_02_dsm.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class OrdenActivity : AppCompatActivity() {

    private lateinit var medicamento: Medicamento
    private lateinit var btnVolverMain: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orden)

        val tvMedicamento = findViewById<TextView>(R.id.medicamentoTextView)
        val tvPrecio = findViewById<TextView>(R.id.precioTextView)
        val btnConfirmar = findViewById<Button>(R.id.confirmarButton)
        btnVolverMain = findViewById(R.id.volverMainButton)


        medicamento = intent.getSerializableExtra("medicamento") as Medicamento

        tvMedicamento.text = "Medicamento: ${medicamento.nombre}"
        tvPrecio.text = "Precio: \$${medicamento.precio}"


        btnConfirmar.setOnClickListener {
            guardarOrdenEnFirebase(medicamento)

        }


        btnVolverMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun guardarOrdenEnFirebase(medicamento: Medicamento) {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("pedidos")
        val pedidoId = ref.push().key

        val datosPedido = mapOf(
            "nombre" to medicamento.nombre,
            "precio" to medicamento.precio
        )

        ref.child(pedidoId!!).setValue(datosPedido).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Pedido guardado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al guardar el pedido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}