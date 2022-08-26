package com.pr.kotlin_coroutines_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
//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
class MainActivity : AppCompatActivity() {
     val TAG = "PR7"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: ${Thread.currentThread().name}")
        thread {
            Log.d(TAG, "onCreate: ${Thread.currentThread().name}")
            Thread.sleep(1000)
            Log.d(TAG, "onCreate: ${Thread.currentThread().name}")

        }
        Thread.sleep(2000)
        Log.d(TAG, "onCreate: ${Thread.currentThread().name}")
        GlobalScope.launch {
            time()
            Log.d(TAG, "Coroutine: ${Thread.currentThread().name}")

        }


    }

    suspend fun time(){
        delay(2000)
    }
}