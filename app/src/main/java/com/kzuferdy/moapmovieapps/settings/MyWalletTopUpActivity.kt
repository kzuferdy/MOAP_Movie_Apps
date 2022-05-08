package com.kzuferdy.moapmovieapps.settings

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kzuferdy.moapmovieapps.R
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.*
import java.lang.NullPointerException
import java.lang.NumberFormatException


class MyWalletTopUpActivity : AppCompatActivity() {

    private var status10K : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet_top_up)

        initListener()

    }

    private fun initListener() {
        btn_top_up.setOnClickListener {
            startActivity(Intent(this, MyWalletSuccessActivity::class.java))
        }

        tv_10k.setOnClickListener {
            if (status10K) {
                deselectMoney(tv_10k)
            } else {
                selectMoney(tv_10k)
            }
        }

        et_amount.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) { }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {  }

            override fun afterTextChanged(s: Editable) {
                try {
                    if (s.toString().toInt() >= 10000) {
                        btn_top_up.visibility = View.VISIBLE
                    } else {
                        tv_10k.setTextColor(resources.getColor(R.color.white))
                        tv_10k.setBackgroundResource(R.drawable.shape_line_white)
                        status10K = false
                        btn_top_up.visibility = View.INVISIBLE
                    }
                } catch (e : NumberFormatException) {
                    tv_10k.setTextColor(resources.getColor(R.color.white))
                    tv_10k.setBackgroundResource(R.drawable.shape_line_white)
                    status10K = false
                    btn_top_up.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun selectMoney(textView:TextView){
        textView.setTextColor(resources.getColor(R.color.colorTurqoise))
        textView.setBackgroundResource(R.drawable.shape_line_turqoise)
        status10K = true

        btn_top_up.visibility = View.VISIBLE
        et_amount.setText("10000")
    }

    private fun deselectMoney(textView:TextView){
        textView.setTextColor(resources.getColor(R.color.white))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status10K = false

        btn_top_up.visibility = View.INVISIBLE
        et_amount.setText("")
    }

}
