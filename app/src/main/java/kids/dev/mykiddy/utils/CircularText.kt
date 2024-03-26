package kids.dev.mykiddy.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
fun CircularText(
    text: String,
    size: Dp,
    backgroundColor: Color,
    textColor: Color,
    shape: Shape = CircleShape,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color = backgroundColor, shape = shape)
            .clickable { onClick() },
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor
        )
    }
}