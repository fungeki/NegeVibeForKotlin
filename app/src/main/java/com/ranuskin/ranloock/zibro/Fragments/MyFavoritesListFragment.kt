package com.ranuskin.ranloock.zibro.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.Adapters.MyFavoritesAdapter

import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_my_favorites_list.*
import kotlinx.android.synthetic.main.fragment_my_favorites_list.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class MyFavoritesListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_favorites_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        my_favorites_recyclerview.layoutManager = LinearLayoutManager(context)
        my_favorites_recyclerview.adapter = MyFavoritesAdapter()

    }
}
