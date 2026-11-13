package dev.yuyuyuyuyu.leafarium.ui.editor

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.FormatListBulleted
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.FormatBold
import androidx.compose.material.icons.rounded.FormatItalic
import androidx.compose.material.icons.rounded.FormatQuote
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.Title
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors

@Composable
fun MarkdownToolbar(
    syntaxes: List<MarkdownSyntax>,
    onApply: (MarkdownSyntax) -> Unit,
    modifier: Modifier = Modifier,
    buttonSize: Dp = 36.dp,
) {
    Row(modifier) {
        syntaxes.forEach { syntax ->
            IconButton(
                onClick = { onApply(syntax) },
                modifier = Modifier.size(buttonSize),
                shape = RoundedCornerShape(8.dp),
                colors = IconButtonDefaults.iconButtonColors(contentColor = LeafariumColors.Primary),
            ) {
                Icon(syntax.icon, contentDescription = syntax.label)
            }
        }
    }
}

@Composable
fun MarkdownBadge(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier, shape = RoundedCornerShape(999.dp), color = LeafariumColors.Chip, contentColor = LeafariumColors.Subtle) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
        )
    }
}
