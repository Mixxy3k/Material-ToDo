package com.example.materialtodo.usermanagment

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.materialtodo.components.DefaultTopBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.materialtodo.Screen
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import java.lang.Exception

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val loggedIn = remember { mutableStateOf(false) }

    //create instance of EmailPasswordActivity
    val userData = EmailPasswordActivity()

    if (userData.isUserLoggedIn()) {
        navController.navigate(Screen.HomeScreen.route)
    }

    val state = rememberOneTapSignInState()
    OneTapSignInWithGoogle(
        state = state,
        clientId = "723459265040-0sqshtkijtsr6vhimcgbr1q68104jp48.apps.googleusercontent.com",
        onTokenIdReceived = { tokenId ->
            Log.d("LOG", tokenId)
        },
        onDialogDismissed = { message ->
            Log.d("LOG", message)
        }
    )

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if(loggedIn.value) {
            navController.navigate(Screen.HomeScreen.route)
        }

        Scaffold(topBar = {
            DefaultTopBar(navController = navController, title = "Register")
        },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Welcome to Material Todo!",
                        style = MaterialTheme.typography.titleLarge,
                    )

                    OutlinedTextField(value = email, onValueChange = { newText ->
                        email = newText
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        label = {
                            Text(text = "Email")
                        }
                    )

                    OutlinedTextField(value = password, onValueChange = { newText ->
                        password = newText
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        label = {
                            Text(text = "Password")
                        }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement
                            .spacedBy(
                                space = 16.dp,
                                alignment = Alignment.CenterHorizontally
                            ),
                    ) {
                        Button(
                            onClick = {
                                if (email != null && password != null) {
                                    try {
                                        userData.auth.signInWithEmailAndPassword(email, password)
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    Log.d("W", "signInWithEmail:success")
                                                    loggedIn.value = true
                                                } else {
                                                    Log.w(
                                                        "W",
                                                        "signInWithEmail:failure",
                                                        task.exception
                                                    )
                                                    // set result to error message but only first line after ":"
                                                    Toast.makeText(
                                                        context,
                                                        task.exception!!.message.toString(),
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                    } catch (e: Exception) {
                                        Log.d("w", "Epik Fackup")
                                    }
                                }
                            },
                            modifier = Modifier
                                .padding(top = 8.dp)
                        ) {
                            Text(text = "Login")
                        }

                        Button(
                            onClick = {
                                navController.navigate(Screen.RegisterScreen.route) {
                                    popUpTo(Screen.LoginScreen.route) {
                                        inclusive = true
                                    }
                                }
                            },
                            modifier = Modifier
                                .padding(top = 8.dp)
                        ) {
                            Text(text = "Register")
                        }

                    }
                }

                Text(
                    text = "Log in with",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(top = 40.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                        ),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        state.open()
                    }) {
                        Text(text = "Google account")
                    }

                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Phone number")
                    }
                }
            }
        )
    }
}