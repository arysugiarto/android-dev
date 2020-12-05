package com.example.android.deep.link

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.deeplinkdispatch.DeepLink
import kotlinx.android.synthetic.main.activity_1.*

class Activity1 : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val parameters = intent.extras
            val phone = parameters.getString("phone")
            val message = parameters.getString("text")
            tv1.text = "Phone: $phone \n" +
                    "Message: $message"
        }
    }
}