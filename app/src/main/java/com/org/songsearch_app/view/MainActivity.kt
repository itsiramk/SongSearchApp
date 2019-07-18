package com.org.songsearch_app.view

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.org.songapp.model.Result
import com.org.songapp.model.SearchData
import com.org.songsearch_app.R
import com.org.songsearch_app.adapter.SearchAdapter
import com.org.songsearch_app.utils.ApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), android.support.v7.widget.SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        displayProgressDialog()
        fetchData()
        txt_searchHint!!.visibility = View.GONE
        listView!!.visibility = View.VISIBLE
        return false;
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return false
    }

    private var listView: ListView? = null
    private var txt_searchHint: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.list_view)
        txt_searchHint = findViewById(R.id.txt_search_hint)
        listView!!.setTextFilterEnabled(true)
        searchView!!.setOnQueryTextListener(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun fetchData() {

        val apiService = ApiInterface.create()
        val call = apiService.getCategoryDetails(searchView.query.toString().trim())
        Log.d("REQUEST>>>>", call.request().url().toString() + "")
        call.enqueue(object : Callback<SearchData> {
            override fun onResponse(call: Call<SearchData>, response: retrofit2.Response<SearchData>?) {
                if (response != null) {
                    if (pDialog != null && pDialog!!.isShowing()) {
                        pDialog.dismiss()
                    }
                    var list: ArrayList<Result> = (response.body()!!.getResults() as ArrayList<Result>?)!!
                    Log.d("MainActivity", "" + list.size)
                    var artistName: String = ""
                    var trackName: String = ""
                    var trackNumber: String = ""
                    var trackPrice: String = ""
                    var releaseDate: String = ""
                    var img_song: String = ""
                    for (item: Result in list.iterator()) {
                        artistName = item.getArtistName().toString()
                        trackName = item.getTrackName().toString()
                        trackNumber = item.getTrackNumber().toString()
                        trackPrice = item.getTrackPrice().toString()
                        releaseDate = item.getReleaseDate().toString()
                        img_song = item.getArtistViewUrl().toString()

                    }

                    val myListAdapter = SearchAdapter(this@MainActivity, list)
                    listView!!.adapter = myListAdapter
                    myListAdapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<SearchData>, t: Throwable) {
                //                Log.e(TAG, t.toString());
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.dismiss()
                }
            }
        })
    }

    lateinit var pDialog: ProgressDialog
    fun displayProgressDialog() {

        pDialog = ProgressDialog(this@MainActivity)
        pDialog!!.setMessage("Loading Data..")
        pDialog!!.setCancelable(false)
        pDialog!!.isIndeterminate = false
        pDialog!!.show()
    }
}