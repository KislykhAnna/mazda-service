package com.example.mazdaservice.HistoryView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mazdaservice.R

class HistoryHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    private lateinit var historyItemClickListener : ItemClickListener

    var type : TextView = itemView.findViewById(R.id.type)
    var topic : TextView = itemView.findViewById(R.id.topic)
    var date : TextView = itemView.findViewById(R.id.date)
    var status : TextView = itemView.findViewById(R.id.status)

    override fun onClick(v : View) {
        this.historyItemClickListener.onItemClick(v, layoutPosition)
    }

    fun setItemClickListener(ic : ItemClickListener) {
        this.historyItemClickListener = ic
    }

    interface ItemClickListener {
        fun onItemClick(v : View, pos : Int)
    }
}