package com.example.caarvamusicplayer2o

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.caarvamusicplayer2o.databinding.ActivityLogin2Binding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLogin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener{
            val email = binding.emailEdittext.text.toString()
            val password = binding.PassWordEdittext.text.toString()


            if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),email)){
                binding.emailEdittext.setError("Invalid Email")
                return@setOnClickListener
            }
            if(password.length < 6){
                binding.PassWordEdittext.setError("Length Should Be 6 Char")
                return@setOnClickListener
            }

            loginWithFirebase(email,password)
        }

        binding.gotoSignupBtn.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }
    }
      fun loginWithFirebase(email : String,password : String){
         setInProgress(true)
         FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                setInProgress(false)
                startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                finish()
                Toast.makeText(applicationContext, "Login SuccessFully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                setInProgress(false)
                Toast.makeText(applicationContext, "Failed To Login", Toast.LENGTH_SHORT).show()
            }
      }

    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().currentUser?.apply {
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
            finish()
        }
    }

        fun setInProgress(inProgress: Boolean){
            if(inProgress){
                binding.loginBtn.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.loginBtn.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }





}