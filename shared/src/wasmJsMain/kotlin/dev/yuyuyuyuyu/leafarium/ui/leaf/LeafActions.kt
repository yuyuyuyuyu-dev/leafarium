package dev.yuyuyuyuyu.leafarium.ui.leaf

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChatBubbleOutline
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Repeat
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.leafarium.sample.Leaf
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors

@Composable
fun LeafActions(
    leaf: Leaf,
    onReply: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var shared by remember(leaf.id) { mutableStateOf(leaf.shared) }
    var liked by remember(leaf.id) { mutableStateOf(leaf.liked) }
    Row(modifier.offset(x = (-8).dp), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
        LeafAction(
            icon = Icons.Rounded.ChatBubbleOutline,
            label = "Reply",
            count = leaf.replyCount,
            active = false,
            modifier = Modifier.clickable(onClick = onReply),
        )
        LeafAction(
            icon = Icons.Rounded.Repeat,
            label = "Share to your Tree",
            count = leaf.shareCount - (if (leaf.shared) 1 else 0) + (if (shared) 1 else 0),
            active = shared,
            modifier = Modifier.toggleable(value = shared, role = Role.Checkbox) { shared = it },
        )
        LeafAction(
            icon = if (liked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
            label = "Like",
            count = leaf.likeCount - (if (leaf.liked) 1 else 0) + (if (liked) 1 else 0),
            active = liked,
            modifier = Modifier.toggleable(value = liked, role = Role.Checkbox) { liked = it },
        )
    }
}

@Composable
private fun LeafAction(
    icon: ImageVector,
    label: String,
    count: Int,
    active: Boolean,
    modifier: Modifier,
) {
    val color = if (active) LeafariumColors.Primary else LeafariumColors.Muted
    Row(
        modifier =
            Modifier
                .clip(CircleShape)
                .then(modifier)
                .heightIn(min = 36.dp)
                .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Icon(icon, contentDescription = label, tint = color, modifier = Modifier.size(20.dp))
        if (count > 0) {
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = color,
                fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal,
            )
        }
    }
}
