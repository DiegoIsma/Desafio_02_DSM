package com.example.desafio_02_dsm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.whenStarted
import com.example.Farmacia_Chavez.MainActivity
import com.google.firebase.auth.FirebaseAuth

class Login2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var btnLogin: Button
    private lateinit var textViewRegister: TextView

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        auth = FirebaseAuth.getInstance()
        btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = findViewById<EditText>(R.id.editTexEmailAddress).text.toString()
            val password = findViewById<EditText>(R.id.editTexPassword).text.toString()
            this.login(email, password)
        }
        textViewRegister = findViewById<TextView>(R.id.textViewRegister)
        textViewRegister.setOnClickListener {
            this.goToRegister()
        }
    }

    private fun login(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun goToRegister(){
        val intent = Intent( this, Login::class.java)
        startActivity(intent)
    }
}