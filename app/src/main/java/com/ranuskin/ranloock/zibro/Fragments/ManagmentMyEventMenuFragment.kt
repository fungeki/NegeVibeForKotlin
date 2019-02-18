package com.ranuskin.ranloock.zibro.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.Adapters.MyEventsAdapter

import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_managment_my_event_menu.*
import kotlinx.android.synthetic.main.fragment_my_events_list.*


/**
 * A simple [Fragment] subclass.
 *
 */
class ManagmentMyEventMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_managment_my_event_menu, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        managementMyEventMenuRecyclerView.layoutManager = LinearLayoutManager(context)


    }

}
