package com.example.mydictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydictionary.databinding.MeaningRecyclerRowBinding

class MeaningAdaptor(private var meaningList: List<Meanings>) :
    RecyclerView.Adapter<MeaningAdaptor.MeaningViewHolder>() {

    class MeaningViewHolder( private val binding: MeaningRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(meanings: Meanings){
            binding.partOfSpeechTextview.text = meanings.partOfSpeech
            binding.definitionTextview.text = meanings.definitions.joinToString("\n\n") {
                val currentIndex = meanings.definitions.indexOf(it)
                (currentIndex+1).toString()+"."+it.definition.toString()
            }

            if (meanings.synonyms.isEmpty()){
                binding.synonymsTitleTextview.visibility = View.GONE
                binding.synonymsTextview.visibility = View.GONE
            }else{
                binding.synonymsTitleTextview.visibility = View.VISIBLE
                binding.synonymsTextview.visibility = View.VISIBLE
                binding.synonymsTextview.text = meanings.synonyms.joinToString(", ")
            }

            if (meanings.antonyms.isEmpty()){
                binding.antonymsTitleTextview.visibility = View.GONE
                binding.antonymsTextview.visibility = View.GONE
            }else{
                binding.antonymsTitleTextview.visibility = View.VISIBLE
                binding.antonymsTextview.visibility = View.VISIBLE
                binding.antonymsTextview.text = meanings.antonyms.joinToString(", ")
            }
        }
    }

    fun updateNewData(newMeaningList: List<Meanings>){
        meaningList = newMeaningList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding = MeaningRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MeaningViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meaningList.size
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }
}