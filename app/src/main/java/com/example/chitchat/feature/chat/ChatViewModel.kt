package com.example.chitchat.feature.chat

import androidx.lifecycle.ViewModel
import com.example.chitchat.model.Channel
import com.example.chitchat.model.Message
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {

    private val _message = MutableStateFlow<List<Message>>(emptyList())
   val message = _message.asStateFlow()
    private val db = Firebase.database

    fun sendMessage(channelId: String, messageText: String){
        val message = Message(
            db.reference.push().key ?: UUID.randomUUID().toString(),
            Firebase.auth.currentUser?.uid ?: "",
            messageText,
            System.currentTimeMillis(),
            Firebase.auth.currentUser?.displayName ?: "",
            "https://example.com/user1.jpg",
            null

        )

       db.getReference("messages").child(channelId).push().setValue(message)
    }

    fun listenForMessages(channelId: String){
        db.getReference("messages").child(channelId).orderByChild("createdAt")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<Message>()
                    snapshot.children.forEach {
                        val message = it.getValue(Message::class.java)
                        message?.let {
                            list.add(it)
                        }
                    }
                    _message.value = list
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }




            })


    }




}