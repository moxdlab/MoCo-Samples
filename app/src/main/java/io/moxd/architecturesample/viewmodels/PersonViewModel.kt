package io.moxd.architecturesample.viewmodels

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.toMutableStateList
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.map
import io.moxd.architecturesample.model.FakePersonStore
import io.moxd.architecturesample.model.Person
import io.moxd.architecturesample.receiver.ScreenStatusReceiver

// you will not only have one single viewmodel, but maybe as many as you have screens
class PersonViewModel(private val app: Application) : AndroidViewModel(app) {
    private val personStore = FakePersonStore()
    private val receiver = ScreenStatusReceiver()

    init {
        registerReceiver()
    }

    override fun onCleared() {
        unregisterReceiver()
        super.onCleared()
    }

    fun getAllPersons() = personStore.getAll().map {
        it.toMutableStateList()
    }

    fun addPerson(name: String, tel: String) {
        personStore.addPerson(Person(name, tel))
    }

    private fun registerReceiver() {
        val screenOnOffFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
        ContextCompat.registerReceiver(
            app.applicationContext,
            receiver,
            screenOnOffFilter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

    private fun unregisterReceiver() {
        app.applicationContext.unregisterReceiver(receiver)
    }
}