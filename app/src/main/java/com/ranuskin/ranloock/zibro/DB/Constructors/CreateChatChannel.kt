package com.ranuskin.ranloock.zibro.DB.Constructors

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatChannel
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatMessage
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent

fun createChatChannel(channelName: String, completion: (Boolean)->Unit){
    val db = FirebaseFirestore.getInstance()
    val chatRef = db.collection("chats").document(channelName)
    var chatChannelMap = HashMap<String, Any>()
    chatChannelMap["event_id"] = ""
    chatChannelMap["last_message"] = "הצ׳אט ריק"
    chatChannelMap["message_time"] = ""
    val chatMessage = ChatMessage("0","0", "צ׳אט $channelName נוצר", "")
    val chatMessageMap = chatMessage.toMap()
    chatRef.set(chatChannelMap).addOnSuccessListener {
        chatRef.collection("messages").document("0").set(chatMessageMap).addOnSuccessListener {
            completion(true)
        }.addOnFailureListener {
            completion(false)
        }
    }.addOnFailureListener {
        completion(false)
    }

}