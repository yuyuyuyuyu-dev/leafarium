package dev.yuyuyuyuyu.leafarium.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Eco
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.leafarium.sample.SampleForest
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors
import dev.yuyuyuyuyu.leafarium.ui.tree.Avatar
import leafarium.shared.generated.resources.Res
import leafarium.shared.generated.resources.leaf_icon
import org.jetbrains.compose.resources.painterResource

private val compactDestinations = Destination.entries - Destination.Settings

@Composable
fun CompactTopBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().height(60.dp).padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Image(
                painter = painterResource(Res.drawable.leaf_icon),
                contentDescription = "Leafarium",
                modifier = Modifier.size(32.dp).clip(RoundedCornerShape(8.dp)),
            )
            Text(title, style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f).semantics { heading() })
            Avatar(SampleForest.me, size = 32.dp)
        }
        HorizontalDivider(color = LeafariumColors.Line)
    }
}

@Composable
fun CompactBottomBar(
    selected: Destination?,
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        HorizontalDivider(color = LeafariumColors.Line)
        NavigationBar(containerColor = LeafariumColors.Ground, tonalElevation = 0.dp) {
            compactDestinations.forEach { destination ->
                val isSelected = destination == selected
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { navigator.open(destination) },
                    icon = { Icon(if (isSelected) destination.selectedIcon else destination.icon, contentDescription = null) },
                    label = { Text(destination.label, style = MaterialTheme.typography.labelSmall) },
                    colors =
                        NavigationBarItemDefaults.colors(
                            selectedIconColor = LeafariumColors.PrimaryDeep,
                            selectedTextColor = LeafariumColors.PrimaryDeep,
                            indicatorColor = LeafariumColors.PrimarySoft,
                            unselectedIconColor = LeafariumColors.Subtle,
                            unselectedTextColor = LeafariumColors.Subtle,
                        ),
                )
            }
        }
    }
}

@Composable
fun NewLeafButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        containerColor = LeafariumColors.Primary,
        contentColor = LeafariumColors.Surface,
    ) {
        Icon(Icons.Rounded.Eco, contentDescription = "New Leaf")
    }
}
