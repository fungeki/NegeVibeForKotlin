package com.ranuskin.ranloock.zibro.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatMessage
import com.ranuskin.ranloock.zibro.R

class ChatEventAdapter(): RecyclerView.Adapter<ChatEventViewHolder>(){

    fun initChat(){
        var mList = mutableListOf<ChatMessage>()

    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChatEventViewHolder {
        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(R.layout.row_general_chat_list, p0, false)
        return ChatEventViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(p0: ChatEventViewHolder, p1: Int) {

    }

}

class ChatEventViewHolder(v: View): RecyclerView.ViewHolder(v){

}