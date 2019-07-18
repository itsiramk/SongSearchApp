package com.org.songsearch_app.utils

import android.util.Log
import com.org.songapp.model.SearchData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

public interface ApiInterface {


    @GET("/search")
    abstract fun getCategoryDetails(@Query("term") aParam: String?): Call<SearchData>

    companion object Factory {
        val BASE_URL = "https://itunes.apple.com"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}