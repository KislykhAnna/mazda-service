package com.example.mazdaservice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.jaeger.library.StatusBarUtil
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_welcome.*


class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        StatusBarUtil.setTransparent(this)

        var auth = FirebaseAuth.getInstance()
        login_button.setOnClickListener {
            val login: String = mobile_enter.text.toString()
            val pass: String = password_enter.text.toString()
            if (validate(login, pass))
            {
                auth.signInWithEmailAndPassword(login,pass).addOnCompleteListener{
                    if (it.isSuccessful)
                    {
                        intent = Intent(this@Welcome, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                        finish()
                    }
                }
            }
            else
            {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validate(email: String, password: String) =
        email.isNotEmpty() && password.isNotEmpty()
}
