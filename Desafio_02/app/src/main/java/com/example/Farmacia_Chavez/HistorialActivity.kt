package com.example.Farmacia_Chavez

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.desafio_02_dsm.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HistorialActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var pedidosList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        listView = findViewById(R.id.listViewHistorial)
        pedidosList = arrayListOf()

        val ref = FirebaseDatabase.getInstance().getReference("pedidos")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pedidosList.clear()
                for (pedidoSnapshot in snapshot.children) {
                    val nombre = pedidoSnapshot.child("nombre").getValue(String::class.java)
                    val precio = pedidoSnapshot.child("precio").getValue(Double::class.java)
                    pedidosList.add("$nombre - \$${precio}")
                }
                val adapter = ArrayAdapter(this@HistorialActivity, android.R.layout.simple_list_item_1, pedidosList)
                listView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HistorialActivity, "Error al cargar el historial", Toast.LENGTH_SHORT).show()
            }
        })
    }
}