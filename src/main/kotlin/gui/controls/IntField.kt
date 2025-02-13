package gui.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun intTextField(
    value: Int,
    label: String,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(4.dp),
    minValue: Int = 1,
    maxValue: Int,
) {
    var text by remember { mutableStateOf(if(value!=0) value.toString() else null ) }
    Row(
        modifier = modifier.padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text ?: "",
            onValueChange = {
                val filteredText = it.filter { char ->
                    char.isDigit() ||  (char == '-')
                }
                text = filteredText
                val newValue = filteredText.toIntOrNull()
                if (newValue != null && newValue >= minValue && newValue <= maxValue) {
                    onValueChange(newValue)
                }
            },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .weight(1f)
                .background(Color.White, shape)
                .clip(shape),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            label = { Text(label)}
        )
    }
}