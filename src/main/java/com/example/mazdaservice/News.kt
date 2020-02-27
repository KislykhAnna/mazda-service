package com.example.mazdaservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mazdaservice.NewsView.NewsAdapter
import com.example.mazdaservice.NewsView.NewsElement
import kotlinx.android.synthetic.main.activity_repair.*

class News : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        back_button.setOnClickListener {
            finish()
        }

        val newsAdapter = NewsAdapter(news)

        val newsListView = findViewById<RecyclerView>(R.id.NewsView)
        newsListView.layoutManager = LinearLayoutManager(this)
        newsListView.itemAnimator = DefaultItemAnimator()

        newsListView.adapter = newsAdapter
    }

    // Для примера сделал массив с новостями
    private val news : Array<NewsElement>
        get() =
            arrayOf(NewsElement(R.drawable.news1),
                NewsElement(R.drawable.news2),
                NewsElement(R.drawable.news3),
                NewsElement(R.drawable.news4)
            )
}
