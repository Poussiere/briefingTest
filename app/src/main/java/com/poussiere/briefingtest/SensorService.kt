package com.poussiere.briefingtest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.annotation.Nullable
import android.util.Log
import java.util.*


class SensorService : Service() {
    var counter = 0


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        startTimer()
        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("EXIT", "ondestroy!")
        val broadcastIntent = Intent("uk.ac.shef.oak.ActivityRecognition.RestartSensor")
        sendBroadcast(broadcastIntent)
        stoptimertask()
    }

    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    var oldTime: Long = 0
    fun startTimer() {
        //set a new Timer
        timer = Timer()

        //initialize the TimerTask's job
        initializeTimerTask()

        //schedule the timer, to wake up every 1 second
        timer!!.schedule(timerTask, 1000, 1000) //
    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    fun initializeTimerTask() {
        timerTask = object : TimerTask() {
            override fun run() {
                Log.i("in timer", "in timer ++++  " + counter++)
            }
        }
    }

    /**
     * not needed
     */
    fun stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }

    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}