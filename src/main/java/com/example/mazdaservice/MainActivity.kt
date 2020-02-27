package com.example.mazdaservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.setTransparent(this)

        repair_button.setOnClickListener {
            loadPage(Repair::class.java)
        }

        call_button.setOnClickListener {
            loadPage(Call::class.java)
        }

        about_button.setOnClickListener {
            loadPage(About::class.java)
        }

        profile_button.setOnClickListener {
            loadPage(Profile::class.java)
        }

        settings_button.setOnClickListener {
            loadPage(Settings::class.java)
        }

        offers_button.setOnClickListener {
            loadPage(News::class.java)
        }

        offers_button_white.setOnClickListener {
            loadPage(News::class.java)
        }

        history_button.setOnClickListener {
            loadPage(History::class.java)
        }
    }

    private fun loadPage(window : Class<*>) {
        intent = Intent(this, window)
        startActivity(intent)
    }
}
