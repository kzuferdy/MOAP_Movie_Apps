package onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kzuferdy.moapmovieapps.R
import com.kzuferdy.moapmovieapps.Utils.Preferences
import kotlinx.android.synthetic.main.activity_onboarding_one.*
import sign.signin.SignInActivity

class OnboardingOneActivity : AppCompatActivity() {

    lateinit var  preference:Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)

        preference = Preferences(this)

        if( preference.getValues("onboarding").equals("1")) {
            finishAffinity()

            var intent = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }

        btn_home.setOnClickListener {
          var intent = Intent(this@OnboardingOneActivity, OnboardingTwoActivity::class.java)
            startActivity(intent)
        }
        btn_daftar.setOnClickListener {
            preference.setValues("onboarding", "1")
            finishAffinity()

            var intent = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}