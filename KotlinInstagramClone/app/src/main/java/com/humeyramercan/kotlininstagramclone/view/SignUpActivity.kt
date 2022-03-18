package com.humeyramercan.kotlininstagramclone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.humeyramercan.kotlininstagramclone.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var bindig:ActivitySignUpBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig= ActivitySignUpBinding.inflate(layoutInflater)
        val view=bindig.root
        setContentView(view)

        auth=Firebase.auth
    }
    fun signUp(view: View){
        var email=bindig.emailTextSignUp.text.toString()
        var password=bindig.passwordTextSignUp.text.toString()

        if(email.isNullOrBlank() || password.isNullOrBlank()){
            Toast.makeText(this,"You should enter an email and password!",Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                Toast.makeText(this@SignUpActivity,"Welcome to the club!",Toast.LENGTH_LONG).show()
                var intent= Intent(this@SignUpActivity,FeedActivity::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener {
                Toast.makeText(this@SignUpActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }
}