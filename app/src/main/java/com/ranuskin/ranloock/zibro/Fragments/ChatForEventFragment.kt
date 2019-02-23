package com.ranuskin.ranloock.zibro.Fragments


import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.EventListener
import android.widget.Toast
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.ranuskin.ranloock.zibro.Adapters.ChatEventAdapter
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.DB.Push.pushEventChatMessage
import com.ranuskin.ranloock.zibro.Objects.Chat.ChatMessage
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent

import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_chat_for_event.*
import kotlinx.android.synthetic.main.fragment_chat_for_event.view.*
import java.text.SimpleDateFormat
import java.util.*


class ChatForEventFragment : Fragment(), View.OnClickListener {

    var chatList = mutableListOf<ChatMessage>()
    var chatAdapter = ChatEventAdapter(chatList)

    override fun onClick(v: View?) {
        sendMessage()
    }

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

        chat_for_event_chat_recyclerview.layoutManager = LinearLayoutManager(context)
        chat_for_event_chat_recyclerview.adapter = chatAdapter
        val lastItem = chatAdapter.itemCount - 1
        chat_for_event_chat_recyclerview.scrollToPosition(lastItem)
        fragment_chat_for_event_send_button.setOnClickListener(this)
        listenToMessages()


    }

    private fun sendMessage(){
        val text = fragment_chat_for_event_input_message_edittext.text.toString()
        val sdf = SimpleDateFormat("HH:mm", Locale("he"))
        val currentTime = sdf.format(Date())
        if (text.count() > 0 && SignedInUser.getUsername() != null){
            fragment_chat_for_event_send_button.isEnabled = false
            val chatMessage = ChatMessage(SignedInUser.getUID(),SignedInUser.getUsername()!!,text,currentTime)
            pushEventChatMessage(chatList.size.toString(),"test",chatMessage){ bool->
                println("was the message sent: $bool")

                if (!bool){
                    Toast.makeText(context!!,"שליחת הודעה נכשלה",Toast.LENGTH_SHORT).show()
                }
                fragment_chat_for_event_send_button.isEnabled = true

            }
        } else {
            Toast.makeText(context!!,"הודעה קצרה מידי, נא להכניס הודעה ארוכה יותר",Toast.LENGTH_SHORT).show()
        }
    }

    private fun listenToMessages(){
        FirebaseFirestore.getInstance().collection("chats").document("test")
            .collection("messages").addSnapshotListener(EventListener<QuerySnapshot> { snapshots, e ->
            if (e != null) {
                Log.w("chat", "Listen failed.", e)
                return@EventListener
            }
                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED -> {
                            val data = dc.document.data
                            val msgUID = data["uid"] as String
                            val msgMessage = data["message"] as String
                            val msgTime = data["time"] as String
                            val userName = data["userName"] as String

                            val chatMessageToAdd = ChatMessage(msgUID,userName,msgMessage,msgTime)
                            chatList.add(chatMessageToAdd)


                            val lastItem = chatAdapter.itemCount - 1
                            chatAdapter.notifyDataSetChanged()
                            chat_for_event_chat_recyclerview.scrollToPosition(lastItem)


                        }

                    }
                }
        })
    }


}
