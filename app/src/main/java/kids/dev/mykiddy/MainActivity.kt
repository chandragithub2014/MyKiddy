package kids.dev.mykiddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kids.dev.mykiddy.ui.theme.MyKiddyTheme
import androidx.navigation.compose.rememberNavController
import kids.dev.mykiddy.ui.alphabets.ui.AlphabetComposable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyKiddyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //  Greeting("Android")
                    val navController = rememberNavController()
                    Navigator(navController)
                }
            }
        }
    }
}

@Composable
fun Navigator(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "alphabetListScreen") {
        composable("alphabetListScreen") { AlphabetComposable(navController = navHostController) }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyKiddyTheme {
        Greeting("Android")
    }
}