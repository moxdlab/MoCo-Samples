package io.moxd.architecturesample

import android.app.Application
import io.moxd.architecturesample.model.AppDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // TODO: this seems to be persistent work, doesn't it? (never blindly use MainScope!!!)
        MainScope().launch(Dispatchers.IO) {
            AppDataStore.incAppOpenedCount(applicationContext)
        }
    }
}