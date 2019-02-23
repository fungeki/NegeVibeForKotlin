package com.ranuskin.ranloock.zibro.DB.Constructors

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatChannel
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatMessage
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent

fun createChatChannel(chatMessage: ChatMessage,chatChannel: ChatChannel, completion: (Boolean)->Unit){
    val db = FirebaseFirestore.getInstance()
    val chatRef = db.collection("chats").document(chatChannel.id)
    var chatChannelMap = HashMap<String, Any>()
    chatChannelMap["event_id"] = chatChannel.eventid
    chatChannelMap["last_message"] = chatChannel.lastMessage
    chatChannelMap["message_time"] = chatChannel.messageTime
    val chatMessageMap = chatMessage.toMap()
    chatRef.set(chatChannelMap).addOnSuccessListener {
        Log.d(chatChannel.id,"chat was created")
        chatRef.collection("messages").document("0").set(chatMessageMap).addOnSuccessListener {
            completion(true)
        }.addOnFailureListener {
            completion(false)
        }
    }.addOnFailureListener {
        completion(false)
    }

}