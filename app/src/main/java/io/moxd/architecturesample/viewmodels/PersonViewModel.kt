package io.moxd.architecturesample.viewmodels

import android.app.Application
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.map
import io.moxd.architecturesample.model.FakePersonStore
import io.moxd.architecturesample.model.NotificationHelper
import io.moxd.architecturesample.model.Person

// you will not only have one single viewmodel, but maybe as many as you have screens
class PersonViewModel(app: Application) : AndroidViewModel(app) {
    private val personStore = FakePersonStore()
    private val notificationHelper = NotificationHelper(app.applicationContext)

    fun getAllPersons() = personStore.getAll()
        .map {
            it.toMutableStateList()
        }

    fun addPerson(name: String, tel: String) {
        val p = Person(name, tel)
        personStore.addPerson(p)
        notificationHelper.postNewUserNotification(p)
    }
}