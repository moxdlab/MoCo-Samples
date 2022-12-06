package io.moxd.architecturesample.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.moxd.architecturesample.R
import kotlin.random.Random

//TODO: homework -> ask for permission
class NotificationHelper(private val context: Context) {

    companion object {
        private const val CHANNEL_ID = "contacts_add_channel"
    }

    init {
        createNotificationChannel()
    }

    fun postNewUserNotification(person: Person) {

        val pendingIntent = PendingIntent.getActivity(
            context, 0, person.callIntent(), PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_person_24)
            .setContentTitle("Contact added!") //-> strings.xml
            .setContentText("${person.name}: ${person.tel}")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(Random.nextInt(), builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Contacts Added Channel" //-> strings.xml
            val descriptionText =
                "Notification channel for new contacts being added" //-> strings.xml
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}