package com.example.Farmacia_Chavez

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.desafio_02_dsm.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        // Botón para ir a la vista de Seleccionar Medicamento
        val btnSeleccionarMedicamento = findViewById<Button>(R.id.btnSeleccionarMedicamento)
        btnSeleccionarMedicamento.setOnClickListener {
            val intent = Intent(this, SeleccionarMedicamentoActivity::class.java)
            startActivity(intent)
        }

        // Botón para ir a la vista de Ver Orden
        val btnVerOrden = findViewById<Button>(R.id.btnVerOrden)
        btnVerOrden.setOnClickListener {
            val intent = Intent(this, OrdenActivity::class.java)
            startActivity(intent)
        }

        // Botón para ir a la vista de Historial de Compras
        val btnHistorialCompras = findViewById<Button>(R.id.btnHistorialCompras)
        btnHistorialCompras.setOnClickListener {
            val intent = Intent(this, HistorialActivity::class.java)
            startActivity(intent)
        }
    }
}