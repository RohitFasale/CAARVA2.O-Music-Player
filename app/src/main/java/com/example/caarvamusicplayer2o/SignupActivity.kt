package com.example.caarvamusicplayer2o

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.caarvamusicplayer2o.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createAccountBtn.setOnClickListener{
            val email = binding.emailEdittext.text.toString()
            val password = binding.PassWordEdittext.text.toString()
            val confirmPassword = binding.confirmPasswordEdittext.text.toString()

            if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),email)){
                binding.emailEdittext.setError("Invalid Email")
                return@setOnClickListener
            }
            if(password.length < 6){
                binding.PassWordEdittext.setError("Length Should Be 6 Char")
                return@setOnClickListener
            }
            if(!password.equals(confirmPassword)){
                binding.confirmPasswordEdittext.setError("PassWord Not Match")
                return@setOnClickListener
            }

            createAccountWithFirebase(email,password)
        }
        binding.gotoLoginBtn.setOnClickListener {
            finish()
        }
    }

    fun createAccountWithFirebase(email : String,password : String){

            setInProgress(true)
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                    setInProgress(false)
                    Toast.makeText(applicationContext, "Account Created SuccessFully", Toast.LENGTH_SHORT).show()
                    finish()
                }.addOnFailureListener{
                    setInProgress(false)
                    Toast.makeText(applicationContext, "Failed To Create Account", Toast.LENGTH_SHORT).show()
                }
    }



    fun setInProgress(inProgress: Boolean){
        if(inProgress){
            binding.createAccountBtn.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.createAccountBtn.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }
}