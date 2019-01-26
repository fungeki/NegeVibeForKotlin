package com.ranuskin.ranloock.zibro.Adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import com.ranuskin.ranloock.zibro.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_general_event.view.*

class GeneralEventListAdapter(val listener: (ZibroEvent) -> Unit): RecyclerView.Adapter<GeneralEventListViewHolder>(){

    private var events = EventsLibrary.getMyEvents()
    //number of items
    override fun getItemCount(): Int {
        return events.size
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GeneralEventListViewHolder {
        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(R.layout.row_general_event, p0, false)
        return GeneralEventListViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: GeneralEventListViewHolder, p1: Int) {
        val model = events[p1]
        Picasso.get().load(model.images[0].link).placeholder(R.drawable.zebra)
            .into(p0.itemView.eventImageView)
        p0.itemView.general_event_description.text = model.description
        p0.itemView.general_event_name.text = model.title

        p0.itemView.setOnClickListener {
            listener(model)

        }
    }


}

class GeneralEventListViewHolder(v: View): RecyclerView.ViewHolder(v){

}