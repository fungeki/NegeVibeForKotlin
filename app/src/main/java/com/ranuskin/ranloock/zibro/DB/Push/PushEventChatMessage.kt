package com.ranuskin.ranloock.zibro.DB.Push

import com.google.firebase.firestore.FirebaseFirestore
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatChannel
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatMessage

fun pushEventChatMessage(chatid: String,chatMessage: ChatMessage,completion: (Boolean)->Unit){
//    // Access a Cloud Firestore instance from your Activity
    val db = FirebaseFirestore.getInstance().collection("chats")
    val chatRef = db.document(chatid)
    val chatMessageMap = chatMessage.toMap()
    var messageUpdate = HashMap<String, Any>()
    messageUpdate["last_message"] = chatMessage.message
    messageUpdate["message_time"] = chatMessage.time


    chatRef.update(messageUpdate).addOnSuccessListener {
        chatRef.collection("messages").document().set(chatMessageMap).addOnSuccessListener {
            completion(true)
        }.addOnFailureListener {
            completion(false)
        }
    }.addOnFailureListener {
        completion(false)
    }

}