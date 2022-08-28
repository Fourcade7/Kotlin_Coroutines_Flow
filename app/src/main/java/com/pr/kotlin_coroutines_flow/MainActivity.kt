package com.pr.kotlin_coroutines_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

//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
class MainActivity : AppCompatActivity() {
    val TAG = "PR7"
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textview1)
        //first coroutine
        //gs()
        //usessusfunction()
        //coroutine_context()
        //runblock()
        //use_job_join()
        //use_job_cancel()
        //chek_active()
        //withtimeout()
        //async()
        //await()
        //lifecyclescope()
        goflow()


    }


    fun gs() {

        GlobalScope.launch {
            delay(1000)
            Log.d(TAG, "gs: ${Thread.currentThread().name}")

        }
        Log.d(TAG, "gs: ${Thread.currentThread().name}")

    }

    fun usessusfunction() {
        GlobalScope.launch {
            Log.d(TAG, "usessusfunction: ${gonetworkcall()}")
            Log.d(TAG, "usessusfunction: ${gonetworkcall2()}")
        }
    }

    suspend fun gonetworkcall(): String {
        delay(3000)
        return "This is answer"
    }

    suspend fun gonetworkcall2(): String {
        delay(3000)
        return "This is answer2"
    }

    fun coroutine_context() {
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "gs: ${Thread.currentThread().name}")

            val answer = gonetworkcall()
            withContext(Dispatchers.Main) {
                textView.text = answer
                Log.d(TAG, "gs: ${Thread.currentThread().name}")

            }

        }
    }

    fun runblock() {
        runBlocking {
            delay(2000)
            launch(Dispatchers.IO) {
                Log.d(TAG, "gs: ${Thread.currentThread().name}")
            }
            Log.d(TAG, "gs: ${Thread.currentThread().name}")
        }
    }

    fun use_job_join() {
        val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "gs Job: ${Thread.currentThread().name}")
            repeat(5) {
                Log.d(TAG, "gs Repeat: ${Thread.currentThread().name}")
                delay(1000)
            }
        }
        runBlocking {
            delay(2000)
            job.join()
            Log.d(TAG, "gs Runblock: ${Thread.currentThread().name}")

        }
    }

    fun use_job_cancel() {
        val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "gs Job: ${Thread.currentThread().name}")
            repeat(5) {
                Log.d(TAG, "gs Repeat: ${Thread.currentThread().name}")
                delay(1000)
            }
        }
        runBlocking {
            delay(2000)
            job.cancel()
            Log.d(TAG, "gs Runblock: ${Thread.currentThread().name}")

        }
    }


    fun chek_active() {
        val job = GlobalScope.launch {
            for (i in 0 until 10) {
                if (isActive) {

                    delay(1000)
                    Log.d(TAG, "gs Active: ${Thread.currentThread().name} $i")
                }
            }
        }
        runBlocking {
            delay(5000)
            job.join()
            Log.d(TAG, "gs Active: ${Thread.currentThread().name} ")

        }
    }

    fun withtimeout() {
        val job = GlobalScope.launch {
            withTimeout(4000) {
                for (i in 0 until 10) {
                    if (isActive) {
                        delay(1000)
                        Log.d(TAG, "gs Active: ${Thread.currentThread().name} $i")
                    }
                }
            }
        }
        runBlocking {
            delay(5000)
            job.join()
            Log.d(TAG, "gs Active: ${Thread.currentThread().name} ")

        }
    }

    fun async() {
        GlobalScope.launch(Dispatchers.IO) {

            val time = measureTimeMillis {
                // Log.d(TAG, "gs Active: ${Thread.currentThread().name} ")

                val job1 = launch { Log.d(TAG, "call: ${gonetworkcall()}") }
                val job2 = launch { Log.d(TAG, "call: ${gonetworkcall2()}") }
                job1.join()
                job2.join()
            }
            Log.d(TAG, "async_await: $time")
            Log.d(TAG, "gs Active: ${Thread.currentThread().name} ")


        }
    }

    fun await() {
        GlobalScope.launch(Dispatchers.IO) {

            val time = measureTimeMillis {


                val req1 = async {
                    gonetworkcall()
                }
                val req2 = async {
                    gonetworkcall()
                }
                Log.d(TAG, "req 1: ${req1.await()}")
                Log.d(TAG, "req 2: ${req2.await()}")


            }

            Log.d(TAG, "time: $time")


        }
    }

    fun lifecyclescope() {
        lifecycleScope.launch {
            while (true) {
                delay(1000)
                Log.d(TAG, "lifecyclescope finished when activity ended: ")
            }
        }
    }


    fun goflow() {
        val myflow = flow<Int> {
            for (i in 1..10) {
                emit(i)
                delay(1000)

            }
            for (i in 20..30) {
                emit(i)
                delay(3000)

            }
        }

        GlobalScope.launch {
            myflow.buffer().filter {
                it%2==1
            }.map {
                it*2
            }.collect {
                Log.d(TAG, "collected $it ")


            }
        }
    }


}