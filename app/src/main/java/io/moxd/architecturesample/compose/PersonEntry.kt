package io.moxd.architecturesample.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PersonEntry(
    modifier: Modifier = Modifier,
    name: String,
    tel: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = name,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            text = tel,
            modifier = Modifier.align(Alignment.CenterEnd),
            style = MaterialTheme.typography.h6
        )
    }
}

@Preview
@Composable
fun PersonEntryPreview() {
    PersonEntry(name = "Max Mustermann", tel = "+490123456789") { }
}