package io.moxd.architecturesample.viewmodels

import android.app.Application
import androidx.lifecycle.*
import io.moxd.architecturesample.model.PersonStore
import io.moxd.architecturesample.model.persistence.AppDatabase
import io.moxd.architecturesample.model.persistence.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// you will not only have one single viewmodel, but maybe as many as you have screens
class PersonViewModel(app: Application) : AndroidViewModel(app) {

    private val db = AppDatabase.getInstance(app.applicationContext)
    private val personDao = db.personDao()
    private val personStore = PersonStore(personDao).apply {
        viewModelScope.launch(Dispatchers.IO) { init() }
    }

    fun getAllPersons(): LiveData<List<Person>> = personStore.getAll()

    fun addPerson(name: String, tel: String) {
        viewModelScope.launch(Dispatchers.IO) {
            personStore.addPerson(Person(name = name, tel = tel))
        }
    }
}