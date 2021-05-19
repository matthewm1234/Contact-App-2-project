package com.example.contactlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.contactlist.databinding.ActivityLoginBinding
import com.example.contactlist.databinding.ActivitySignInBinding
import com.example.contactlist.room.db.UserRepository
import com.example.contactlist.room.db.entities.UserEntity

class LoginActivity : AppCompatActivity() {
    var binding: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        setContentView(binding?.root)
        validateSignUpDetails(binding!!)
    }

    private fun validateSignUpDetails(binding: ActivityLoginBinding) {
        val button = binding.loginButton
        button.setOnClickListener {
            if (binding.userEmail.text.isNullOrEmpty() || binding.password.text.isNullOrEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(binding.userEmail.text.toString()).matches())) {
                Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show()

            } else {
                verifyUser(binding)
            }
        }

    }

    private fun verifyUser(binding: ActivityLoginBinding) {
        val repository = UserRepository(this)
        val email = binding.userEmail.text.toString()
        val password = binding.password.text.toString()
        val user = repository.verifyUser(email, password)
        if (user != null) {
            val intent = Intent(this, CategoryActivity::class.java)
            finish()
            startActivity(intent)

        }
        else {
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
        }
    }
}