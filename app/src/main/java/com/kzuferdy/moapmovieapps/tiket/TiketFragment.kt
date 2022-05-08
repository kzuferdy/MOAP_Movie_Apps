package com.kzuferdy.moapmovieapps.tiket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.kzuferdy.moapmovieapps.R
import com.kzuferdy.moapmovieapps.home.dashboard.ComingSoonAdapter
import com.kzuferdy.moapmovieapps.model.Film
import kotlinx.android.synthetic.main.fragment_tiket.*
import java.util.prefs.Preferences


/**
 * A simple [Fragment] subclass.
 * Use the [TiketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TiketFragment : Fragment() {

    private lateinit var preferences: com.kzuferdy.moapmovieapps.Utils.Preferences
    lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tiket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = com.kzuferdy.moapmovieapps.Utils.Preferences(requireContext())
        mDatabase = FirebaseDatabase.getInstance("https://moapmovieapps-default-rtdb.firebaseio.com").getReference("Film")


        rc_tiket.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        getData()

    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val film = getdataSnapshot.getValue(Film::class.java!!)
                    dataList.add(film!!)
                }

                rc_tiket.adapter = ComingSoonAdapter(dataList) {
                    val intent = Intent(context,
                        TiketActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                tv_total.setText(dataList.size.toString() +" Movies")

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}
