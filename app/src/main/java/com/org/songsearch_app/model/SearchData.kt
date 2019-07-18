package com.org.songapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class SearchData {
    @SerializedName("resultCount")
    @Expose
    private var resultCount: Int? = null
    @SerializedName("results")
    @Expose
    private var results: List<Result>? = null

    fun getResultCount(): Int? {
        return resultCount
    }

    fun setResultCount(resultCount: Int?) {
        this.resultCount = resultCount
    }

    fun getResults(): List<Result>? {
        return results
    }

    fun setResults(results: List<Result>) {
        this.results = results
    }

}