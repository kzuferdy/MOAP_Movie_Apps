package com.kzuferdy.moapmovieapps.checkout

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
import com.kzuferdy.moapmovieapps.model.Checkout
import java.lang.NumberFormatException
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter(private  var data: List<Checkout>,
                      private  val listener:(Checkout)-> Unit)
    : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_checkout, parent, false)

        return ViewHolder(inflatedView)
    }


    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: CheckoutAdapter.ViewHolder, position: Int) {
      holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val tvTittle:TextView = view.findViewById(R.id.tv_kursi)
    private val tvHarga:TextView = view.findViewById(R.id.tv_harga)


        fun bindItem(data:Checkout, listener: (Checkout) -> Unit, context: Context){


            val localId = Locale("id", "ID")
            val formatrupiah = NumberFormat.getCurrencyInstance(localId)
            tvHarga.setText(formatrupiah.format(data.harga!!.toDouble()))

            if(data.kursi!!.startsWith("Total")){
                tvTittle.setText(data.kursi)
                tvTittle.setCompoundDrawables(null, null, null, null)

            } else {
                tvTittle.setText("Seat No. "+data.kursi)
            }

            tvTittle.setText(data.kursi)
            tvHarga.setText(data.harga)


            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
