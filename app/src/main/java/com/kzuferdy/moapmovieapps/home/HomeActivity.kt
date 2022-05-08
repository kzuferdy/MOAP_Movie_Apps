package com.kzuferdy.moapmovieapps.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.kzuferdy.moapmovieapps.R
import com.kzuferdy.moapmovieapps.home.dashboard.DashboardFragment
import com.kzuferdy.moapmovieapps.settings.SettingFragment
import com.kzuferdy.moapmovieapps.tiket.TiketFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val fragmentTiket = TiketFragment()
        val fragmentSetting = SettingFragment()
        val fragmentHome = DashboardFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(iv_menu1, R.drawable.icons_home_active)
            changeIcon(iv_menu2, R.drawable.icons_ticket)
            changeIcon(iv_menu3, R.drawable.icons_profile)
        }
        iv_menu2.setOnClickListener {
            setFragment(fragmentTiket)

            changeIcon(iv_menu1, R.drawable.icons_home)
            changeIcon(iv_menu2, R.drawable.icons_ticket_active)
            changeIcon(iv_menu3, R.drawable.icons_profile)
        }
        iv_menu3.setOnClickListener {
            setFragment(fragmentSetting)

            changeIcon(iv_menu1, R.drawable.icons_home)
            changeIcon(iv_menu2, R.drawable.icons_ticket)
            changeIcon(iv_menu3, R.drawable.icons_profile_active)
        }

    }

    private fun setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransacion = fragmentManager.beginTransaction()
        fragmentTransacion.replace(R.id.layout_frame, fragment)
        fragmentTransacion.commit()
    }

    private fun changeIcon(imageView: ImageView, int : Int){
        imageView.setImageResource(int)
    }

}