package com.example.chitchat.feature.auth.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.chitchat.R
import com.example.chitchat.feature.auth.signin.SignInState

@Composable
fun SignUpScreen(navController: NavController){
    val viewModel : SignUpViewModel = hiltViewModel()
    val uiState=  viewModel.state.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    val context = LocalContext.current

    LaunchedEffect(key1 = uiState.value) {
        when(uiState.value){
            is SignUpState.Success -> {
                navController.navigate("Home")
            }
            is SignUpState.Error -> {
                Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }



    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it) // Apply padding from Scaffold
        ) {
            // 1. Background Image
            Image(
                painter = painterResource(id = R.drawable.bg), // Your background image
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Crop the image to fill the screen
            )

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Welcome", color = Color.White,
                    modifier = Modifier.padding(top = 60.dp),
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(top = 150.dp)
                    .padding(16.dp)
                    .background(color = Color.Transparent),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {


                Image(
                    painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                    modifier = Modifier
                        .size(150.dp)
                        .background(color = Color.White)
                )
                Spacer(modifier = Modifier.size(30.dp))

                OutlinedTextField(
                    value = name, onValueChange = {name=it},
                    placeholder = { Text(text = "name", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Full Name", color = Color.White) },
                    shape = RoundedCornerShape(20),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,      // Color when typing
                        unfocusedTextColor = Color.White,    // Color when not focused
                        cursorColor = Color.White,           // Blinking cursor color
                        focusedBorderColor = Color.White,    // Border color when focused
                        unfocusedBorderColor = Color.LightGray // Border color when not focused
                    ))
                Spacer(modifier = Modifier.size(10.dp))


                OutlinedTextField(
                    value = email, onValueChange = {email=it},
                    placeholder = { Text(text = "Email", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Email", color = Color.White) },
                    shape = RoundedCornerShape(20),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,      // Color when typing
                        unfocusedTextColor = Color.White,    // Color when not focused
                        cursorColor = Color.White,           // Blinking cursor color
                        focusedBorderColor = Color.White,    // Border color when focused
                        unfocusedBorderColor = Color.LightGray // Border color when not focused
                    ))

                Spacer(modifier = Modifier.size(10.dp))



                OutlinedTextField(
                    value = password, onValueChange = {password=it},
                    placeholder = { Text(text = "password", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text(text = "Password", color = Color.White) },
                    shape = RoundedCornerShape(20),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,      // Color when typing
                        unfocusedTextColor = Color.White,    // Color when not focused
                        cursorColor = Color.White,           // Blinking cursor color
                        focusedBorderColor = Color.White,    // Border color when focused
                        unfocusedBorderColor = Color.LightGray // Border color when not focused
                    ))




                OutlinedTextField(
                    value = confirmPassword, onValueChange = {confirmPassword=it},
                    placeholder = { Text(text = "confirmPassword", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "confirmPassword", color = Color.White) },
                    shape = RoundedCornerShape(20),
                    visualTransformation = PasswordVisualTransformation(),
                    isError = password.isNotEmpty()&& confirmPassword.isNotEmpty() && password != confirmPassword,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,      // Color when typing
                        unfocusedTextColor = Color.White,    // Color when not focused
                        cursorColor = Color.White,           // Blinking cursor color
                        focusedBorderColor = Color.White,    // Border color when focused
                        unfocusedBorderColor = Color.LightGray // Border color when not focused
                    )


                )



                Spacer(modifier = Modifier.size(20.dp))

                if(uiState.value==SignUpState.Loading){
                    CircularProgressIndicator(color = Color.White)
                }
                else{

                Button(onClick = {viewModel.SignUp(name, email, password, confirmPassword)},
                    modifier = Modifier.fillMaxWidth(),
                    colors =   ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    enabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && password == confirmPassword
                ) {
                    Text(text = "Sign Up")
                }

                TextButton(onClick = {navController.popBackStack()}) {
                    Text(text = "Already have an account? Sign In", color = Color.LightGray)
                }
                }
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview(){
    SignUpScreen(navController = NavController(LocalContext.current))
}