package app.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            AppNavigation()
        }
    }

    @Composable
    fun AppNavigation(){
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "list"
        ){
            composable("list") {
                ListScreen(navController)
            }

            composable("detail/{itemId}") { backStackEntry ->

                val id = backStackEntry.arguments?.getString("itemId")

                DetailScreen(
                    itemId = id ?: "",
                    navController = navController
                )
            }
        }
    }

    @Composable
    fun ListScreen(navController: NavHostController) {
        val items = List(20) {
            ListItem(it, "Item $it")
        }

        Column(
            modifier =  Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Home List",
                //fontStyle = Bold,
                fontSize = 24.sp,
                modifier = Modifier.fillMaxWidth(),

                )
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                items(
                    items = items,
                    key = { item -> item.id }
                ) { item ->

                    Text(
                        text = item.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                navController.navigate("detail/${item.id}")
                            }
                    )
                }
            }
        }
    }

    @Composable
    fun DetailScreen(
        itemId: String,
        navController: NavController
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Detail Screen")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Item ID: $itemId")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text("Go Back")
            }
        }
    }

}
data class ListItem(
    val id: Int,
    val title: String
)