package com.ranuskin.ranloock.zibro.Adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatMessage
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.row_chat_other_members_messages.view.*
import kotlinx.android.synthetic.main.row_chat_signed_user_messages.view.*
import kotlinx.android.synthetic.main.row_chat_system_messages.view.*

class ChatEventAdapter(var messagesList: MutableList<ChatMessage>): RecyclerView.Adapter<ChatEventViewHolder>(){

    private val TYPE_CURRENT_USER = 1
    private val TYPE_OTHER_USER = 2
    private val TYPE_SYSTEM_MESSAGE = 3

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChatEventViewHolder {
        Log.d("Chat event adapter", "type: $p1")
        if (p1 == TYPE_CURRENT_USER){
        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(R.layout.row_chat_signed_user_messages, p0, false)
        return ChatEventViewHolder(cellForRow)
        } else if (p1 == TYPE_SYSTEM_MESSAGE){
            var layoutInflater = LayoutInflater.from(p0.context)
            var cellForRow = layoutInflater.inflate(R.layout.row_chat_system_messages, p0, false)
            return ChatEventViewHolder(cellForRow)
        }
        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(R.layout.row_chat_other_members_messages, p0, false)
        return ChatEventViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutType(position)
    }

    private fun getLayoutType(position : Int) : Int{
        val chatMessage = messagesList[position]
        if (chatMessage.uid == SignedInUser.getUID()){
            return TYPE_CURRENT_USER
        } else if (chatMessage.uid == "0"){
            return TYPE_SYSTEM_MESSAGE
        }
        return TYPE_OTHER_USER


    }

    override fun onBindViewHolder(p0: ChatEventViewHolder, position: Int) {
        val itemView = p0.itemView
        val model = messagesList[position]
        val userType = getLayoutType(position)
        when (userType) {
            TYPE_CURRENT_USER -> {
                itemView.row_chat_signed_user_message_content_textview.text = model.message
                itemView.row_chat_signed_user_message_time_textview.text = model.time
            }
            TYPE_OTHER_USER -> {
                itemView.row_chat_other_members_username_textview.text = model.userName
                itemView.row_chat_other_members_time_textview.text = model.time
                itemView.row_chat_other_member_text_textview.text = model.message
            }
            else -> itemView.row_chat_system_messages_textview.text = model.message
        }
    }

}

class ChatEventViewHolder(v: View): RecyclerView.ViewHolder(v){

}