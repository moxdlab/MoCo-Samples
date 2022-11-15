package io.moxd.architecturesample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import io.moxd.architecturesample.compose.MainScreen
import io.moxd.architecturesample.ui.theme.ArchitectureSampleTheme
import io.moxd.architecturesample.viewmodels.PersonViewModel
import androidx.compose.runtime.livedata.observeAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<PersonViewModel>()
        setContent {
            ArchitectureSampleTheme {
                // no need for 'remember':
                val items by viewModel.getAllPersons().observeAsState(emptyList())
                val appOpenedCount by viewModel.getAppOpenedCount().observeAsState(0)
                MainScreen(appOpenedCount, items,
                    onFabClicked = {
                        viewModel.addPerson("Max Musterfrau", "+491234567890")
                    },
                    onItemClicked = { person ->
                        //discuss if this should be view, viewmodel or model related code:
                        Intent(Intent.ACTION_DIAL, Uri.parse("tel:${person.tel}")).also {
                            startActivity(it)
                        }
                    }
                )
            }
        }
    }
}

