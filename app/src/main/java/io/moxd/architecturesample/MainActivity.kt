package io.moxd.architecturesample

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import io.moxd.architecturesample.compose.MainScreen
import io.moxd.architecturesample.ui.theme.ArchitectureSampleTheme
import io.moxd.architecturesample.viewmodels.PersonViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<PersonViewModel>()
        setContent {
            ArchitectureSampleTheme {
                // no need for 'remember':
                val items by viewModel.getAllPersons().observeAsState(emptyList())
                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {}

                MainScreen(
                    items,
                    onFabClicked = { viewModel.addPerson("Max Musterfrau", "+491234567890") },
                    onItemClicked = { person ->
                        when (ContextCompat.checkSelfPermission(this, CALL_PHONE)) {
                            PackageManager.PERMISSION_GRANTED -> {
                                Intent(Intent.ACTION_CALL, Uri.parse("tel:${person.tel}")).also {
                                    startActivity(it)
                                }
                            }
                            else -> launcher.launch(CALL_PHONE)
                        }
                    }
                )
            }
        }
    }
}

