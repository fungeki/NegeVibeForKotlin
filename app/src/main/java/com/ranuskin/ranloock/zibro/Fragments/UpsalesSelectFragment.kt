package com.ranuskin.ranloock.zibro.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.Adapters.UpsalesBasketAdapter
import com.ranuskin.ranloock.zibro.Interfaces.UpsalesDelegate
import com.ranuskin.ranloock.zibro.Objects.Upsales.UpsalesObj
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_upsales_select.*


class UpsalesSelectFragment : Fragment(), UpsalesDelegate {

    lateinit var mUpsalesList: MutableList<UpsalesObj>
    lateinit var mAdapter: UpsalesBasketAdapter

    override fun addToList(mUpsalesObj: UpsalesObj) {
        mUpsalesList.add(mUpsalesObj)
        mAdapter.notifyDataSetChanged()



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upsales_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUpsalesList = mutableListOf()
        mAdapter = UpsalesBasketAdapter(context!!, this, mUpsalesList)
        fragment_upsales_select_recyclerview.layoutManager = LinearLayoutManager(context)
        fragment_upsales_select_recyclerview.adapter = mAdapter

    }

}
