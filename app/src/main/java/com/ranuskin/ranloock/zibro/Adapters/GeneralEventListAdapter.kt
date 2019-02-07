package com.ranuskin.ranloock.zibro.Adapters


import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.DB.Update.updateFavorites
import com.ranuskin.ranloock.zibro.Objects.UserUtils.UserFavorites
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import com.ranuskin.ranloock.zibro.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_general_event.view.*
import java.text.SimpleDateFormat
import java.util.*


class GeneralEventListAdapter(val listener: (ZibroEvent) -> Unit): RecyclerView.Adapter<GeneralEventListViewHolder>(), Filterable{

    var searchedEvents: MutableList<ZibroEvent> = EventsLibrary.getAllEvents()
    var isFavorite = false
    lateinit var mContext: Context
    private var events = EventsLibrary.getAllEvents()
    //number of items
    override fun getItemCount(): Int {
        return searchedEvents.size
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GeneralEventListViewHolder {
        mContext = p0.context
        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(com.ranuskin.ranloock.zibro.R.layout.row_general_event, p0, false)
        return GeneralEventListViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: GeneralEventListViewHolder, p1: Int) {
        val model = searchedEvents[p1]

        //date initializing
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("en_US"))
        val date = sdf.parse(model.date)
        val day = SimpleDateFormat("dd", Locale("he"))
        val dayStr = day.format(date)
        p0.itemView.row_general_event_day_textview.text = dayStr
        val month = SimpleDateFormat("MMM", Locale("he"))
        val monthStr = month.format(date)
        p0.itemView.row_general_event_month_textview.text = monthStr

        //views init
        Picasso.get().load(model.images[0].link).placeholder(com.ranuskin.ranloock.zibro.R.drawable.zebra)
            .into(p0.itemView.eventImageView)
        p0.itemView.general_event_description.text = model.description
        p0.itemView.general_event_name.text = model.title

        p0.itemView.setOnClickListener {
            listener(model)

        }

        p0.itemView.general_event_share.setOnClickListener {
            var sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "מנשמע זיברואים? יום אחד בא אלי חבר, אמר לי מוישה, בוא נלך ל" + "${model.title}")
            sendIntent.type = "text/plain"
            mContext.startActivity(Intent.createChooser(sendIntent, "למי לשלוח?"))
        }
        p0.itemView.general_event_add_to_favorites.setOnClickListener {
            p0.itemView.general_event_add_to_favorites.isEnabled = false
            val favorite = UserFavorites(model.id.toString(), model.date)
            updateFavorites(favorite){ bool ->
                println(bool)
            }
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


    override fun getFilter(): Filter {
        return eventsFilter
    }

    private var eventsFilter = object: Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var filteredList = mutableListOf<ZibroEvent>()
            if (constraint == null || constraint.length < 2){
                filteredList.addAll(events)
            } else {
                val filterPattern = constraint.toString().trim()
                for (model in events){
                    if (model.title.toLowerCase().contains(filterPattern)){
                        filteredList.add(model)
                    }
                }
            }

            var results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            searchedEvents.clear()
            results?.let { mResults ->
                searchedEvents.addAll(mResults.values as List<ZibroEvent>)
            }
        }

    }
}

class GeneralEventListViewHolder(v: View): RecyclerView.ViewHolder(v){

}