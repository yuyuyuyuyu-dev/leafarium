package dev.yuyuyuyuyu.leafarium.ui.tree

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yuyuyuyuyu.leafarium.sample.Tree

@Composable
fun Avatar(
    tree: Tree,
    modifier: Modifier = Modifier,
    size: Dp = 44.dp,
) {
    Box(
        modifier =
            modifier
                .size(size)
                .clip(CircleShape)
                .background(tree.tone.background),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = tree.initials,
            color = tree.tone.content,
            fontWeight = FontWeight.Bold,
            fontSize = (size.value / 3).sp,
        )
    }
}
