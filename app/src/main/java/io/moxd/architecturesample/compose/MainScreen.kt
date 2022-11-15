package io.moxd.architecturesample.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.moxd.architecturesample.model.Person

@Composable
fun MainScreen(
    appOpenedCount: Int,
    itemsToShow: List<Person>,
    onFabClicked: () -> Unit,
    onItemClicked: (person: Person) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "App opened $appOpenedCount times") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClicked) {
                Icon(Icons.Filled.Add, contentDescription = "add")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Spacer(Modifier.height(20.dp))
            LazyColumn {
                itemsIndexed(itemsToShow) { index, (name, tel) ->
                    PersonEntry(name = name, tel = tel) {
                        onItemClicked(itemsToShow[index])
                    }
                    Divider()
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(12, itemsToShow = listOf(
        Person("Preview", "+49123456789"),
        Person("Preview 2", "+49987654321")
    ), onFabClicked = {}, onItemClicked = {})
}