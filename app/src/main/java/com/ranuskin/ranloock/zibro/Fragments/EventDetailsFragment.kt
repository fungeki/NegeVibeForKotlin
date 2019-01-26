package com.ranuskin.ranloock.zibro.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent

import com.ranuskin.ranloock.zibro.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_event_details.*
import kotlinx.android.synthetic.main.row_general_event.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class EventDetailsFragment : Fragment() {
    private var events = EventsLibrary.getMyEvents()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        bundle?.let {
            bundle ->
            val event = bundle.getSerializable("event") as ZibroEvent
            eventDetailsTitleEvent.text = event.title
            eventDetailsEventDate.text = event.date
            eventDetailsLocation.text = event.locationname

            Picasso.get().load(event.images[0].link).placeholder(R.drawable.zebra)
                .into(eventDetailsImage)
        }

    }


}
