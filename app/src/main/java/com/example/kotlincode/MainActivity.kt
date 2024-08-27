package com.example.kotlincode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincode.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var activityMainBinding: ActivityMainBinding

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

        override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView= findViewById(R.id.recyclerView)


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getProductData()


        retrofitData.enqueue(object : Callback<recipe?> {

            override fun onResponse(call: Call<recipe?>, response: Response<recipe?>) {
                // what you want to perform when api call is a success
                var responseBody = response.body()
                val productArray = responseBody?.recipes!!
               val layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("PRODUCTS", productArray.get(10).reviewCount.toString())

                recyclerView.setLayoutManager(layoutManager)

                myAdapter = MyAdapter(this@MainActivity, productArray)
            recyclerView.adapter = myAdapter

            }

            override fun onFailure(call: Call<recipe?>, t: Throwable) {
                // if api call is a failure
                Log.d("TAG", "onFailure: " + t.message)
            }
        })


    }

}

