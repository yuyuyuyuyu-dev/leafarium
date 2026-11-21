package dev.yuyuyuyuyu.leafarium.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.leafarium.sample.SampleForest
import dev.yuyuyuyuyu.leafarium.ui.editor.ForestComposer
import dev.yuyuyuyuyu.leafarium.ui.leaf.LeafItem
import dev.yuyuyuyuyu.leafarium.ui.navigation.Navigator

@Composable
fun ForestScreen(
    navigator: Navigator,
    compact: Boolean,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        if (!compact) {
            item { ScreenHeader(title = "Forest", subtitle = "Leaves from the Trees you follow") }
            item { ForestComposer() }
        }
        items(SampleForest.homeForest, key = { it.id }) { leaf ->
            LeafItem(leaf, navigator, horizontalPadding = if (compact) 16.dp else 24.dp)
        }
    }
}
