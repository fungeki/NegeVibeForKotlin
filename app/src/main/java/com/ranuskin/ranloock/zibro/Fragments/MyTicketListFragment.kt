package com.ranuskin.ranloock.zibro.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.Adapters.MyTicketsAdapter

import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_my_ticket_list.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MyTicketListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_ticket_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        my_tickets_recyclerview.layoutManager = LinearLayoutManager(context)
        my_tickets_recyclerview.adapter = MyTicketsAdapter()

    }


}
