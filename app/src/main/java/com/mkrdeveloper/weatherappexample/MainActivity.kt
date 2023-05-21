package com.mkrdeveloper.weatherappexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mkrdeveloper.weatherappexample.databinding.ActivityMainBinding
import com.mkrdeveloper.weatherappexample.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
           val response = try {
                RetrofitInstance.api.getCurrentWeather("new york","metric", applicationContext.getString(R.string.api_key))
            }catch (e: IOException){
               Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_SHORT).show()
               return@launch
            }catch (e: HttpException){
               Toast.makeText(applicationContext, "http error ${e.message}", Toast.LENGTH_SHORT)
                   .show()
               return@launch
            }

            if (response.isSuccessful && response.body()!= null){
                withContext(Dispatchers.Main){
                    binding.tvTemp.text = "tem: ${response.body()!!.main.temp}"
                }
            }
        }
    }
}