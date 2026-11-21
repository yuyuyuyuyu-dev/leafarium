package dev.yuyuyuyuyu.leafarium.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Eco
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import dev.yuyuyuyuyu.leafarium.sample.SampleForest
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors
import dev.yuyuyuyuyu.leafarium.ui.tree.Avatar
import leafarium.shared.generated.resources.Res
import leafarium.shared.generated.resources.leaf_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun SideNavigation(
    selected: Destination?,
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()).padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Wordmark(Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 20.dp))
        Column(Modifier.selectableGroup(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Destination.entries.forEach { destination ->
                SideNavigationItem(destination, destination == selected) { navigator.open(destination) }
            }
        }
        Button(
            onClick = navigator.newLeaf,
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth().height(52.dp),
        ) {
            Icon(Icons.Rounded.Eco, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
            Text("New Leaf", fontSize = 16.sp)
        }
        Spacer(Modifier.height(32.dp))
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Avatar(SampleForest.me, size = 40.dp)
            Column {
                Text(SampleForest.me.name, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                Text("@${SampleForest.me.handle}", style = MaterialTheme.typography.bodySmall, color = LeafariumColors.Muted)
            }
        }
    }
}

@Composable
fun Wordmark(modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Image(
            painter = painterResource(Res.drawable.leaf_icon),
            contentDescription = null,
            modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)),
        )
        Text("Leafarium", fontSize = 22.sp, fontWeight = FontWeight.Bold, letterSpacing = (-0.02).em)
    }
}

@Composable
private fun SideNavigationItem(
    destination: Destination,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(if (selected) LeafariumColors.PrimarySoft else Color.Transparent)
                .selectable(selected = selected, role = Role.Tab, onClick = onClick)
                .heightIn(min = 48.dp)
                .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val color = if (selected) LeafariumColors.PrimaryDeep else LeafariumColors.Ink
        Icon(if (selected) destination.selectedIcon else destination.icon, contentDescription = null, tint = color)
        Text(
            text = destination.label,
            fontSize = 17.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
            color = color,
        )
    }
}
