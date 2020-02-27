package com.example.mazdaservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_repair.back_button
import kotlinx.android.synthetic.main.activity_settings.*


class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        back_button.setOnClickListener {
            finish()
        }

        logout_button.setOnClickListener {
            val fAuth = FirebaseAuth.getInstance()
            fAuth.signOut()
            intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
    }
}
