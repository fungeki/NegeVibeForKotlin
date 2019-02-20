package com.ranuskin.ranloock.zibro.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.Adapters.ChatEventAdapter
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatMessage
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent

import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_chat_for_event.*


class ChatForEventFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_for_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        bundle?.let { bundle ->
            val event = bundle.getSerializable("event") as ZibroEvent
            (activity as AppCompatActivity).supportActionBar?.title = "צ׳אט " + event.title
        }
        var chatList = mutableListOf<ChatMessage>()
        for (i in 1..5){
            val msg = ChatMessage("", "woof","מיאו", "12:00")
            chatList.add(msg)
        }
        var msg = ChatMessage("", "woof","meow\nmeow\nmeow\n", "12:00")
        chatList.add(msg)
        msg.message = "הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב הב"
        chatList.add(msg)
        println(chatList)
        val chatAdapter = ChatEventAdapter(chatList)
        chat_for_event_chat_recyclerview.layoutManager = LinearLayoutManager(context)
        chat_for_event_chat_recyclerview.adapter = chatAdapter
    }

}
