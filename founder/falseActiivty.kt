package com.kiit.founder

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar

class falseActiivty : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_false_actiivty)

        Handler().postDelayed(
        {
            var i = Intent(this@falseActiivty,MainActivity::class.java)
            startActivity(i)
            this.finish()
        },40
)

    }
}
