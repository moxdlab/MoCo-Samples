package io.moxd.architecturesample.model

import kotlinx.coroutines.delay

class FakePersonDownloader {

    suspend fun downloadNewPersons(): List<Person> {
        delay(1500) //simulate download
        return listOf(Person("Downloaded Person", "+49123000123"))
    }

}