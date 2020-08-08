package evan.chen.tutorial.tdd.robolectricsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.support.v7.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send.setOnClickListener {

            val loginId = loginId.text.toString()
            val pwd = password.text.toString()

            val isLoginIdOK = RegisterVerify().isLoginIdVerify(loginId)

            val isPwdOK = RegisterVerify().isPasswordVerify(pwd)

            val builder = AlertDialog.Builder(this)

            if (!isLoginIdOK) {
                // Register fail
                val builder = AlertDialog.Builder(this)

                builder.setMessage("loginId at least 8 words and first letter should be alphabetized.")
                    .setTitle("Error")

                builder.show()
            }

            else if (!isPwdOK) {
                val builder = AlertDialog.Builder(this)

                builder.setMessage("Password at least 8 words and first letter should be alphabetized.")
                    .setTitle("Error")

                builder.show()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("ID", loginId)

                startActivity(intent)
            }
        }
    }
}
