package kids.dev.mykiddy.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kids.dev.mykiddy.utils.CircularText
import kids.dev.mykiddy.utils.ScreenTypes
import kids.dev.mykiddy.utils.hexToColor


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppScaffold(
    title: String,
    isLowerCase: Boolean = false,
    navController: NavHostController = NavHostController(LocalContext.current),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onScaffoldButtonClick:(String) -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = title)
                    }
                },

                modifier = Modifier.wrapContentHeight(),
                actions = {

                    CircularText(
                        text = if(isLowerCase) "a" else "A",
                        size = 40.dp,
                        backgroundColor = Color.Red,
                        textColor = Color.White
                    ){
                        onScaffoldButtonClick(ScreenTypes.ALPHABETS_SCREEN)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    CircularText(
                        text = "N",
                        size = 40.dp,
                        backgroundColor = Color.Blue,
                        textColor = Color.White
                    ){
                        onScaffoldButtonClick(ScreenTypes.NUMBERS_SCREEN)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    CircularText(
                        text = "C",
                        size = 40.dp,
                        backgroundColor = Color.Green,
                        textColor = Color.Blue
                    ){
                        onScaffoldButtonClick(ScreenTypes.COLORS_SCREEN)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    CircularText(
                        text = "W",
                        size = 40.dp,
                        backgroundColor = hexToColor("#f37735"),
                        textColor = Color.Black
                    ){
                        onScaffoldButtonClick(ScreenTypes.DAYS_SCREEN)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    CircularText(
                        text = "M",
                        size = 40.dp,
                        backgroundColor = hexToColor("#123e61"),
                        textColor = Color.White
                    ){
                        onScaffoldButtonClick(ScreenTypes.MONTHS_SCREEN)
                    }

                }
            )
        },
        scaffoldState = scaffoldState

    ) {
        content()

    }
}


