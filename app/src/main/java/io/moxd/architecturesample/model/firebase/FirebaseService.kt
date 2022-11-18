package io.moxd.architecturesample.model.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.moxd.architecturesample.model.FakePersonStore
import io.moxd.architecturesample.model.Person

//see https://github.com/firebase/snippets-android/blob/69e09a88c1fe5fba88e3c78fabd9968813bd15ca/messaging/app/src/main/java/com/google/firebase/example/messaging/kotlin/MyFirebaseMessagingService.kt#L23-L48
class FirebaseService : FirebaseMessagingService() {

    private val TAG = "FCM"

    override fun onMessageReceived(message: RemoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${message.from}")

        // Check if message contains a data payload.
        if (message.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${message.data}")

            if (/* Check if data needs to be processed by long running job */ false) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleJob()
            } else {
                // Handle message within 10 seconds
                handleNow(message.data)
            }
        }

        // Check if message contains a notification payload.
        message.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }

        //...
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "New Token: $token")
    }

    private fun handleNow(data: Map<String, String>) {
        data.forEach {
            FakePersonStore.addPerson(Person(it.key, it.value))
        }
    }

    private fun scheduleJob() {
        TODO("schedule using WorkManager")
    }
}