package dev.yuyuyuyuyu.leafarium.ui.editor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.leafarium.theme.LeafariumColors

@Composable
fun LeafTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle.copy(color = LeafariumColors.Ink),
        cursorBrush = SolidColor(LeafariumColors.Primary),
        modifier =
            modifier
                .fillMaxWidth()
                .semantics { contentDescription = placeholder },
        decorationBox = { innerTextField ->
            Box(Modifier.padding(vertical = 8.dp)) {
                if (value.text.isEmpty()) {
                    Text(placeholder, style = textStyle, color = LeafariumColors.Muted)
                }
                innerTextField()
            }
        },
    )
}
