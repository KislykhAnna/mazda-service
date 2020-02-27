package com.example.mazdaservice

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mazdaservice.HistoryView.HistoryAdapter
import com.example.mazdaservice.HistoryView.HistoryElement
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_repair.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class History : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var history = mutableListOf<HistoryElement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("Repair records").child(auth.currentUser!!.uid)

        database.child("FutureElement").removeValue()

        database.addValueEventListener(object: ValueEventListener{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(p0: DataSnapshot) {
                for (snapshot : DataSnapshot in p0.children) {
                    snapshot.key.toString()

                    val repairTopic = snapshot.child("repairTopic").value.toString()
                    val type = "Ремонт"
                    val date = snapshot.child("date").value.toString()

                    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                    val currentDate : LocalDate = LocalDate.parse(LocalDate.now().format(formatter), formatter)

                    val itemDate : LocalDate
                    itemDate = if (date != "null") {
                        LocalDate.parse(date, formatter)
                    } else {
                        LocalDate.parse("01.01.2011", formatter)
                    }

                    val status = when {
                        itemDate > currentDate -> "Запланирован"
                        itemDate == currentDate -> "Выполняется"
                        else -> "Выполнен"
                    }

                    history.add(HistoryElement(type, repairTopic, date, status))
                    SetParam()
                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })

        back_button.setOnClickListener { finish() }
    }

    fun SetParam() {
        val historyAdapter = HistoryAdapter(history)

        val historyListView = findViewById<RecyclerView>(R.id.HistoryView)
        historyListView.layoutManager = LinearLayoutManager(this)
        historyListView.itemAnimator = DefaultItemAnimator()

        historyListView.adapter = historyAdapter
    }

}
