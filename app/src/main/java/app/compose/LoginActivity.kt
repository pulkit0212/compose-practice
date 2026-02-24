package app.compose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Top()
            LoginScreen()
        }
    }
}

@Composable
fun CounterApp() {

    // State
    var count by remember { mutableStateOf(0) }

    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // UI
        Text(
            text = "Count: $count",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            Modifier.padding(8.dp)
        ) {
            Button(
                onClick = {
                    count++   // State change → Recomposition
                }
            ) {
                Text("Increase")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = {
                    if (count == 0){
                        count = 0
                    }
                    else{
                        count--
                    }
                }
            ) {
                Text("Decrease")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.background(color = Color.Gray, shape = RoundedCornerShape(8.dp)).border(width = 1.dp, color = Color.Red)
        ){
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Box")
            }
        }
    }
}
@Preview(widthDp = 250, heightDp = 500)

@Composable
fun Top(){
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Login",
            //fontStyle = Bold,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth(),

        )
    }
}

@Preview
@Composable
fun LoginScreen(){
    val context = LocalContext.current

    var email by rememberSaveable() { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var password by rememberSaveable() { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        EmailField(
            email = email,
            isError = emailError,
            onEmailChange = {
                email = it
                emailError = false
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(
            password = password,
            isError = passwordError,
            onPasswordChange = {
                password = it
                passwordError = false
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                emailError = email.isBlank()
                passwordError = password.isBlank()
                if (!emailError&&!passwordError){
                    Log.d("Login", "Success")
                    // Intent to open MainActivity
                    val intent = Intent(context, TodoActivity::class.java)
                    context.startActivity(intent)

                    // Optional: Close LoginActivity
                    (context as? Activity)?.finish()
                }
            }
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun EmailField(
    email : String,
    isError : Boolean,
    onEmailChange: (String)->Unit
){
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        isError = isError,
        label = {Text("Email")},
        modifier = Modifier.fillMaxWidth()

    )
}

@Composable
fun PasswordField(
    password: String,
    isError: Boolean,
    onPasswordChange:(String)-> Unit
){
    OutlinedTextField(
        value = password,
        isError = isError,
        onValueChange = onPasswordChange,
        label = {Text("Password")},
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation()
    )
}