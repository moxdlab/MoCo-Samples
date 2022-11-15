package io.moxd.architecturesample.viewmodels

import android.app.Application
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import io.moxd.architecturesample.model.AppDataStore
import io.moxd.architecturesample.model.FakePersonStore
import io.moxd.architecturesample.model.Person

// you will not only have one single viewmodel, but maybe as many as you have screens
class PersonViewModel(private val app: Application) : AndroidViewModel(app) {
    private val personStore = FakePersonStore()

    fun getAllPersons() = personStore.getAll()
        .map {
            it.toMutableStateList()
        }

    fun getAppOpenedCount() = AppDataStore.getAppOpenedCount(app.applicationContext).asLiveData()

    fun addPerson(name: String, tel: String) {
        personStore.addPerson(Person(name, tel))
    }
}