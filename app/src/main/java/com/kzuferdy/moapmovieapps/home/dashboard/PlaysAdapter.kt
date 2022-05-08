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
import com.kzuferdy.moapmovieapps.model.Plays

class PlaysAdapter(private  var data: List<Plays>,
                   private  val listener:(Plays)-> Unit)
    : RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_plays, parent, false)

        return ViewHolder(inflatedView)
    }


    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: PlaysAdapter.ViewHolder, position: Int) {
      holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val tvTittle:TextView = view.findViewById(R.id.tv_kursi)


    private val tvImage:ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data:Plays, listener: (Plays) -> Unit, context: Context){
            tvTittle.setText(data.nama)


            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(tvImage)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
