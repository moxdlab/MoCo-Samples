package io.moxd.architecturesample.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// might be "Room" or another real, persisting storage in future
class FakePersonStore private constructor() {

    companion object {
        var INSTANCE: FakePersonStore? = null //TODO: not thread safe, look up "singleton pattern"
        fun getInstance(): FakePersonStore {
            return if (INSTANCE == null)
                FakePersonStore().also { INSTANCE = it }
            else INSTANCE!!
        }
    }

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
        personListLive.postValue(personList)
    }

    fun getAll(): LiveData<List<Person>> = personListLive
}