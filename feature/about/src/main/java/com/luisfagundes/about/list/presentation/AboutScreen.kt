package com.luisfagundes.about.list.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.luisfagundes.about.model.AboutItem
import com.luisfagundes.about.model.aboutItemList

@Composable
fun AboutRoute(
    onItemClick: (descriptionId: Int) -> Unit,
) {
    AboutScreen(
        modifier = Modifier.fillMaxWidth(),
        onItemClick = onItemClick
    )
}

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onItemClick: (descriptionId: Int) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(aboutItemList) { item ->
            Item(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onItemClick(item.descriptionResId) },
                item = item
            )
            HorizontalDivider(
                thickness = 1.dp
            )
        }
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    item: AboutItem
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = context.getString(item.titleResId)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
           text = context.getString(item.titleResId)
        )
    }
}