package com.example.mazdaservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jaeger.library.StatusBarUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_referral.*

class Referral : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_referral)
        StatusBarUtil.setTransparent(this)
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val database = FirebaseDatabase.getInstance().reference.child("Clients")
        val currentUserPoints = FirebaseDatabase.getInstance().reference.child("Clients").child(user!!.uid).child("pointsAmount")

        continue_button.setOnClickListener {
            val currentReferralCode = code_enter.text.toString()
            var value: Int
            var flag = 1
            if (currentReferralCode != "") {
                database.addListenerForSingleValueEvent(object: ValueEventListener {
                    override fun onDataChange(users: DataSnapshot) {
                        for (child in users.children) {
                            val email = child.child("email").value.toString()
                            if (currentReferralCode == email){
                                flag = 0
                                val referralUserID = child.key
                                val currentReferralPoints =FirebaseDatabase.getInstance().reference.child("Clients").child(referralUserID.toString()).child("pointsAmount")
                                currentReferralPoints.addListenerForSingleValueEvent(object : ValueEventListener{
                                    override fun onDataChange(points: DataSnapshot) {
                                        val currentReferralPointsValue = points.value.toString()
                                        value = currentReferralPointsValue.toInt()
                                        value += 100
                                        currentReferralPoints.setValue(value.toString())
                                        currentUserPoints.setValue(30)
                                        Toast.makeText(this@Referral, "На ваш счет зачисленно 100 бонусных баллов!", Toast.LENGTH_SHORT).show()

                                        setIntent(MainActivity::class.java)
                                    }
                                    override fun onCancelled(points: DatabaseError) { }
                                })
                            }
                        }
                        if (flag == 1) {
                            Toast.makeText(this@Referral, "Такого email не существует, попробуйте еще раз", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onCancelled(p0: DatabaseError) { }
                })
            }
            else {
                Toast.makeText(this@Referral, "Введите email", Toast.LENGTH_SHORT).show()
            }
        }
        
        skip_text.setOnClickListener { setIntent(MainActivity::class.java) }
    }

    fun setIntent(toView : Class<*>) {
        val intent = Intent ( this@Referral, toView)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}
