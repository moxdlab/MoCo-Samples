package io.moxd.architecturesample.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.moxd.architecturesample.R
import io.moxd.architecturesample.model.persistence.Person

@Composable
fun MainScreen(
    itemsToShow: List<Person>,
    onFabClicked: () -> Unit,
    onItemClicked: (person: Person) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClicked) {
                Icon(Icons.Filled.Add, contentDescription = "add")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LazyColumn {
                itemsIndexed(itemsToShow) { index, (_, name, tel) ->
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
    MainScreen(itemsToShow = listOf(
        Person(1, "Preview", "+49123456789"),
        Person(2, "Preview 2", "+49987654321")
    ), onFabClicked = {}, onItemClicked = {})
}