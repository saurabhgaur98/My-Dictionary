package com.example.mydictionary

import com.google.gson.annotations.SerializedName

data class MeaningModel(
    @SerializedName("word"       ) var word       : String?              = null,
    @SerializedName("phonetic"   ) var phonetic   : String?              = null,
    @SerializedName("meanings"   ) var meanings   : ArrayList<Meanings>  = arrayListOf()

)

data class Meanings (

    @SerializedName("partOfSpeech" ) var partOfSpeech : String?                = null,
    @SerializedName("definitions"  ) var definitions  : ArrayList<Definitions> = arrayListOf(),
    @SerializedName("synonyms"     ) var synonyms     : ArrayList<String>      = arrayListOf(),
    @SerializedName("antonyms"     ) var antonyms     : ArrayList<String>      = arrayListOf()

)

data class Definitions (

    @SerializedName("definition" ) var definition : String?                     = null

)
