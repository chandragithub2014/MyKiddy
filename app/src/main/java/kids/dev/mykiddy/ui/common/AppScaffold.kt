package kids.dev.mykiddy.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kids.dev.mykiddy.R
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
                            Text(text = title/*, color = Color.Red*/)
                        }
                    },
                    modifier = Modifier.wrapContentHeight(),
                    actions = {

                        CircularText(
                            text = if (isLowerCase) "a" else "A",
                            size = 40.dp,
                            backgroundColor = Color(android.graphics.Color.parseColor("#AF1818")),
                            textColor = Color.White,
                            talkBackText = stringResource(R.string.alphabet_launch_label)
                        ) {
                            onScaffoldButtonClick(ScreenTypes.ALPHABETS_SCREEN)
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        CircularText(
                            text = "N",
                            size = 40.dp,
                            backgroundColor = Color.Blue,
                            textColor = Color.White,
                            talkBackText = stringResource(R.string.numbers_launch_label)
                        ) {
                            onScaffoldButtonClick(ScreenTypes.NUMBERS_SCREEN)
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        CircularText(
                            text = "C",
                            size = 40.dp,
                            backgroundColor = Color.Green,
                            textColor = Color.Blue,
                            talkBackText = stringResource(R.string.colors_launch_label)
                        ) {
                            onScaffoldButtonClick(ScreenTypes.COLORS_SCREEN)
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        CircularText(
                            text = "W",
                            size = 40.dp,
                            backgroundColor = hexToColor("#f37735"),
                            textColor = Color.Black,
                            talkBackText = stringResource(R.string.week_launch_names)
                        ) {
                            onScaffoldButtonClick(ScreenTypes.DAYS_SCREEN)
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        CircularText(
                            text = "M",
                            size = 40.dp,
                            backgroundColor = hexToColor("#123e61"),
                            textColor = Color.White,
                            talkBackText = stringResource(R.string.months_launch_names)
                        ) {
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


