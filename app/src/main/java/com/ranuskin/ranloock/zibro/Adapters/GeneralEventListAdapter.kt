package com.ranuskin.ranloock.zibro.Adapters


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import com.ranuskin.ranloock.zibro.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_general_event.view.*




class GeneralEventListAdapter(val listener: (ZibroEvent) -> Unit): RecyclerView.Adapter<GeneralEventListViewHolder>(){

    var isFavorite = false
    lateinit var mContext: Context
    private var events = EventsLibrary.getMyEvents()
    //number of items
    override fun getItemCount(): Int {
        return events.size
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GeneralEventListViewHolder {
        mContext = p0.context
        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(com.ranuskin.ranloock.zibro.R.layout.row_general_event, p0, false)
        return GeneralEventListViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: GeneralEventListViewHolder, p1: Int) {
        val model = events[p1]
        Picasso.get().load(model.images[0].link).placeholder(com.ranuskin.ranloock.zibro.R.drawable.zebra)
            .into(p0.itemView.eventImageView)
        p0.itemView.general_event_description.text = model.description
        p0.itemView.general_event_name.text = model.title

        p0.itemView.setOnClickListener {
            listener(model)

        }
        p0.itemView.general_event_add_to_favorites.setOnClickListener {
            p0.itemView.general_event_add_to_favorites.isEnabled = false
            if (isFavorite){
                val fadeOut = AnimationUtils.loadAnimation(mContext, R.anim.fade_out)
                p0.itemView.general_event_add_to_favorites.startAnimation(fadeOut)
                fadeOut.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        p0.itemView.general_event_add_to_favorites.setImageResource(R.drawable.ic_circle_favorites_hollow)
                        var fadeIn = AnimationUtils.loadAnimation(mContext, R.anim.fade_in)
                        p0.itemView.general_event_add_to_favorites.startAnimation(fadeIn)
                        fadeIn.setAnimationListener(object: Animation.AnimationListener{
                            override fun onAnimationRepeat(animation: Animation?) {
                            }

                            override fun onAnimationEnd(animation: Animation?) {
                                p0.itemView.general_event_add_to_favorites.isEnabled = true
                            }

                            override fun onAnimationStart(animation: Animation?) {
                            }

                        })

                    }

                    override fun onAnimationStart(animation: Animation?) {
                    }
                })
            } else {
                val fadeOut = AnimationUtils.loadAnimation(mContext, R.anim.fade_out)
                p0.itemView.general_event_add_to_favorites.startAnimation(fadeOut)
                fadeOut.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        p0.itemView.general_event_add_to_favorites.setImageResource(R.drawable.ic_circle_favorites_full)
                        var fadeIn = AnimationUtils.loadAnimation(mContext, R.anim.fade_in)
                        p0.itemView.general_event_add_to_favorites.startAnimation(fadeIn)
                        fadeIn.setAnimationListener(object: Animation.AnimationListener{
                            override fun onAnimationRepeat(animation: Animation?) {
                             }

                            override fun onAnimationEnd(animation: Animation?) {
                                p0.itemView.general_event_add_to_favorites.isEnabled = true
                             }

                            override fun onAnimationStart(animation: Animation?) {
                            }

                        })

                    }

                    override fun onAnimationStart(animation: Animation?) {
                    }
                })
            }
            isFavorite = !isFavorite
        }
    }


}

class GeneralEventListViewHolder(v: View): RecyclerView.ViewHolder(v){

}