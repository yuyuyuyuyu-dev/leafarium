package dev.yuyuyuyuyu.leafarium.ui.editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yuyuyuyuyu.leafarium.sample.SampleForest
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors
import dev.yuyuyuyuyu.leafarium.ui.tree.Avatar

@Composable
fun ForestComposer(modifier: Modifier = Modifier) {
    var draft by remember { mutableStateOf(TextFieldValue()) }
    Column(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Avatar(SampleForest.me)
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                LeafTextField(
                    value = draft,
                    onValueChange = { draft = it },
                    placeholder = "What is growing on your Tree?",
                    textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp, lineHeight = 27.sp),
                    modifier = Modifier.heightIn(min = 56.dp),
                )
                FlowRow(
                    verticalArrangement = Arrangement.Center,
                    itemVerticalAlignment = Alignment.CenterVertically,
                ) {
                    MarkdownToolbar(
                        syntaxes = MarkdownSyntax.entries - MarkdownSyntax.Heading,
                        onApply = { draft = draft.applying(it) },
                    )
                    MarkdownBadge("Markdown", Modifier.padding(start = 8.dp))
                    Spacer(Modifier.weight(1f))
                    Button(onClick = { draft = TextFieldValue() }, enabled = draft.text.isNotBlank()) {
                        Text("Post Leaf")
                    }
                }
            }
        }
        HorizontalDivider(color = LeafariumColors.Line)
    }
}
