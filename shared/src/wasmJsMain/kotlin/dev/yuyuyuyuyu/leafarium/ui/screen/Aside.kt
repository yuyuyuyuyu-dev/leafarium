package dev.yuyuyuyuyu.leafarium.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.leafarium.sample.SampleForest
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors
import dev.yuyuyuyuyu.leafarium.ui.leaf.LeafBody
import dev.yuyuyuyuyu.leafarium.ui.navigation.Navigator
import dev.yuyuyuyuyu.leafarium.ui.tree.Avatar
import dev.yuyuyuyuyu.leafarium.ui.tree.FollowButton

private val markdownSamples = listOf("**bold**", "*italic*", "`code`", "[text](https://example.com)", "- item", "> quote")

@Composable
fun Aside(
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()).padding(start = 24.dp, end = 16.dp, top = 24.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        AsideCard(title = "Trees to follow") {
            SampleForest.suggestedTrees.forEach { tree ->
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Avatar(tree, size = 40.dp)
                    Column(Modifier.weight(1f).clickable { navigator.openTree(tree.handle) }) {
                        Text(tree.name, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                        Text("@${tree.handle}", style = MaterialTheme.typography.bodySmall, color = LeafariumColors.Muted)
                    }
                    FollowButton()
                }
            }
        }
        AsideCard(title = "Write in Markdown") {
            markdownSamples.forEach { sample ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = sample.substringBefore("(") + if (sample.contains("(")) "(url)" else "",
                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                        color = LeafariumColors.Subtle,
                        modifier = Modifier.weight(1f),
                    )
                    LeafBody(sample, Modifier.weight(1f), MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
private fun AsideCard(
    title: String,
    content: @Composable () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = LeafariumColors.Surface,
        border = BorderStroke(1.dp, LeafariumColors.Line),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(horizontal = 20.dp, vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.semantics { heading() })
            content()
        }
    }
}
