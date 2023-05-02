package io.moxd.architecturesample.viewmodels

import androidx.lifecycle.ViewModel
import io.moxd.architecturesample.model.FakePersonStore
import io.moxd.architecturesample.model.Person

// you will not only have one single viewmodel, but maybe as many as you have screens
class PersonViewModel : ViewModel() {
    private val personStore = FakePersonStore()

    fun getAllPersons() = personStore.getAll()

    fun addPerson(name: String, tel: String) {
        personStore.addPerson(Person(name, tel))
    }
}