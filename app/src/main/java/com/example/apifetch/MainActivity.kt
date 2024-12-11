package com.example.apifetch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fetchNews()
    }

    private fun fetchNews() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://news-api14.p.rapidapi.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AllnewsService::class.java)
        val call = service.getTopNewsHeadlines(
            "122b98eeebmsh38d3a9c85f0a22fp1de28ejsncf155fd24963",
            "news-api14.p.rapidapi.com"
        )

        call.enqueue(object : Callback<Allnews> {
            override fun onResponse(call: Call<Allnews>, response: Response<Allnews>) {
                val responseBody = response.body()
                val dataList = responseBody?.data
                val collectedData = StringBuilder()

                if (dataList != null) {
                    for (article in dataList) {
                        collectedData.append(article.title).append(" ")
                    }
                    val newsTitle = findViewById<TextView>(R.id.newstitle)
                    newsTitle.text = collectedData.toString()
                } else {
                    Log.e("Error", "Response body is null")
                }
            }

            override fun onFailure(call: Call<Allnews>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }
}
