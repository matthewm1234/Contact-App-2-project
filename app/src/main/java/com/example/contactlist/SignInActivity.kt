package com.example.contactlist
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.contactlist.databinding.ActivitySignInBinding
import com.example.contactlist.room.db.UserRepository
import com.example.contactlist.room.db.entities.UserEntity

class SignInActivity : AppCompatActivity() {
    private var binding: ActivitySignInBinding ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(LayoutInflater.from(this))
        setContentView(binding?.root)
        binding?.login?.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        validateSignUpDetails(binding!!)
    }

    private fun validateSignUpDetails(binding: ActivitySignInBinding) {
        val button = binding.signInButton

         binding.checkbox.setOnCheckedChangeListener{ _, isChecked ->
             if(isChecked)
                 button.isEnabled = true
         }

        button.setOnClickListener{
            if (binding.userName.text.isNullOrEmpty() || binding.userEmail.text.isNullOrEmpty() || binding.password.text.isNullOrEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
            else if (binding.userName.text!!.length < 5){
                Toast.makeText(this, "Username can not be less than 5 characters", Toast.LENGTH_SHORT).show()
            }
            else if (binding.password.text!!.length < 5){
                Toast.makeText(this, "Password can not be less than 5 characters", Toast.LENGTH_SHORT).show()

            }
            else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(binding.userEmail.text.toString()).matches())){
                Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show()

            }
            else{
                insertToDb(binding)
            }
        }

    }

    private fun insertToDb(binding: ActivitySignInBinding) {
        val repository = UserRepository(this)
        val ifRegistered = repository.checkIfAlreadyRegistered(binding.userEmail.text.toString())
        if (ifRegistered != null){
            Toast.makeText(this, "Email Already Registered", Toast.LENGTH_SHORT).show()
        }
        else{
         val userName: String = binding.userName.text.toString()
         val email = binding.userEmail.text.toString()
         val password = binding.password.text.toString()
         val userEntity = UserEntity(name = userName, email = email,password = password)
         repository.insertUser(userEntity)
         val intent = Intent(this,LoginActivity::class.java)
         startActivity(intent)
        }
    }

}


