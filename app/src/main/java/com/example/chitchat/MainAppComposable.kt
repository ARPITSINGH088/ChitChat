package com.example.chitchat

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chitchat.feature.auth.signin.SignInScreen
import com.example.chitchat.feature.auth.signup.SignUpScreen
import com.example.chitchat.feature.home.HomeScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MainApp(){
    val navController = rememberNavController()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val startDestination = if(currentUser != null){
        "Home"
    }else{
        "Login"
    }

    NavHost(navController = navController,startDestination = startDestination)
    {
        composable("Login"){
            SignInScreen(navController)
        }
        composable("SignUp"){
            SignUpScreen(navController)

        }
        composable("Home"){
            HomeScreen(navController)
        }

    }
}