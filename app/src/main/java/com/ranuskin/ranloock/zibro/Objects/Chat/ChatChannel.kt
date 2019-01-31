package com.ranuskin.ranloock.zibro.Objects.Chat

class ChatChannel(val id: Int,
                  val name: String,
                  val lastMessage: String,
                  val messageTime: Int,
                  val messages: List<ChatMessage>){

}