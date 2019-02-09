package com.ranuskin.ranloock.zibro.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.Objects.MyEvent
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import com.ranuskin.ranloock.zibro.Objects.allMy
import com.ranuskin.ranloock.zibro.Objects.myEvent01
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_my_events.view.*



class MyEventsAdapter: RecyclerView.Adapter<MyEventsViewHolder>(){




    private lateinit var mEvents: List<ZibroEvent>


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyEventsViewHolder {
        var layoutInflater = LayoutInflater.from(p0.context)
        var cell = layoutInflater.inflate(com.ranuskin.ranloock.zibro.R.layout.row_my_events, p0, false)
        return MyEventsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        mEvents = EventsLibrary.getAllEvents()
        return mEvents.size
    }

    override fun onBindViewHolder(p0: MyEventsViewHolder, p1: Int) {

      val model = mEvents[p1]
       // val model2 = allMy[p1]
   val ref = p0.itemView
       Picasso.get().load(model.images[0].link).placeholder(com.ranuskin.ranloock.zibro.R.drawable.zebra).into(ref.my_events_imageview)
      ref.titleEventTextView.text = model.title
    //   ref.numberOfParticipantsTextView.text = model2.ParticipantsNum


    }


}

class MyEventsViewHolder(v: View): RecyclerView.ViewHolder(v){}