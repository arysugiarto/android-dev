package com.arysugiarto.android.deep.modular_naviagtion_component

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(findViewById(R.id.mainNavigationFragment)).navigateUp()

    private fun setupNavigation() {
        val navController = Navigation.findNavController(findViewById(R.id.mainNavigationFragment))
        NavigationUI.setupActionBarWithNavController(this@MainActivity, navController)
    }
}