package dev.yuyuyuyuyu.leafarium.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Yard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yuyuyuyuyu.leafarium.sample.SampleForest
import dev.yuyuyuyuyu.leafarium.sample.Tree
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors
import dev.yuyuyuyuyu.leafarium.ui.leaf.LeafBody
import dev.yuyuyuyuyu.leafarium.ui.leaf.LeafItem
import dev.yuyuyuyuyu.leafarium.ui.navigation.Navigator
import dev.yuyuyuyuyu.leafarium.ui.tree.Avatar
import dev.yuyuyuyuyu.leafarium.ui.tree.FollowButton

private val treeSections = listOf("Leaves", "Replies", "Likes")

@Composable
fun TreeScreen(
    handle: String,
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    val tree = SampleForest.trees[handle]
    var section by remember(handle) { mutableIntStateOf(0) }
    LazyColumn(modifier) {
        item {
            ScreenHeader(
                title = tree?.name ?: "Tree",
                subtitle = tree?.let { "${it.leafCount} Leaves" },
                onBack = navigator.back,
                divider = false,
            )
        }
        if (tree == null) {
            item { EmptyState("This Tree has not been planted.") }
            return@LazyColumn
        }
        item { TreeProfile(tree) }
        item {
            PrimaryTabRow(selectedTabIndex = section, containerColor = LeafariumColors.Surface, contentColor = LeafariumColors.Ink) {
                treeSections.forEachIndexed { index, title ->
                    Tab(
                        selected = section == index,
                        onClick = { section = index },
                        text = { Text(title, fontWeight = if (section == index) FontWeight.Bold else FontWeight.Medium) },
                        unselectedContentColor = LeafariumColors.Muted,
                    )
                }
            }
        }
        val leaves = if (section == 0) SampleForest.leavesOf(handle) else emptyList()
        if (leaves.isEmpty()) {
            item { EmptyState("No ${treeSections[section].lowercase()} here yet.") }
        }
        items(leaves, key = { it.id }) { leaf -> LeafItem(leaf, navigator) }
    }
}

@Composable
private fun TreeProfile(tree: Tree) {
    Column {
        Box(Modifier.fillMaxWidth().height(160.dp).background(LeafariumColors.Banner)) {
            Avatar(
                tree = tree,
                size = 104.dp,
                modifier =
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 24.dp)
                        .offset(y = 52.dp)
                        .border(4.dp, LeafariumColors.Surface, CircleShape),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().height(60.dp).padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            if (tree == SampleForest.me) {
                OutlinedButton(onClick = {}) { Text("Edit Tree", color = LeafariumColors.Ink) }
            } else {
                FollowButton()
            }
        }
        Column(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            TreeIdentity(tree)
        }
    }
}

@Composable
private fun TreeIdentity(tree: Tree) {
    Column {
        Text(tree.name, style = MaterialTheme.typography.headlineSmall)
        Text("@${tree.handle}", style = MaterialTheme.typography.bodyMedium, color = LeafariumColors.Muted)
    }
    if (tree.bio.isNotBlank()) {
        LeafBody(tree.bio)
    }
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Icon(Icons.Outlined.Yard, contentDescription = null, tint = LeafariumColors.Muted)
        Text("Planted in ${tree.plantedIn}", style = MaterialTheme.typography.bodyMedium, color = LeafariumColors.Muted)
    }
    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        TreeCount(tree.followingCount, "Following")
        TreeCount(tree.followerCount, "Followers")
    }
}

@Composable
private fun TreeCount(
    count: Int,
    label: String,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(count.toString(), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Text(label, style = MaterialTheme.typography.bodyMedium, color = LeafariumColors.Muted)
    }
}
