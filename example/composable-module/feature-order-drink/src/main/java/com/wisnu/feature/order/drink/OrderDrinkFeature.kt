package com.wisnu.feature.order.drink

import android.content.Intent
import android.app.Application
import com.wisnu.launcher.main.FeatureLaunchable
import com.wisnu.launcher.main.FeatureType

class OrderDrinkFeature(private val application: Application): FeatureLaunchable {
    override val type: Int = FeatureType.ORDER_DRINK

    override fun title(): Int = R.string.feature_order_drink

    override fun intent(): Intent? = Intent(application, OrderDrinkActivity::class.java)

    override fun icon(): Int = R.drawable.ic_flight

}