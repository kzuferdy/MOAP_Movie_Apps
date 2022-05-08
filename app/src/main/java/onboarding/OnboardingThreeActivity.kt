package onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kzuferdy.moapmovieapps.R
import kotlinx.android.synthetic.main.activity_onboarding_three.*
import sign.signin.SignInActivity

class OnboardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)
        btn_home.setOnClickListener {
            var intent = Intent(this@OnboardingThreeActivity, SignInActivity::class.java)

           startActivity(intent)

        }
    }
}