package com.example.mazdaservice.HistoryView

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mazdaservice.R

class HistoryAdapter(private var history: MutableList<HistoryElement>) : RecyclerView.Adapter<HistoryHolder>(){

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryHolder(v)
    }

    override fun getItemCount(): Int = history.size

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val history = history[position]
        holder.type.text = history.type
        holder.topic.text = history.topic
        holder.date.text = history.date
        holder.status.text = history.status

        when (holder.status.text) {
            "Выполняется" -> {
                holder.status.setBackgroundResource(R.color.colorYellowInProcess)
            }
            "Выполнен" -> {
                holder.status.setBackgroundResource(R.color.colorGreenDone)
            }
            "" -> {
                holder.status.setBackgroundResource(Color.TRANSPARENT)
            }
        }

        holder.setItemClickListener(object : HistoryHolder.ItemClickListener{
            override fun onItemClick(v : View, pos : Int){

            }
        })
    }
}