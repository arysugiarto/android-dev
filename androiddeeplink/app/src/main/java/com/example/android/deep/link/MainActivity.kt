package com.example.android.deep.link

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.example.android.deep.link.deeplink.AppDeepLinkModule

@DeepLinkHandler(AppDeepLinkModule::class)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val deepLinkDelegate = DeepLinkDelegate(AppDeepLinkModuleLoader())

        deepLinkDelegate.dispatchFrom(this)

        finish()
    }
}