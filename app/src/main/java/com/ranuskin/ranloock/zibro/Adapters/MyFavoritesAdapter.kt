package com.ranuskin.ranloock.zibro.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import com.ranuskin.ranloock.zibro.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_my_favorites.view.*

class MyFavoritesAdapter(): RecyclerView.Adapter<MyFavoritesViewHolder>(){
    lateinit var arr: List<ZibroEvent>
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyFavoritesViewHolder {
        val inflaterLayout = LayoutInflater.from(p0.context)
        val cell = inflaterLayout.inflate(R.layout.row_my_favorites, p0, false)
        return MyFavoritesViewHolder(cell)
    }

    override fun getItemCount(): Int {
        arr = EventsLibrary.getMyEvents()
        return arr.size
    }

    override fun onBindViewHolder(p0: MyFavoritesViewHolder, p1: Int) {
        val model = arr[p1]
        p0.itemView.row_my_favorites_description.text = model.description
        p0.itemView.row_my_favorites_title_textview.text = model.title
        Picasso.get().load(model.images[0].link).placeholder(R.drawable.zebra).into(p0.itemView.row_my_favorites_imageview)
    }

}

class MyFavoritesViewHolder(v: View): RecyclerView.ViewHolder(v){

}