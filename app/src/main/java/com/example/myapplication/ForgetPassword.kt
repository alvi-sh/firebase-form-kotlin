package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityForgetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgetPassword : AppCompatActivity() {

    lateinit var forgetPasswordBinding: ActivityForgetPasswordBinding
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        forgetPasswordBinding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(forgetPasswordBinding.root)

        forgetPasswordBinding.submitBtn.setOnClickListener {
            var email = forgetPasswordBinding.forgetPasswordEmail.text.toString()
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Link has been sent to $email", Toast.LENGTH_LONG).show()
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