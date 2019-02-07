package com.ranuskin.ranloock.zibro.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import com.ranuskin.ranloock.zibro.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_general_chat_list.view.*

class ChatListAdapter(val listener: (ZibroEvent) -> Unit): RecyclerView.Adapter<ChatListViewHolder>(){

    lateinit var arr: List<ZibroEvent>
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChatListViewHolder {

        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(R.layout.row_general_chat_list, p0, false)
        return ChatListViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        arr = EventsLibrary.getAllEvents()
        return arr.size
    }

    override fun onBindViewHolder(p0: ChatListViewHolder, p1: Int) {
        val model = arr[p1]
        p0.itemView.chat_list_event_title_textview.text = model.title
        Picasso.get().load(model.images[0].link).placeholder(R.drawable.zebra)
            .into(p0.itemView.chat_list_imageview)
        p0.itemView.setOnClickListener {
            listener(model)
        }
    }

}

class ChatListViewHolder(v: View): RecyclerView.ViewHolder(v){

}