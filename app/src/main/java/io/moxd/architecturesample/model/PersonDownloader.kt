package io.moxd.architecturesample.model

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PersonDownloader {

    private val host = "http://10.0.2.2:8080" // that is the development host, for you this might be "https://google.com" etc.

    suspend fun downloadNewPersons(): List<Person> {
        val client = HttpClient()
        val response = client.request(host)
        val personData = response.body<String>()
        val (name, tel) = personData.split(",")
        return listOf(Person(name, tel))
    }

}