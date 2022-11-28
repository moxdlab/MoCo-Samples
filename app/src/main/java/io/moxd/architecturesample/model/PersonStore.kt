package io.moxd.architecturesample.model

import androidx.lifecycle.LiveData
import io.moxd.architecturesample.model.persistence.Person
import io.moxd.architecturesample.model.persistence.PersonDao

class PersonStore(private val personDao: PersonDao) {

    suspend fun init() {
        if (personDao.getPersonCount() == 0)
            fillWithFakeValues()
    }

    private suspend fun fillWithFakeValues() {
        addPerson(Person(name = "Max Mustermann", tel = "+490123456789"))
        addPerson(Person(name = "Max Mustermann 1", tel = "+490123456799"))
        addPerson(Person(name = "Max Mustermann 2", tel = "+490123456711"))
    }


    suspend fun addPerson(p: Person) {
        personDao.insert(p)
    }

    fun getAll(): LiveData<List<Person>> = personDao.getAllContacts()
}