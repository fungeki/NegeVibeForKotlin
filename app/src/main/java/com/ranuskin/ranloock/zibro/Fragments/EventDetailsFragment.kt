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
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 *
 */
class EventDetailsFragment : Fragment() {
    private var events = EventsLibrary.getAllEvents()

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
            eventDetailsLocation.text = event.locationname
          //  eventDetailsCategory.text = Category.values(Category)

            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("en_US"))
            val date = sdf.parse(event.date)

            val hebrewDate = SimpleDateFormat("EEEE d MMMM yyyy", Locale("he"))
            var hebrewDateStr = hebrewDate.format(date)
            eventDetailsEventDate.text = hebrewDateStr

            val time = SimpleDateFormat("HH:mm:ss",Locale("he"))
            val timeStr = time.format(date)
            eventDetailsTime.text = timeStr

            eventDetailsEventDescription.text = event.description
            eventDetailsPrice.text = " מחיר :  ${event.price} ₪"

            Picasso.get().load(event.images[0].link).placeholder(R.drawable.zebra)
                .into(eventDetailsImage)


        }

    }


}
