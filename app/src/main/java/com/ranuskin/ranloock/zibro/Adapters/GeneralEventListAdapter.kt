package com.ranuskin.ranloock.zibro.Adapters


import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_general_event_list.*
import kotlinx.android.synthetic.main.general_event_row.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class GeneralEventListAdapter: RecyclerView.Adapter<GeneralEventListViewHolder>(){
    private var events = EventsLibrary.getMyEvents()
    //number of items
    override fun getItemCount(): Int {
        return events.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GeneralEventListViewHolder {
        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(R.layout.general_event_row, p0, false)
        return GeneralEventListViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: GeneralEventListViewHolder, p1: Int) {
        val model = events.get(p1)
        Picasso.get().load(model.images.get(0).link)
            .into(p0.itemView.eventImageView)
        p0.itemView.general_event_description.text = model.description
        p0.itemView.general_event_name.text = model.title
    }


}

class GeneralEventListViewHolder(v: View): RecyclerView.ViewHolder(v){

}