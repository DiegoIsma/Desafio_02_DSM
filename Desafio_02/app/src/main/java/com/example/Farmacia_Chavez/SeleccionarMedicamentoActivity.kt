package com.example.Farmacia_Chavez

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.desafio_02_dsm.R
import java.io.Serializable

data class Medicamento(val nombre: String, val precio: Double) : Serializable
class SeleccionarMedicamentoActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var medicamentosList: ArrayList<Medicamento>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_medicamento)

        listView = findViewById(R.id.listViewMedicamentos)
        medicamentosList = arrayListOf(
            Medicamento("Paracetamol", 5.0),
            Medicamento("Ibuprofeno", 10.0),
            Medicamento("acetaminofeno", 6.0),
        Medicamento("Amoxicilina", 4.0),
        Medicamento("Loratadina", 4.0),
        Medicamento("Omeprazol ", 6.0),
        Medicamento("Metformina", 10.0),
        Medicamento("Enalapril", 8.0),
        Medicamento("Diclofenaco", 5.0),
        Medicamento("Insulina", 25.0),
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, medicamentosList.map { "${it.nombre} - \$${it.precio}" })
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val medicamentoSeleccionado = medicamentosList[position]
            val intent = Intent(this, OrdenActivity::class.java)
            intent.putExtra("medicamento", medicamentoSeleccionado)
            startActivity(intent)
        }
    }
}




