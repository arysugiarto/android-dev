package com.wisnu.feature.order.drink

import com.wisnu.launcher.main.Application
import com.wisnu.launcher.main.Launcher

class OrderDrinkApplication : Application {
    override fun onCreate(launcher: Launcher) {
        launcher.registerFeature(OrderDrinkFeature(launcher.application))
    }
}