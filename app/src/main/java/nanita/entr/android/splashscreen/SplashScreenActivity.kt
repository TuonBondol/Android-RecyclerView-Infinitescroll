package nanita.entr.android.splashscreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import core.mvp.kotlinandroid.R
import nanita.entr.android.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val mainIntent = Intent(this, HomeActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 2000)
    }
}
