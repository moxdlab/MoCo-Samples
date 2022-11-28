package io.moxd.architecturesample.model.persistence

import androidx.room.*

@Entity(tableName = "persons")
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String,
    val tel: String,
)