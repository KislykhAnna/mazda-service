package com.example.mazdaservice

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_repair.*
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

@Suppress("NAME_SHADOWING")
class Repair : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repair)

        var count = 0

        val auth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance().reference
        val countOfRepairs = FirebaseDatabase.getInstance().reference.child("Repair records").child(auth.currentUser!!.uid)

        countOfRepairs.child("FutureElement").setValue("1")

        countOfRepairs.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(countOfRepairs: DataSnapshot) {
                count = countOfRepairs.childrenCount.toInt()
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })

        back_button.setOnClickListener {
            countOfRepairs.child("FutureElement").removeValue()
            finish()
        }

        date.setOnClickListener { setDate() }

        time.setOnClickListener { setTime() }

        send_button.setOnClickListener { sendRecord(auth, database, countOfRepairs, count) }
    }

    @SuppressLint("SetTextI18n")
    private fun setDate() {
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val sb = StringBuilder()

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
            if (day < 10) sb.append("0").append(day).append(".")
            else sb.append(day).append(".")
            if (month < 10) sb.append("0").append(month + 1).append(".")
            else sb.append(month + 1).append(".")
            sb.append(year)
            date.setText(sb)
        }, year, month, day)
        dpd.show()
    }

    private fun setTime() {
        val calendar = Calendar.getInstance()
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)

        val sb = StringBuilder()

        val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hours, minutes ->
            if (hours < 10) sb.append("0").append(hours).append(":")
            else sb.append(hours).append(":")
            if (minutes < 10) sb.append("0").append(minutes)
            else sb.append(minutes)
            time.setText(sb)
        }, hours, minutes, true)
        tpd.show()
    }

    private fun sendRecord(auth: FirebaseAuth, database : DatabaseReference, countOfRepairs : DatabaseReference, count : Int) {
        val repair = RepairRecords(auth.currentUser!!.uid, "nothing", topic.text.toString(), model.text.toString(), comment.text.toString(), date.text.toString(), time.text.toString())

        database.child("Repair records").child(auth.currentUser!!.uid).child(count.toString()).setValue(repair).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this, "Запись прошла успешно", Toast.LENGTH_SHORT).show()
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Попробуйте позже", Toast.LENGTH_SHORT).show()
            }
        }

        countOfRepairs.child("FutureElement").removeValue()
    }
}