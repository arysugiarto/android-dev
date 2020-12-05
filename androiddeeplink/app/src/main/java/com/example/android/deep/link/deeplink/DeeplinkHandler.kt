package com.example.android.deep.link.deeplink


import android.content.Context
import android.content.Intent
import com.airbnb.deeplinkdispatch.DeepLink
import com.airbnb.deeplinkdispatch.DeepLinkModule
import com.example.android.deep.link.Activity1


@DeepLinkModule
class AppDeepLinkModule

@DeepLink("wisnu://awesome")
fun starActivity1(context: Context): Intent{
    return Intent(context, Activity1::class.java)
}