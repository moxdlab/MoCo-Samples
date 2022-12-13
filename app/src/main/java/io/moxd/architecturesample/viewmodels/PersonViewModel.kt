package io.moxd.architecturesample.viewmodels

import android.app.Application
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.map
import io.moxd.architecturesample.model.FakePersonStore
import io.moxd.architecturesample.model.Person
import io.moxd.architecturesample.model.ProximitySensorHelper

// you will not only have one single viewmodel, but maybe as many as you have screens
class PersonViewModel(app: Application) : AndroidViewModel(app) {

    private val proximitySh = ProximitySensorHelper(app).also {
        it.register()
    }

    private val personStore = FakePersonStore()

    fun getAllPersons() = personStore.getAll()
        .map {
            it.toMutableStateList()
        }

    fun addPerson(name: String, tel: String) {
        personStore.addPerson(Person(name, tel))
    }

    override fun onCleared() {
        proximitySh.unregister()
        super.onCleared()
    }
}