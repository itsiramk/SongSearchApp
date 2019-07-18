package com.org.songsearch_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.org.songapp.model.Result
import com.org.songsearch_app.utils.AppConstant
import com.org.songsearch_app.view.SongDetailActivity
import java.util.*






class SearchAdapter(private val context: Context, private val dataList: ArrayList<Result>) : BaseAdapter() {

    var artistName: String = ""
    var trackName: String = ""
    var trackPrice: String = ""
    var country: String = ""
    var genre: String = ""
    var img_song: String = ""
    var is_streamable: String = ""
    var currency: String = ""
    var release_date: String = ""
    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(com.org.songsearch_app.R.layout.activity_song_list_item, null, true)

            holder.txt_trackName = convertView.findViewById(com.org.songsearch_app.R.id.txt_trackName) as TextView
            holder.txt_artistName = convertView.findViewById(com.org.songsearch_app.R.id.txt_artistName) as TextView
            holder.img_song = convertView.findViewById(com.org.songsearch_app.R.id.img_song) as ImageView
            convertView.tag = holder

        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.txt_trackName!!.setText(dataList[position].getTrackName())
        holder.txt_artistName!!.setText(dataList[position].getArtistName())

        Glide.with(context).load(dataList[position].getArtworkUrl100()).into(holder.img_song!!)

        artistName = dataList[position].getArtistName().toString()
        trackName = dataList[position].getTrackName().toString()
        trackPrice = dataList[position].getTrackPrice().toString() + " $"
        country = dataList[position].getCountry().toString()
        genre = dataList[position].getPrimaryGenreName().toString()
        img_song = dataList[position].getArtworkUrl100().toString()
        is_streamable = dataList[position].getIsStreamable().toString()
        currency = dataList[position].getCurrency().toString()
        release_date = dataList[position].getReleaseDate().toString()

        convertView!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(context, SongDetailActivity::class.java)
                intent.putExtra(AppConstant.ARTIST_NAME, artistName)
                intent.putExtra(AppConstant.TRACK_NAME, trackName)
                intent.putExtra(AppConstant.TRACK_PRICE, trackPrice)
                intent.putExtra(AppConstant.TRACK_GENRE, genre)
                intent.putExtra(AppConstant.COUNTRY, country)
                intent.putExtra(AppConstant.SONG_URL, img_song)
                intent.putExtra(AppConstant.REL_DATE, release_date)
                intent.putExtra(AppConstant.IS_STREAMABLE, is_streamable)
                intent.putExtra(AppConstant.CURRENCY, currency)
                context.startActivity(intent)
            }
        })
        return convertView
    }

    private inner class ViewHolder {

        var txt_trackName: TextView? = null
        var txt_artistName: TextView? = null
        var img_song: ImageView? = null

    }
}