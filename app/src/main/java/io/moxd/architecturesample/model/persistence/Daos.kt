package io.moxd.architecturesample.model.persistence

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDao {

    @Insert
    suspend fun insert(vararg person: Person): List<Long>

    @Query("SELECT * FROM persons")
    fun getAllContacts(): LiveData<List<Person>>

    @Query("SELECT Count(*) FROM persons")
    suspend fun getPersonCount(): Int

}