package io.moxd.architecturesample.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import io.moxd.architecturesample.model.FakePersonStore
import io.moxd.architecturesample.model.Person
import io.moxd.architecturesample.model.PersonDownloader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// you will not only have one single viewmodel, but maybe as many as you have screens
class PersonViewModel : ViewModel() {

    init {
        updateAllPersons()
    }

    private val personStore = FakePersonStore()

    fun getAllPersons() = personStore.getAll()
        .map {
            it.toMutableStateList()
        }

    fun addPerson(name: String, tel: String) {
        personStore.addPerson(Person(name, tel))
    }

    private fun updateAllPersons() {
        viewModelScope.launch(Dispatchers.IO) {
            val persons = PersonDownloader().downloadNewPersons()
            persons.forEach { personStore.addPerson(it) }
        }
    }
}