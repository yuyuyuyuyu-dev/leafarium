package dev.yuyuyuyuyu.leafarium.ui.tree

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors

@Composable
fun FollowButton(
    modifier: Modifier = Modifier,
    initiallyFollowing: Boolean = false,
) {
    var following by remember { mutableStateOf(initiallyFollowing) }
    val padding = PaddingValues(horizontal = 16.dp)
    if (following) {
        OutlinedButton(
            onClick = { following = false },
            modifier = modifier.heightIn(min = 36.dp),
            contentPadding = padding,
            border = BorderStroke(1.dp, LeafariumColors.Outline),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = LeafariumColors.Ink),
        ) {
            Text("Following")
        }
    } else {
        Button(
            onClick = { following = true },
            modifier = modifier.heightIn(min = 36.dp),
            contentPadding = padding,
            colors = ButtonDefaults.buttonColors(containerColor = LeafariumColors.Ink, contentColor = LeafariumColors.Surface),
        ) {
            Text("Follow")
        }
    }
}
