package dev.yuyuyuyuyu.leafarium.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yuyuyuyuyu.leafarium.sample.Leaf
import dev.yuyuyuyuyu.leafarium.sample.SampleForest
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors
import dev.yuyuyuyuyu.leafarium.ui.editor.LeafTextField
import dev.yuyuyuyuyu.leafarium.ui.leaf.LeafActions
import dev.yuyuyuyuyu.leafarium.ui.leaf.LeafBody
import dev.yuyuyuyuyu.leafarium.ui.leaf.LeafItem
import dev.yuyuyuyuyu.leafarium.ui.navigation.Navigator
import dev.yuyuyuyuyu.leafarium.ui.tree.Avatar
import dev.yuyuyuyuyu.leafarium.ui.tree.FollowButton

@Composable
fun LeafScreen(
    leafId: String,
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    val leaf = SampleForest.leaf(leafId)
    LazyColumn(modifier) {
        item { ScreenHeader(title = "Leaf", onBack = navigator.back) }
        if (leaf == null) {
            item { EmptyState("This Leaf has fallen.") }
        } else {
            item { LeafDetail(leaf, navigator) }
            item { ReplyComposer(leaf) }
            items(SampleForest.replies[leaf.id].orEmpty(), key = { it.id }) { reply ->
                LeafItem(reply, navigator)
            }
        }
    }
}

@Composable
private fun LeafDetail(
    leaf: Leaf,
    navigator: Navigator,
) {
    Column(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Avatar(
                tree = leaf.tree,
                size = 48.dp,
                modifier = Modifier.clip(CircleShape).clickable { navigator.openTree(leaf.tree.handle) },
            )
            Column(Modifier.weight(1f).clickable { navigator.openTree(leaf.tree.handle) }) {
                Text(leaf.tree.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text("@${leaf.tree.handle}", style = MaterialTheme.typography.bodyMedium, color = LeafariumColors.Muted)
            }
            if (leaf.tree != SampleForest.me) {
                FollowButton(initiallyFollowing = true)
            }
        }
        LeafBody(leaf.body, textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 19.sp, lineHeight = 29.sp))
        Text(leaf.grownAt, style = MaterialTheme.typography.bodyMedium, color = LeafariumColors.Muted)
        HorizontalDivider(color = LeafariumColors.Line)
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            LeafStat(leaf.shareCount, "Shares")
            LeafStat(leaf.likeCount, "Likes")
        }
        HorizontalDivider(color = LeafariumColors.Line)
        LeafActions(leaf, onReply = {})
    }
    HorizontalDivider(color = LeafariumColors.Line, modifier = Modifier.padding(top = 4.dp))
}

@Composable
private fun LeafStat(
    count: Int,
    label: String,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(count.toString(), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        Text(label, style = MaterialTheme.typography.bodyMedium, color = LeafariumColors.Muted)
    }
}

@Composable
private fun ReplyComposer(leaf: Leaf) {
    var draft by remember(leaf.id) { mutableStateOf(TextFieldValue()) }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Avatar(SampleForest.me, size = 40.dp)
            Column(Modifier.weight(1f)) {
                Text("Replying to @${leaf.tree.handle}", style = MaterialTheme.typography.bodySmall, color = LeafariumColors.Muted)
                LeafTextField(
                    value = draft,
                    onValueChange = { draft = it },
                    placeholder = "Grow a reply",
                    textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 17.sp),
                )
            }
            Button(onClick = { draft = TextFieldValue() }, enabled = draft.text.isNotBlank()) {
                Text("Reply")
            }
        }
        HorizontalDivider(color = LeafariumColors.Line)
    }
}
