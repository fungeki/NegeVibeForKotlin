package com.ranuskin.ranloock.zibro.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.row_my_tickets.view.*
import java.text.SimpleDateFormat
import java.util.*

class MyTicketsAdapter(): RecyclerView.Adapter<MyTicketsViewHolder>(){
    private lateinit var mTickets: List<ZibroEvent>
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyTicketsViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val cell = inflater.inflate(R.layout.row_my_tickets, p0, false)
        return MyTicketsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        mTickets = EventsLibrary.getMyEvents()
        return mTickets.size

    }

    override fun onBindViewHolder(p0: MyTicketsViewHolder, p1: Int) {
       val model = mTickets[p1]
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale("en_US"))
        val date = sdf.parse(model.date)
        val day = SimpleDateFormat("dd", Locale("iw_IL"))
        val dayStr = day.format(date)
        p0.itemView.row_my_tickets_day_textview.text = dayStr
        val month = SimpleDateFormat("MMMM", Locale("iw_IL"))
        val monthStr = month.format(month)
        p0.itemView.row_my_tickets_month_textview.text = monthStr
        p0.itemView.row_my_tickets_event_name_textview.text = model.title
        p0.itemView.row_my_tickets_location_textview.text = model.locationname

    }

}

class MyTicketsViewHolder(v: View): RecyclerView.ViewHolder(v){}