package com.example.retrofitcallapi

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.bumptech.glide.Glide
import com.example.retrofitcallapi.databinding.ActivityMainBinding
import retrofit2.Response
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //insert anh
        val excecutor = Executors.newSingleThreadExecutor()
        val handle = Handler(Looper.getMainLooper())

        var imagee : Bitmap? = null


        //

        val retrofitService = RetrofitInstance.getRetrofitInstance().create(ProductService::class.java)

        val responseLiveData: LiveData<Response<Albums>> =
            liveData {
                val response = retrofitService.getProducts()
                emit(response)
            }

        responseLiveData.observe(this,Observer{
            val productList = it.body()?.listIterator()
            if(productList != null){
                while (productList.hasNext()){
                    val productItem = productList.next()

                    val productTitle = "Product Title: ${productItem.nameProducts}\n"
                    binding.tileTextView.append(productTitle)


                    val URLimage = "${productItem.imageSource}"

                    Glide.with(this)
                        .load("https://laptopmedia.com/wp-content/uploads/2023/09/acer-nitro-v-15-anv15-51-non-fingerprint-with-backlit-black-05.tif-custom-scaled-e1695938821469.jpg")
                        .fitCenter()
                        .into(binding.imageView)

                    binding.tileTextView.append(URLimage)
//                    try {
//
//                        val in = java.net.URL(URLimage).openStream()
//                        imagee = BitmapFactory.decodeStream(in)
//
//                        handle.post{
//                            binding.imageView.setImageBitmap(imagee)
//                            binding.tileTextView.append(URLimage)
//
//                        }
//
//                    }catch (e: java.lang.Exception){
//                        e.printStackTrace()
//                    }








//                    val images = "Product Title: ${productItem.imageSource}\n"
//                    binding.tileTextView.append(images)
                    //lay ra URL anh
                }
            }
        })
    }
}