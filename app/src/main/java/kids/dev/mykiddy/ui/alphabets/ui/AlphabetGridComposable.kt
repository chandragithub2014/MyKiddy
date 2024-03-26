package kids.dev.mykiddy.ui.alphabets.ui

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kids.dev.mykiddy.ui.alphabets.ui.viewmodel.AlphabetViewModel
import java.util.Locale
import org.koin.androidx.compose.get
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.sp
import kids.dev.mykiddy.R
import kids.dev.mykiddy.domain.model.Alphabet
import kids.dev.mykiddy.ui.colorInfo.ColorInfoListComposable
import kids.dev.mykiddy.ui.common.AppScaffold
import kids.dev.mykiddy.ui.months.MonthInfoGrid
import kids.dev.mykiddy.ui.numbers.DisplayNumericGrid
import kids.dev.mykiddy.ui.weeks.DisplayWeeKInfoGrid
import kids.dev.mykiddy.utils.ScreenTypes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun AlphabetComposable(
    navController: NavHostController,
    alphabetViewModel: AlphabetViewModel = get()
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var textToSpeech by remember { mutableStateOf<TextToSpeech?>(null) }
    var isLowerCase by remember {
        mutableStateOf<Boolean>(false)
    }
    var canDisplayAlphabetList by remember { mutableStateOf<Boolean>(true) }
    var canDisplayNumberList by remember { mutableStateOf<Boolean>(false) }
    val alphabetList by remember { alphabetViewModel.alphabetList }
    var screenType by remember { mutableStateOf(ScreenTypes.ALPHABETS_SCREEN) }
    AppScaffold(
        title = "Kidz Kit",
        isLowerCase = isLowerCase,

        onScaffoldButtonClick = {receivedScreenType ->
            screenType = receivedScreenType
        }
       ) {

        LaunchedEffect(Unit) {
            textToSpeech = TextToSpeech(context) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    val result = textToSpeech?.setLanguage(Locale.US)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Language data is missing or the language is not supported.
                        Toast.makeText(
                            context,
                            "Language data is missing or the language is not supported",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(context, "Initialization Failed", Toast.LENGTH_LONG).show()
                }
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                textToSpeech?.shutdown()
            }
        }

        textToSpeech?.let { textToSpeechInstance ->
            when (screenType) {
                ScreenTypes.ALPHABETS_SCREEN -> {
                    DisplayAlphabetGrid(
                        isLowerCase,
                        alphabetViewModel,
                        textToSpeechInstance,
                        alphabetList
                    )
                }
                ScreenTypes.COLORS_SCREEN -> {
                    ColorInfoListComposable(textToSpeechInstance)
                }
                ScreenTypes.DAYS_SCREEN -> {
                    DisplayWeeKInfoGrid(textToSpeechInstance)
                }
                ScreenTypes.MONTHS_SCREEN -> {
                    MonthInfoGrid(textToSpeechInstance)
                }
                else -> {
                    DisplayNumericGrid(textToSpeechInstance)
                }
            }
        }

    }
}


@Composable
fun DisplayAlphabetGrid(
    isLowerCase: Boolean = false,
    alphabetViewModel: AlphabetViewModel,
    textToSpeech: TextToSpeech,
    alphabetList: List<Alphabet>
) {
    val alphabets = if (isLowerCase) ('a'..'z').toList() else ('A'..'Z').toList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(alphabets) { alphabet ->
            AlphabetItem(alphabet = alphabet, alphabetList) { receivedAlphabet ->
                val result =
                    alphabetViewModel.fetchWordForSelectedAlphabet(
                        receivedAlphabet.toString().uppercase(
                            Locale.getDefault()
                        )
                    )
                result?.let { alphabetInfo ->
                    textToSpeech.speak(
                        "$receivedAlphabet for ${alphabetInfo.alphabetVal}",
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
fun AlphabetItem(alphabet: Char, alphabets: List<Alphabet>, onClick: (Char) -> Unit) {
    val alphabetImage =
        alphabets.find { it.alphabetName == alphabet.toString().uppercase() }?.alphabetImage
            ?: R.drawable.ic_apple
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
            //  .padding(4.dp)
            .clickable {
                onClick(alphabet)
                isSelected = !isSelected
            },
        shape = RoundedCornerShape(2.dp),
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = alphabet.toString(),
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

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Text(
                    text = alphabet.toString().lowercase(),
                    modifier = Modifier
                        .padding(8.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            transformOrigin = TransformOrigin.Center
                        },
                    color = Color.Red
                )

            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = alphabetImage),
                    contentDescription = "Your Image"
                )

            }
        }

    }


}

@Composable
fun HorizontalSeparator(color: Color, height: Int) {

}
