package dev.yuyuyuyuyu.leafarium.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Forest
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Park
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Forest
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Park
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface Screen {
    data object Forest : Screen

    data object Search : Screen

    data object Notifications : Screen

    data object Settings : Screen

    data class TreeOf(
        val handle: String,
    ) : Screen

    data class LeafOf(
        val id: String,
    ) : Screen
}

enum class Destination(
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
) {
    Forest("Forest", Icons.Outlined.Forest, Icons.Rounded.Forest),
    Search("Search", Icons.Rounded.Search, Icons.Rounded.Search),
    Notifications("Notifications", Icons.Outlined.Notifications, Icons.Rounded.Notifications),
    MyTree("My Tree", Icons.Outlined.Park, Icons.Rounded.Park),
    Settings("Settings", Icons.Outlined.Settings, Icons.Rounded.Settings),
}

class Navigator(
    val openLeaf: (String) -> Unit,
    val openTree: (String) -> Unit,
    val open: (Destination) -> Unit,
    val back: () -> Unit,
    val newLeaf: () -> Unit,
)
