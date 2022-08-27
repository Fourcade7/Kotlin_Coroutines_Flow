package com.pr.kotlin_coroutines_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.concurrent.thread
//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
class MainActivity : AppCompatActivity() {
     val TAG = "PR7"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //first coroutine
        gs()






    }



    fun gs(){

        GlobalScope.launch {
            delay(1000)
            Log.d(TAG, "gs: ${Thread.currentThread().name}")

        }
        Log.d(TAG, "gs: ${Thread.currentThread().name}")

    }


}