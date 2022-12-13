package io.moxd.architecturesample.model

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class ProximitySensorHelper(
    private val context: Context,
    private val onNewProximity: (proximity: Float) -> Unit = {},
) {

    companion object {
        private const val TAG = "ProximitySensor"
    }

    private val sensorManager by lazy {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            event?.values?.first()?.let { proximity ->
                Log.d(TAG, "Proximity: $proximity cm")
                onNewProximity(proximity)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            Log.d(TAG, "accuracy changed: ${accuracy.readable()}")
        }

    }

    fun register() {
        if (!hasSensor()) return
        sensorManager.registerListener(
            sensorEventListener,
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    fun unregister() {
        sensorManager.unregisterListener(sensorEventListener)
    }

    private fun hasSensor(): Boolean {
        return sensor != null
    }

}

private fun Int.readable(): String {
    return when (this) {
        SensorManager.SENSOR_STATUS_ACCURACY_HIGH -> "high"
        SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM -> "medium"
        SensorManager.SENSOR_STATUS_ACCURACY_LOW -> "low"
        SensorManager.SENSOR_STATUS_UNRELIABLE -> "unreliable"
        SensorManager.SENSOR_STATUS_NO_CONTACT -> "no contact"
        else -> "unknown sensor accuracy"
    }
}