package sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.kzuferdy.moapmovieapps.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import sign.signin.User

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseInstance : FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance("https://moapmovieapps-default-rtdb.firebaseio.com")
        mDatabase = FirebaseDatabase.getInstance("https://moapmovieapps-default-rtdb.firebaseio.com").getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        btn_lanjutkan.setOnClickListener {

            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if (sUsername.equals("")){
                et_username.error ="Silahkan Isi Username Anda"
                et_username.requestFocus()
            } else if (sPassword.equals("")){
                et_password.error ="Silahkan Isi Password Anda"
                et_password.requestFocus()
            }else if (sNama.equals("")){
                et_nama.error ="Silahkan Isi Nama Anda"
                et_nama.requestFocus()
            }else if (sEmail.equals("")){
                et_email.error ="Silahkan Isi Email Anda"
                et_email.requestFocus()
            } else{
                saveUsername(sUsername, sPassword, sNama, sEmail)
            }

        }
    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
      var user = User()
      user.email = sEmail
      user.username = sUsername
      user.nama = sNama
      user.password = sPassword

        if(sUsername != null) {
            chcekingUsername(sUsername, user)
        }
    }

    private fun chcekingUsername(sUsername: String, data: User) {
         mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener {
             override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+databaseError.message, Toast.LENGTH_LONG).show()
             }

             override fun onDataChange(dataSnapshot: DataSnapshot) {
                 var user = dataSnapshot.getValue(User::class.java)
                 if(user == null){
                   mDatabaseReference.child(sUsername).setValue(data)

                   var goSignUpPhotoscreenActivity = Intent(this@SignUpActivity,
                       SignUpPhotoscreenActivity::class.java).putExtra("nama",
                       data.nama)

                     startActivity(goSignUpPhotoscreenActivity)

                 } else {
                     Toast.makeText(this@SignUpActivity, "User Sudah Di Gunakan", Toast.LENGTH_LONG).show()
                 }
             }


         })
    }
}