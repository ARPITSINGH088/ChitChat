package com.example.chitchat.feature.home



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen (navController: NavController) {
    val viewModel : HomeViewModel = hiltViewModel()
    // channels is a State<List<YourChannelClass>>
    val channels = viewModel.channels.collectAsState()

    Scaffold(
        floatingActionButton = {
            Box(modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Magenta.copy(alpha = 0.3f))
                .clickable {
                    navController.navigate("CreateChannel")
                }){
                Text(text = " + ",
                    color = Color.White,
                    modifier = Modifier.padding(16.dp))
            }
        }
    ){
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            LazyColumn {
                // CORRECTED: Pass the list directly to items()
                items(channels.value) { channel ->
                    // Now 'channel' is an actual channel object, not an Int
                    Column {
                        Text(text = channel.name,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth()
                                .padding(8.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.Magenta.copy(alpha = 0.3f))
                                .clickable {
                                    navController.navigate("Chat/${channel.id}"){
                                }}
                                .padding(16.dp)
                        )


                    }
                }
            }
        }
    }
}

