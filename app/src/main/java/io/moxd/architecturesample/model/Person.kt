package io.moxd.architecturesample.model

import android.content.Intent
import android.net.Uri

data class Person(val name: String, val tel: String)

fun Person.callIntent() = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${this.tel}"))