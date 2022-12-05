package io.moxd.architecturesample.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ScreenStatusReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SCREEN_ON -> {
                Log.d("debug", "screen is on!")
            }
            Intent.ACTION_SCREEN_OFF -> {
                Log.d("debug", "screen is off!")
            }
        }
    }
}