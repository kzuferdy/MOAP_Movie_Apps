package com.kzuferdy.moapmovieapps.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kzuferdy.moapmovieapps.R
import com.kzuferdy.moapmovieapps.model.Film

class NowPlayingAdapter(private  var data: List<Film>,
                        private  val listener:(Film)-> Unit)
    : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_now_playing, parent, false)

        return ViewHolder(inflatedView)
    }


    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: NowPlayingAdapter.ViewHolder, position: Int) {
      holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val tvTittle:TextView = view.findViewById(R.id.tv_kursi)
    private val tvGenre:TextView = view.findViewById(R.id.tv_genre)
    private val tvRate:TextView = view.findViewById(R.id.tv_rate)

    private val tvImage:ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data:Film, listener: (Film) -> Unit, context: Context){
            tvTittle.setText(data.judul)
            tvGenre.setText(data.genre)
            tvRate.setText(data.rating)

            Glide.with(context)
                .load(data.poster)
                .into(tvImage)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
