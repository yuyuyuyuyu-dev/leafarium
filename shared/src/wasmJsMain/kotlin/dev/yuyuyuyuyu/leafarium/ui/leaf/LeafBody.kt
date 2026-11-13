package dev.yuyuyuyuyu.leafarium.ui.leaf

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.mikepenz.markdown.m3.Markdown
import com.mikepenz.markdown.m3.markdownColor
import com.mikepenz.markdown.m3.markdownTypography
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors

@Composable
fun LeafBody(
    markdown: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
) {
    val heading = MaterialTheme.typography.titleMedium.copy(fontSize = textStyle.fontSize * 1.2f)
    val small = MaterialTheme.typography.titleSmall
    Markdown(
        content = markdown,
        colors =
            markdownColor(
                text = LeafariumColors.Ink,
                codeBackground = LeafariumColors.Code,
                inlineCodeBackground = LeafariumColors.Chip,
                dividerColor = LeafariumColors.Line,
            ),
        typography =
            markdownTypography(
                h1 = heading.copy(fontSize = heading.fontSize * 1.15f),
                h2 = heading,
                h3 = small.copy(fontSize = textStyle.fontSize * 1.05f),
                h4 = small,
                h5 = small,
                h6 = small,
                text = textStyle,
                code = textStyle.copy(fontFamily = FontFamily.Monospace, fontSize = textStyle.fontSize * 0.88f, lineHeight = 21.sp),
                quote = textStyle.copy(color = LeafariumColors.Subtle),
                paragraph = textStyle,
                ordered = textStyle,
                bullet = textStyle,
                list = textStyle,
                textLink =
                    TextLinkStyles(
                        style = SpanStyle(color = LeafariumColors.Primary, textDecoration = TextDecoration.Underline),
                    ),
            ),
        modifier = modifier.fillMaxWidth(),
    )
}
