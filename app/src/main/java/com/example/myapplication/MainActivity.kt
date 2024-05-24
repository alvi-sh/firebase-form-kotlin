package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.forgetPassword.setOnClickListener {
            var intent = Intent(this@MainActivity, ForgetPassword::class.java)
            startActivity(intent)
        }

        mainBinding.signupBtn.setOnClickListener {
            var firstName = mainBinding.firstName.text.toString()
            var lastName = mainBinding.lastName.text.toString()
            var address = mainBinding.address.text.toString()
            var email = mainBinding.email.text.toString()
            var password = mainBinding.password.text.toString()
            var confirmPassword = mainBinding.confirmPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "User Created", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, task.exception?.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        mainBinding.loginBtn.setOnClickListener {
            var firstName = mainBinding.firstName.text.toString()
            var lastName = mainBinding.lastName.text.toString()
            var address = mainBinding.address.text.toString()
            var email = mainBinding.email.text.toString()
            var password = mainBinding.password.text.toString()
            var confirmPassword = mainBinding.confirmPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, task.exception?.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}