package dev.yuyuyuyuyu.leafarium.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlaceholderScreen(
    title: String,
    compact: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        if (!compact) {
            ScreenHeader(title = title)
        }
        EmptyState("This part of the Forest has not grown yet.")
    }
}
