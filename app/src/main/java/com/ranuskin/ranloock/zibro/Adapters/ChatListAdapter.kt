package com.ranuskin.ranloock.zibro.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.row_general_chat_list.view.*

class ChatListAdapter(): RecyclerView.Adapter<ChatListViewHolder>(){

    lateinit var arr: List<ZibroEvent>
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChatListViewHolder {

        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(R.layout.row_general_chat_list, p0, false)
        return ChatListViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        arr = EventsLibrary.getMyEvents()
        return arr.size
    }

    override fun onBindViewHolder(p0: ChatListViewHolder, p1: Int) {
        p0.itemView.chat_list_event_title_textview.text = arr.get(p1).title
        val image =
    }

}

class ChatListViewHolder(v: View): RecyclerView.ViewHolder(v){

}