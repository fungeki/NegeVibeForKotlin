package com.ranuskin.ranloock.zibro.Objects.Chat

class ChatMessage(var uid: String,
                  var userName: String,
                  var message: String,
                  var time: String){

    fun toMap(): HashMap<String, Any>{
        var chatMessageMap = HashMap<String, Any>()
        chatMessageMap["message"] = this.message
        chatMessageMap["time"] = this.time
        chatMessageMap["uid"] = this.uid
        chatMessageMap["userName"] = this.userName
        return chatMessageMap
    }
}