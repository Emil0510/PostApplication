package com.emilabdurahmanli.userapplication

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.emilabdurahmanli.userapplication.adapter.OnItemClickListener
import com.emilabdurahmanli.userapplication.adapter.PostAdapter
import com.emilabdurahmanli.userapplication.databinding.ActivityMainBinding
import com.emilabdurahmanli.userapplication.model.Post
import com.emilabdurahmanli.userapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        getPosts()

    }
    fun getPosts(){
        val call: Call<List<Post?>?>? = RetrofitClient.getRetrofitInstance()?.getApi()?.getPosts()
        call?.enqueue(object : Callback<List<Post?>?> {
            override fun onResponse(call: Call<List<Post?>?>?, response: Response<List<Post?>?>) {
                val postList: List<Post> = response.body() as List<Post>
                binding.recyclerView.adapter = PostAdapter(postList,object : OnItemClickListener {
                    override fun OnItemClick(item: Post) {
                        val intent = Intent(this@MainActivity,PostActivity::class.java)
                        intent.putExtra("Post",item)
                        startActivity(intent)
                    }
                })
            }

            override fun onFailure(call: Call<List<Post?>?>?, t: Throwable?) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }
}