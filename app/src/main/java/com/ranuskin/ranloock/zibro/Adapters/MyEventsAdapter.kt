package com.ranuskin.ranloock.zibro.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.Objects.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_general_event.view.*
import kotlinx.android.synthetic.main.row_my_events.view.*
import kotlinx.android.synthetic.main.row_my_tickets.view.*


class MyEventsAdapter: RecyclerView.Adapter<MyEventsViewHolder>(){




    private lateinit var mEvents: List<ZibroEvent>
    val myEvent01 = MyEvent(1, EventImage("https://c.pxhere.com/photos/0b/3b/photo-5329.jpg!d"), "קייטנת האגוז", 40)
    val myEvent02 = MyEvent(1, EventImage("https://cdn.pixabay.com/photo/2017/12/08/11/53/event-party-3005668_960_720.jpg"), "ערב תה מסורתי", 72)
    val myEvent03 = MyEvent(1, EventImage("https://cdn.pixabay.com/photo/2017/12/08/11/53/event-party-3005668_960_720.jpg"), "פסטיבל היפ-הופ גול", 102)
    val myEvent04 = MyEvent(1, EventImage("https://cdn.pixabay.com/photo/2017/12/08/11/53/event-party-3005668_960_720.jpg"), "כנס קיי פופ", 209)
    val myEvent05 = MyEvent(1, EventImage("https://cdn.pixabay.com/photo/2017/12/08/11/53/event-party-3005668_960_720.jpg"), "מסיבת גג איציק", 301)

    val zevelEvent = listOf(myEvent01,myEvent02,myEvent03,myEvent04,myEvent05)




    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyEventsViewHolder {
        var layoutInflater = LayoutInflater.from(p0.context)
        var cell = layoutInflater.inflate(com.ranuskin.ranloock.zibro.R.layout.row_my_events, p0, false)
        return MyEventsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        mEvents = EventsLibrary.getAllEvents()
        return zevelEvent.size
    }

    override fun onBindViewHolder(p0: MyEventsViewHolder, p1: Int) {
        val model = mEvents[p1]
        val model2 = zevelEvent[p1]

        val ref = p0.itemView
       Picasso.get().load(model2.images.link).placeholder(com.ranuskin.ranloock.zibro.R.drawable.zebra).into(ref.my_events_imageview)
        ref.titleEventTextView.text = zevelEvent[p1].title
        ref.numberOfParticipantsTextView.text = zevelEvent[p1].participantsNum.toString()
        //   ref.numberOfParticipantsTextView.text = model2.ParticipantsNum


    }


}

class MyEventsViewHolder(v: View): RecyclerView.ViewHolder(v){}
