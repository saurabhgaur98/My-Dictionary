package com.example.mydictionary

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydictionary.databinding.ActivityDictionaryBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Dictionary : AppCompatActivity() {
    private lateinit var binding: ActivityDictionaryBinding
    private lateinit var apiInterface: RetrofitInterface
    private lateinit var adapter: MeaningAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDictionaryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchButton.setOnClickListener {
            val word = binding.search.text.toString()
            getMeaning(word)
//            binding.search.clearFocus()
            binding.searchButton.visibility = View.GONE
            binding.linear.visibility = View.VISIBLE
        }
        binding.search.setOnClickListener{
            binding.searchButton.visibility = View.VISIBLE
            binding.linear.visibility = View.GONE
        }
//        meaningItem()

        adapter = MeaningAdaptor(emptyList())
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter
    }




//    private fun getResult(word : String){
//        apiInterface = RetrofitInstance.getInstance().create(RetrofitInterface::class.java)
//        val call = apiInterface.getMeaning(word)
//        call.enqueue(object : Callback<ArrayList<MeaningModel>> {
//            override fun onResponse(call: Call<ArrayList<MeaningModel>>, response: Response<ArrayList<MeaningModel>>) {
//                if (response.isSuccessful && response.body() != null) {
//
//                }
//            }
//            override fun onFailure(call: Call<ArrayList<MeaningModel>>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
//    }

    private fun getMeaning(word : String){
        setInProgress(true)
        GlobalScope.launch{
            try{
                val response = RetrofitInstance.dictionaryApi.getMeaning(word)
                if (response.body()==null){
                    throw (Exception())
                }
                runOnUiThread{
                    setInProgress(false)
                    response.body()?.first()?.let {
                        setUI(it)
                    }
                }
            }catch (e :Exception){
                runOnUiThread{
                    setInProgress(false)
                    Toast.makeText(applicationContext,"Somethings went wrong",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


    private fun setUI(response: MeaningModel){
        binding.wordTextview.text = response.word
        binding.phoneticsTextview.text = response.phonetic
        adapter.updateNewData(response.meanings)
    }

    private fun setInProgress(isProgress : Boolean){
        if(isProgress){
            binding.searchButton.visibility = View.GONE
            binding.progressbar.visibility = View.VISIBLE
        }else{
            binding.progressbar.visibility = View.GONE
        }
    }
}