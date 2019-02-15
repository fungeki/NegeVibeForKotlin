package com.ranuskin.ranloock.zibro.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.ranuskin.ranloock.zibro.Objects.Category
import com.ranuskin.ranloock.zibro.R
import kotlinx.android.synthetic.main.spinner_category_row_layout.view.*

class GeneralEventCategoryFilterAdapter(context: Context, layout: Int ,list: ArrayList<Category>): ArrayAdapter<Category>(context, layout, list){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)

    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View{
        var mView = convertView
        if (convertView == null){
            mView = LayoutInflater.from(context).inflate(R.layout.spinner_category_row_layout, parent, false)
        }
        val model = getItem(position)
        model?.let {
            pModel ->
            mView!!.spinner_category_row_icon_imageview.setImageResource(pModel.res)
            mView!!.spinner_category_row_textview.text = pModel.toHebrew()
        }

        return mView!!
    }
}