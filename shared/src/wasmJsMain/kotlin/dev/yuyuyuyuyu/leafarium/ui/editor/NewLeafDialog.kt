package dev.yuyuyuyuyu.leafarium.ui.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dev.yuyuyuyuyu.leafarium.sample.SampleForest
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors
import dev.yuyuyuyuyu.leafarium.ui.leaf.LeafBody
import dev.yuyuyuyuyu.leafarium.ui.tree.Avatar

private enum class EditorMode(
    val label: String,
) {
    Write("Write"),
    Preview("Preview"),
}

@Composable
fun NewLeafDialog(onDismiss: () -> Unit) {
    var draft by remember { mutableStateOf(TextFieldValue()) }
    var mode by remember { mutableStateOf(EditorMode.Write) }
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = LeafariumColors.Surface,
            modifier =
                Modifier
                    .widthIn(max = 640.dp)
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Column {
                NewLeafHeader(mode = mode, onModeChange = { mode = it }, onClose = onDismiss)
                HorizontalDivider(color = LeafariumColors.Line)
                NewLeafEditor(mode = mode, draft = draft, onDraftChange = { draft = it })
                HorizontalDivider(color = LeafariumColors.Line)
                NewLeafFooter(
                    canPost = draft.text.isNotBlank(),
                    onApply = { draft = draft.applying(it) },
                    onPost = onDismiss,
                )
            }
        }
    }
}

@Composable
private fun NewLeafEditor(
    mode: EditorMode,
    draft: TextFieldValue,
    onDraftChange: (TextFieldValue) -> Unit,
) {
    Row(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp).heightIn(min = 320.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Avatar(SampleForest.me)
        Box(Modifier.weight(1f)) {
            when (mode) {
                EditorMode.Write -> {
                    val focusRequester = remember { FocusRequester() }
                    LeafTextField(
                        value = draft,
                        onValueChange = onDraftChange,
                        placeholder = "Write your Leaf in Markdown",
                        textStyle =
                            MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = FontFamily.Monospace,
                                lineHeight = 26.sp,
                            ),
                        modifier = Modifier.focusRequester(focusRequester),
                    )
                    LaunchedEffect(Unit) { focusRequester.requestFocus() }
                }

                EditorMode.Preview -> {
                    if (draft.text.isBlank()) {
                        Text(
                            "Nothing to preview yet",
                            color = LeafariumColors.Muted,
                            modifier = Modifier.padding(vertical = 8.dp),
                        )
                    } else {
                        LeafBody(
                            draft.text,
                            Modifier.padding(vertical = 8.dp),
                            MaterialTheme.typography.bodyLarge.copy(fontSize = 17.sp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NewLeafHeader(
    mode: EditorMode,
    onModeChange: (EditorMode) -> Unit,
    onClose: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        IconButton(onClick = onClose) {
            Icon(Icons.Rounded.Close, contentDescription = "Close")
        }
        Text("New Leaf", style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
        Row(
            modifier =
                Modifier
                    .clip(CircleShape)
                    .background(LeafariumColors.Chip)
                    .padding(4.dp)
                    .selectableGroup(),
        ) {
            EditorMode.entries.forEach { entry ->
                val selected = entry == mode
                Text(
                    text = entry.label,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
                    color = if (selected) LeafariumColors.Ink else LeafariumColors.Subtle,
                    modifier =
                        Modifier
                            .clip(CircleShape)
                            .background(if (selected) LeafariumColors.Surface else LeafariumColors.Chip)
                            .selectable(selected = selected, role = Role.Tab) { onModeChange(entry) }
                            .padding(horizontal = 16.dp, vertical = 9.dp),
                )
            }
        }
    }
}

@Composable
private fun NewLeafFooter(
    canPost: Boolean,
    onApply: (MarkdownSyntax) -> Unit,
    onPost: () -> Unit,
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
        itemVerticalAlignment = Alignment.CenterVertically,
    ) {
        MarkdownToolbar(syntaxes = MarkdownSyntax.entries, onApply = onApply, buttonSize = 40.dp)
        Spacer(Modifier.weight(1f))
        Text(
            text = "Markdown supported",
            style = MaterialTheme.typography.labelSmall,
            color = LeafariumColors.Subtle,
            modifier = Modifier.padding(end = 12.dp),
        )
        Button(onClick = onPost, enabled = canPost, modifier = Modifier.heightIn(min = 44.dp)) {
            Text("Post Leaf")
        }
    }
}
