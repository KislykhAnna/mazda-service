package com.example.mazdaservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jaeger.library.StatusBarUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_repair.*
import kotlinx.android.synthetic.main.activity_repair.back_button

class Profile : AppCompatActivity() {
    private val TAG = "EditProfileActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val database = FirebaseDatabase.getInstance().reference
        database.child("Clients").child(user!!.uid).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(data: DataSnapshot){
                //val user = data.getValue(User::class.java)
                name.setText(data.child("userName").value.toString(), TextView.BufferType.EDITABLE)
                mobile.setText(data.child("mobileNumber").value.toString(), TextView.BufferType.EDITABLE)
                email.setText(data.child("email").value.toString(), TextView.BufferType.EDITABLE)
                password.setText(data.child("password").value.toString(), TextView.BufferType.EDITABLE)
                score.setText(data.child("pointsAmount").value.toString(), TextView.BufferType.EDITABLE)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "onCancelled: ", error.toException())
            }
        })

        back_button.setOnClickListener {
            finish()
        }

        save_button.setOnClickListener {
            //Добавить сохранение
            finish()
        }
    }
}
