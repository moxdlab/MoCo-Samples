package io.moxd.architecturesample.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// might be "Room" or another real, persisting storage in future
object FakePersonStore {

    private val personList = mutableListOf<Person>()
    private val personListLive = MutableLiveData<List<Person>>()

    init {
        fillWithFakeValues()
    }

    private fun fillWithFakeValues() {
        addPerson(Person("Max Mustermann", "+490123456789"))
        addPerson(Person("Max Mustermann 1", "+490123456799"))
        addPerson(Person("Max Mustermann 2", "+490123456711"))
    }


    fun addPerson(p: Person) {
        personList += p
        personListLive.postValue(personList.toList())
    }

    fun getAll(): LiveData<List<Person>> = personListLive
}