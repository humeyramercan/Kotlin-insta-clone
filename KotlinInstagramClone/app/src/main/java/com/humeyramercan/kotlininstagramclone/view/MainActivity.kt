package com.humeyramercan.kotlininstagramclone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.humeyramercan.kotlininstagramclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        auth= Firebase.auth
        if(auth.currentUser!=null){
            val intent=Intent(this,FeedActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun signIn(view:View){
        var email=binding.emailTextSignIn.text.toString()
        var password=binding.passwordTextSignIn.text.toString()

        if(email.isNullOrBlank() || password.isNullOrBlank() ){
            Toast.makeText(this,"Please enter email and password.",Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent=Intent(this@MainActivity,FeedActivity::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }
    fun goToSignUpPage(view: View){
        val intent=Intent(this,SignUpActivity::class.java)
        startActivity(intent)
    }
}