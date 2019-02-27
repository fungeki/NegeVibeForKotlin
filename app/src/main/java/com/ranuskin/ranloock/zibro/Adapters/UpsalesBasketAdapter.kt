package com.ranuskin.ranloock.zibro.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ranuskin.ranloock.zibro.Interfaces.UpsalesDelegate
import com.ranuskin.ranloock.zibro.Objects.Upsales.UpsaleType
import com.ranuskin.ranloock.zibro.Objects.Upsales.UpsalesObj
import kotlinx.android.synthetic.main.row_upsales_add_item.view.*
import android.support.v4.content.res.TypedArrayUtils.getText


class UpsalesBasketAdapter(private val mContext: Context): RecyclerView.Adapter<UpsalesBasketViewHolder>(){


    lateinit var upsaleDelegate: UpsalesDelegate
    lateinit var upsaleLists : MutableList<UpsalesObj>

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UpsalesBasketViewHolder {
        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(com.ranuskin.ranloock.zibro.R.layout.row_upsales_add_item, p0, false)
        return UpsalesBasketViewHolder(cellForRow)
    }

    lateinit var upsalesDelegate: UpsalesDelegate

    override fun getItemCount(): Int {
        upsalesDelegate = mContext as UpsalesDelegate
        upsaleLists = mutableListOf()
        return upsaleLists.size + 1
    }

    override fun onBindViewHolder(p0: UpsalesBasketViewHolder, p1: Int) {
        val itemView = p0.itemView
        var upsaleType = UpsaleType.BEER
        //create a list of items for the spinner.
        val types = UpsaleType.values()
        val items = mutableListOf<String>()
        for (type in types){
            items.add(type.named)
        }
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, items)
        //set the spinners adapter to the previously created one.
        itemView.upsales_spinner.adapter = adapter
        itemView.upsales_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                upsaleType = UpsaleType.from(position)
                //Toast.makeText(mContext, "$mType | 22", Toast.LENGTH_SHORT).show()
            }
        }

        itemView.row_upsale_add_or_save_button.setOnClickListener{
            val priceStr = itemView.row_upsales_add_item_price_edittext.text.toString()
            val descriptionStr = itemView.row_upsales_item_description_edittext.text.toString()
            if (priceStr.count() > 0 && descriptionStr.count() > 0){
                val priceDbl = java.lang.Double.parseDouble(priceStr)
                val mUpsale = UpsalesObj(upsaleType, descriptionStr, priceDbl)
                upsaleLists.add(mUpsale)
                upsalesDelegate.addToList(mUpsale)
                //notifyDataSetChanged()
            }




        }
    }

}
class UpsalesBasketViewHolder(v: View): RecyclerView.ViewHolder(v){

}
