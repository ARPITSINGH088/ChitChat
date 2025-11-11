package com.example.chitchat.feature.home



import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.chitchat.model.Channel
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (navController: NavController) {
    val viewModel : HomeViewModel = hiltViewModel()
    // channels is a State<List<YourChannelClass>>
    val channels = viewModel.channels.collectAsState()
    val addChannel=remember { mutableStateOf(false) }
    val sheetState= rememberModalBottomSheetState()

    Scaffold(
        floatingActionButton = {
            Box(modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Magenta.copy(alpha = 0.3f))
                .clickable {
                    addChannel.value=true
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

    if (addChannel.value){
        ModalBottomSheet(onDismissRequest = {addChannel.value=false}, sheetState = sheetState,containerColor = Color(0xFFE1BEE7)) {
            AddChannelDialog {
                viewModel.addChannel(it)
                addChannel.value=false
            }
        }
    }
}

@Composable
fun AddChannelDialog(onAddChannel: (String)-> Unit){
 val  channelName = remember {mutableStateOf("")}
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Add Channel")
        Spacer(modifier = Modifier.padding(8.dp))

        TextField(value = channelName.value, onValueChange = {channelName.value = it},
            label = {Text("Channel Name")}, singleLine = true)

        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {onAddChannel(channelName.value)}, modifier = Modifier.fillMaxWidth(),colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6A0DAD) // A nice dark purple
        )) {
            Text("Add")
        }

    }

}