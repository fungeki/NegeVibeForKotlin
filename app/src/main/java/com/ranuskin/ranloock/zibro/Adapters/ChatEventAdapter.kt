package com.ranuskin.ranloock.zibro.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatMessage
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.row_chat_other_members_messages.view.*
import kotlinx.android.synthetic.main.row_chat_signed_user_messages.view.*

class ChatEventAdapter(var messagesList: MutableList<ChatMessage>): RecyclerView.Adapter<ChatEventViewHolder>(){


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChatEventViewHolder {
        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(R.layout.row_chat_signed_user_messages, p0, false)
        return ChatEventViewHolder(cellForRow)

//        var layoutInflater = LayoutInflater.from(p0.context)
//        var cellForRow = layoutInflater.inflate(R.layout.row_chat_other_members_messages, p0, false)
//        return ChatEventViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    override fun onBindViewHolder(p0: ChatEventViewHolder, p1: Int) {
        val itemView = p0.itemView
        val model = messagesList[p1]
        itemView.row_chat_signed_user_message_content_textview.text = model.message
        itemView.row_chat_signed_user_message_time_textview.text = model.time

    }

}

class ChatEventViewHolder(v: View): RecyclerView.ViewHolder(v){

}