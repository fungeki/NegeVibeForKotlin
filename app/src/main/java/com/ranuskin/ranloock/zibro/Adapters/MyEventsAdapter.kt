package com.ranuskin.ranloock.zibro.Adapters

import android.support.v4.widget.ViewDragHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import com.ranuskin.ranloock.zibro.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_my_events.view.*

class MyEventsAdapter: RecyclerView.Adapter<MyEventsViewHolder>(){
    private lateinit var mEvents: List<ZibroEvent>
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyEventsViewHolder {
        var layoutInflater = LayoutInflater.from(p0.context)
        var cell = layoutInflater.inflate(R.layout.row_my_events, p0, false)
        return MyEventsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        mEvents = EventsLibrary.getMyEvents()
        return mEvents.size
    }

    override fun onBindViewHolder(p0: MyEventsViewHolder, p1: Int) {
        val model = mEvents[p1]
        val ref = p0.itemView
        Picasso.get().load(model.images[0].link).placeholder(R.drawable.zebra).into(ref.my_events_imageview)
    }

}

class MyEventsViewHolder(v: View): RecyclerView.ViewHolder(v){}