package com.org.songsearch_app.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.org.songsearch_app.utils.AppConstant
import java.text.ParseException
import java.text.SimpleDateFormat


class SongDetailActivity : AppCompatActivity() {

    private var txt_trackName: TextView? = null
    private var txt_artistName: TextView? = null
    private var txt_rel_date: TextView? = null
    private var txt_genre: TextView? = null
    private var txt_country: TextView? = null
    private var txt_trackPrice: TextView? = null
    private var txt_currency: TextView? = null
    private var img_song: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.org.songsearch_app.R.layout.activity_song_details)
        txt_trackName = findViewById(com.org.songsearch_app.R.id.txt_trackName)
        txt_artistName = findViewById(com.org.songsearch_app.R.id.txt_artistName)
        txt_rel_date = findViewById(com.org.songsearch_app.R.id.txt_rel_date)
        txt_genre = findViewById(com.org.songsearch_app.R.id.txt_genre)
        txt_country = findViewById(com.org.songsearch_app.R.id.txt_country)
        txt_currency = findViewById(com.org.songsearch_app.R.id.txt_currency)
        txt_trackPrice = findViewById(com.org.songsearch_app.R.id.txt_trackPrice)
        img_song = findViewById(com.org.songsearch_app.R.id.img_song)
        val intent = getIntent()
        /*val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)

        val inputText = intent.getStringExtra(AppConstant.REL_DATE)
        val date = inputFormat.parse(inputText)
        val outputText = outputFormat.format(date)*/

        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        try {
            val date1 = df.parse(intent.getStringExtra(AppConstant.REL_DATE))
            val outputFormatter1 = SimpleDateFormat("dd-MMM-yyyy")
            val output1 = outputFormatter1.format(date1) //
            Log.d("Date>>>>",output1)

        } catch (e: ParseException) {
            e.printStackTrace()
        }



        txt_trackName!!.setText(intent.getStringExtra(AppConstant.TRACK_NAME))
        txt_artistName!!.setText(intent.getStringExtra(AppConstant.ARTIST_NAME))
        txt_rel_date!!.setText(intent.getStringExtra(AppConstant.REL_DATE))
        txt_genre!!.setText(intent.getStringExtra(AppConstant.TRACK_GENRE))
        txt_country!!.setText(intent.getStringExtra(AppConstant.COUNTRY))
        txt_trackPrice!!.setText(intent.getStringExtra(AppConstant.TRACK_PRICE))
        txt_currency!!.setText(intent.getStringExtra(AppConstant.CURRENCY))
        Glide.with(this).load(intent.getStringExtra(AppConstant.SONG_URL)).into(img_song!!)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}