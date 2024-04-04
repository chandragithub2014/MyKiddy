package kids.dev.mykiddy.ui.numbers

import android.speech.tts.TextToSpeech
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kids.dev.mykiddy.R
import kotlinx.coroutines.delay


@Composable
fun DisplayNumericGrid(
    textToSpeech: TextToSpeech
){
    val numbers = (1..20).toList()
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {

        Text(
            text = stringResource(R.string.numbers_label),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(
                color = Color.Red,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
                // Set the desired font size here
            )
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
        ) {
            items(numbers) { number ->
                NumericItem(number = number) { receivedNumber ->
                    textToSpeech.speak(
                        receivedNumber,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        ""
                    )

                }
            }
        }
    }
}

@Composable
fun NumericItem(number: Int, onClick: (String) -> Unit) {
    println("In NumericItem $number")
    var isSelected by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isSelected) 8f else 1f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )

    LaunchedEffect(isSelected) {
        if (isSelected) {
            delay(1000) // Adjust the duration of the animation as needed
            isSelected = false
        }
    }
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .width(100.dp) // Set width
            .height(100.dp)
            //   .padding(1.dp)
            .clickable {
                onClick(number.toString())
                isSelected = !isSelected
            },
      //  .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(2.dp)),
        shape = RoundedCornerShape(2.dp),
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                modifier = Modifier
                    .padding(8.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = TransformOrigin.Center
                    },
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                    // Set the desired font size here
                )
            )
        }

    }

}