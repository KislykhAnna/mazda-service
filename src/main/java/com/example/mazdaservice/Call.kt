package com.example.mazdaservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_call.*
import kotlinx.android.synthetic.main.activity_repair.back_button

class Call : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        back_button.setOnClickListener {
            finish()
        }
        var name = ""
        var message = ""
        send_button.setOnClickListener {
            name = topic.text.toString()
            message = text.text.toString()

            if (name != "" && message != "")
            {
                val emailIntent = Intent(Intent.ACTION_SEND)
                emailIntent.type = "text/plain"
                emailIntent.putExtra(
                    Intent.EXTRA_EMAIL,
                    arrayOf("nikcheche710@gmail.com")
                )
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, name)
                emailIntent.putExtra(Intent.EXTRA_TEXT, message)
                startActivity(emailIntent)
            }
            else
            {
                Toast.makeText(this@Call, "Введите тему/текст сообщения!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}