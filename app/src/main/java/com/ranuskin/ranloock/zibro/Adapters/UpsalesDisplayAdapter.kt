package com.ranuskin.ranloock.zibro.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.Objects.Upsales.UpsalesObj
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.row_upsales_listing.view.*

class UpsalesDisplayAdapter(val upsalesList: MutableList<UpsalesObj>): RecyclerView.Adapter<UpsalesViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UpsalesViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val cell = inflater.inflate(R.layout.row_upsales_listing, p0, false)
        return UpsalesViewHolder(cell)
    }

    override fun getItemCount(): Int {
       return upsalesList.size
    }

    override fun onBindViewHolder(p0: UpsalesViewHolder, p1: Int) {
        val model = upsalesList[p1]
        p0.itemView.row_upsales_listing_upsale_textview.text = model.titleStr
    }

}

class UpsalesViewHolder(v: View):RecyclerView.ViewHolder(v){

}