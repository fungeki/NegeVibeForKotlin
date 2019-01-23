package com.ranuskin.ranloock.zibro.Adapters


import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.fragment_event_list.*

class GeneralEventListAdapter: RecyclerView.Adapter<GeneralEventListViewHolder>(){
    //number of items
    override fun getItemCount(): Int {
        return 3
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GeneralEventListViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.general_event_row, p0, false)
        return GeneralEventListViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: GeneralEventListViewHolder, p1: Int) {

    }


}

class GeneralEventListViewHolder(v: View): RecyclerView.ViewHolder(v){

}