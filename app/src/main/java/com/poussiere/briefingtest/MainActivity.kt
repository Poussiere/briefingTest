package com.poussiere.briefingtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.util.Log


class MainActivity : AppCompatActivity() {

    lateinit var mServiceIntent: Intent
    lateinit var mSensorService: SensorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSensorService = SensorService()
        mServiceIntent = Intent(this, mSensorService::class.java)
        if (!isMyServiceRunning(mSensorService::class.java)) {
            startService(mServiceIntent)
        }
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                Log.i("isMyServiceRunning?", true.toString() + "")
                return true
            }
        }
        Log.i("isMyServiceRunning?", false.toString() + "")
        return false
    }


    override fun onDestroy() {
        stopService(mServiceIntent)
        Log.i("MAINACT", "onDestroy!")
        super.onDestroy()

    }
}
