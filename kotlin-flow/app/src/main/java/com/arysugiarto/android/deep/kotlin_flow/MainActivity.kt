package com.arysugiarto.android.deep.kotlin_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainActivity : AppCompatActivity() {

    lateinit var flow: Flow<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFlow()
        setupClick()

    }


    fun setupFlow(){
        flow = flow(){
            (0..10).forEach {
                // Emit items with 500 milliseconds delay
                delay(500)
                emit(it)

            }
        }.flowOn(Dispatchers.Default)
    }


}