package com.poussiere.briefingtest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SensorRestarterBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i(SensorRestarterBroadcastReceiver::class.java.simpleName, "Service Stops! Oooooooooooooppppssssss!!!!")
        context.startService(Intent(context, SensorService::class.java))
    }

}