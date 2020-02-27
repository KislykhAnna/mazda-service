package com.example.mazdaservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_log_in.*

class LogIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        StatusBarUtil.setTransparent(this)

        login_button.setOnClickListener {
            intent = Intent(this@LogIn, Welcome::class.java)
            startActivity(intent)
        }

        register_button.setOnClickListener {
            intent = Intent(this@LogIn, Register::class.java)
            startActivity(intent)
        }
    }
}
