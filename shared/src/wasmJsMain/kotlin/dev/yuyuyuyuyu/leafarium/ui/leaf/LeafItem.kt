package dev.yuyuyuyuyu.leafarium.ui.leaf

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.leafarium.sample.Leaf
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors
import dev.yuyuyuyuyu.leafarium.ui.navigation.Navigator
import dev.yuyuyuyuyu.leafarium.ui.tree.Avatar

@Composable
fun LeafItem(
    leaf: Leaf,
    navigator: Navigator,
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 24.dp,
) {
    Column(modifier.fillMaxWidth()) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .clickable(onClickLabel = "Open Leaf", role = Role.Button) { navigator.openLeaf(leaf.id) }
                    .padding(horizontal = horizontalPadding, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Avatar(
                tree = leaf.tree,
                modifier =
                    Modifier
                        .clip(CircleShape)
                        .clickable(onClickLabel = "Open ${leaf.tree.name}'s Tree") { navigator.openTree(leaf.tree.handle) },
            )
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                LeafByline(leaf, navigator)
                LeafBody(leaf.body)
                LeafActions(leaf, onReply = { navigator.openLeaf(leaf.id) })
            }
        }
        HorizontalDivider(color = LeafariumColors.Line)
    }
}

@Composable
private fun LeafByline(
    leaf: Leaf,
    navigator: Navigator,
) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = leaf.tree.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(onClickLabel = "Open ${leaf.tree.name}'s Tree") { navigator.openTree(leaf.tree.handle) },
        )
        Text("@${leaf.tree.handle}", style = MaterialTheme.typography.bodyMedium, color = LeafariumColors.Muted)
        Text("·", style = MaterialTheme.typography.bodyMedium, color = LeafariumColors.Muted)
        Text(leaf.grownAgo, style = MaterialTheme.typography.bodyMedium, color = LeafariumColors.Muted)
    }
}
