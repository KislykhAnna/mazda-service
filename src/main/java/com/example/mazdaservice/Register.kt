package com.example.mazdaservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.jaeger.library.StatusBarUtil
import android.util.Log
import android.view.View
import android.widget.Toast

import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_register.*


class Register : AppCompatActivity() {
    private val TAG = "RegisterActivity"
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        StatusBarUtil.setTransparent(this)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        continue_button.setOnClickListener {
            val email = email_enter.text.toString()
            val pass = password_enterf.text.toString()
            if (email != "" && pass!= "" && password_repeat_enter.text.toString() != "" && name_enter.text.toString() != "" && mobile_enter.text.toString() != "")
            {
                if (pass == password_repeat_enter.text.toString()){
                    auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val user = User ( name_enter.text.toString(),mobile_enter.text.toString(), email, pass,0)
                                database.child("Clients").child(it.result!!.user!!.uid).setValue(user).addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        intent = Intent(this, Referral::class.java)
                                        startActivity(intent)
                                    }else {
                                        Log.e(TAG, "Ошибка содания профиля", it.exception)
                                        Toast.makeText(this, "Попробуйте позже", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }else {
                                Log.e(TAG, "Ошибка содания пользователя", it.exception)
                                Toast.makeText(this, "Попробуйте позже", Toast.LENGTH_SHORT).show()
                            }
                        }
                }else {
                    Toast.makeText(this, "Пароль должен быть длиннее 6 символов, а также пароли должны совпадать.", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this, "Заполните все поля, пожалуйста.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
