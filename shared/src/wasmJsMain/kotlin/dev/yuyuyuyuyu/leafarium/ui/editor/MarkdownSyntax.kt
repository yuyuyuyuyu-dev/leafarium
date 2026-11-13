package dev.yuyuyuyuyu.leafarium.ui.editor

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.FormatListBulleted
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.FormatBold
import androidx.compose.material.icons.rounded.FormatItalic
import androidx.compose.material.icons.rounded.FormatQuote
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.Title
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

enum class MarkdownSyntax(
    val label: String,
    val icon: ImageVector,
    val prefix: String,
    val suffix: String = "",
    val startsLine: Boolean = false,
) {
    Heading("Heading", Icons.Rounded.Title, "## ", startsLine = true),
    Bold("Bold", Icons.Rounded.FormatBold, "**", "**"),
    Italic("Italic", Icons.Rounded.FormatItalic, "*", "*"),
    Code("Code", Icons.Rounded.Code, "`", "`"),
    Link("Link", Icons.Rounded.Link, "[", "](https://)"),
    BulletedList("Bulleted list", Icons.AutoMirrored.Rounded.FormatListBulleted, "- ", startsLine = true),
    Quote("Quote", Icons.Rounded.FormatQuote, "> ", startsLine = true),
}

fun TextFieldValue.applying(syntax: MarkdownSyntax): TextFieldValue {
    val start = if (syntax.startsLine) text.lastIndexOf('\n', selection.min - 1) + 1 else selection.min
    val end = if (syntax.startsLine) start else selection.max
    val updated = text.substring(0, start) + syntax.prefix + text.substring(start, end) + syntax.suffix + text.substring(end)
    val shift = syntax.prefix.length
    return copy(text = updated, selection = TextRange(selection.min + shift, selection.max + shift))
}
