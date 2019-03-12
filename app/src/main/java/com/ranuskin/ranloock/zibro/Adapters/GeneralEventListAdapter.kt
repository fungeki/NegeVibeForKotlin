package com.ranuskin.ranloock.zibro.Adapters


import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.DB.Update.updateFavorites
import com.ranuskin.ranloock.zibro.DB.Update.updateLikes
import com.ranuskin.ranloock.zibro.Objects.UserUtils.UserReaction
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_general_event.view.*
import java.text.SimpleDateFormat
import java.util.*




class GeneralEventListAdapter(activity: Activity,val listener: (ZibroEvent) -> Unit): RecyclerView.Adapter<GeneralEventListViewHolder>(), Filterable{

    var searchedEvents: MutableList<ZibroEvent> = EventsLibrary.getAllEvents()
    var isFavorite = false
    val mActivity = activity //meow
    private var events = EventsLibrary.getAllEvents()
    private var likes = SignedInUser.getLikes()
    //number of items
    override fun getItemCount(): Int {
        return searchedEvents.size
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GeneralEventListViewHolder {
        var layoutInflater = LayoutInflater.from(p0.context)
        var cellForRow = layoutInflater.inflate(com.ranuskin.ranloock.zibro.R.layout.row_general_event, p0, false)
        return GeneralEventListViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: GeneralEventListViewHolder, p1: Int) {
        var model = searchedEvents[p1]
        if (likes.containsKey(model.id.toString())){
            p0.itemView.general_event_like_imageview.setImageResource(com.ranuskin.ranloock.zibro.R.drawable.ic_circle_llike_full)
        }
        //date initializing
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("en_US"))
        val date = sdf.parse(model.date)
        val day = SimpleDateFormat("dd", Locale("he"))
        val dayStr = day.format(date)
        p0.itemView.row_general_event_day_textview.text = dayStr
        val month = SimpleDateFormat("MMM", Locale("he"))
        val monthStr = month.format(date)
        p0.itemView.row_general_event_month_textview.text = monthStr
        p0.itemView.row_general_event_likes_textview.text = model.likes.toString()
        //views init
        Picasso.get().load(model.images[0].link).placeholder(com.ranuskin.ranloock.zibro.R.drawable.zebra)
            .into(p0.itemView.eventImageView)
        p0.itemView.general_event_description.text = model.description
        p0.itemView.general_event_name.text = model.title

        p0.itemView.setOnClickListener {
            listener(model)

        }

        p0.itemView.general_event_like_imageview.setOnClickListener {
            updateLikes(model.id.toString()){ bool->
                println(bool)
                likes = SignedInUser.getLikes()
                if (likes.containsKey(model.id.toString())){
            p0.itemView.general_event_like_imageview.setImageResource(com.ranuskin.ranloock.zibro.R.drawable.ic_circle_llike_full)
            val newLikes = (model.likes + 1)
            mActivity.runOnUiThread {
                EventsLibrary.updateLocalLikes(p1, true)
                searchedEvents[p1].likes = newLikes
                model.likes = newLikes
                Toast.makeText(mActivity, "אהבת? גם אנחנו", Toast.LENGTH_LONG).show()
                p0.itemView.row_general_event_likes_textview.text = "$newLikes" }


        } else {
            p0.itemView.general_event_like_imageview.setImageResource(com.ranuskin.ranloock.zibro.R.drawable.ic_circle_like_hollow)
            val newLikes = (model.likes - 1)
            mActivity.runOnUiThread {
                EventsLibrary.updateLocalLikes(p1, false)
                searchedEvents[p1].likes = newLikes
                model.likes = newLikes
                Toast.makeText(mActivity, "הורדנו את הלייק", Toast.LENGTH_LONG).show()
                p0.itemView.row_general_event_likes_textview.text = "$newLikes" }

        }
    }
}

        p0.itemView.general_event_share.setOnClickListener {
            var sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "מנשמע זיברואים? יום אחד בא אלי חבר, אמר לי מוישה, בוא נלך ל" + "${model.title}")
            sendIntent.type = "text/plain"
            mActivity.startActivity(Intent.createChooser(sendIntent, "למי לשלוח?"))
        }
        p0.itemView.general_event_add_to_favorites.setOnClickListener {
            p0.itemView.general_event_add_to_favorites.isEnabled = false
            val favorite = UserReaction(model.id.toString(), model.date)
            updateFavorites(favorite){ bool ->
                println(bool)
            }
            if (isFavorite){
                val fadeOut = AnimationUtils.loadAnimation(mActivity, com.ranuskin.ranloock.zibro.R.anim.fade_out)
                p0.itemView.general_event_add_to_favorites.startAnimation(fadeOut)
                fadeOut.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        p0.itemView.general_event_add_to_favorites.setImageResource(com.ranuskin.ranloock.zibro.R.drawable.ic_circle_favorites_hollow)
                        var fadeIn = AnimationUtils.loadAnimation(mActivity, com.ranuskin.ranloock.zibro.R.anim.fade_in)
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
                val fadeOut = AnimationUtils.loadAnimation(mActivity, com.ranuskin.ranloock.zibro.R.anim.fade_out)
                p0.itemView.general_event_add_to_favorites.startAnimation(fadeOut)
                fadeOut.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        p0.itemView.general_event_add_to_favorites.setImageResource(com.ranuskin.ranloock.zibro.R.drawable.ic_circle_favorites_full)
                        var fadeIn = AnimationUtils.loadAnimation(mActivity, com.ranuskin.ranloock.zibro.R.anim.fade_in)
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
        return eventsFilterByName
    }

    fun filterByType(type: Int){
        var filteredList = mutableListOf<ZibroEvent>()
        for (model in events){
            if (model.type == type){
                filteredList.add(model)
            }
        }
        searchedEvents.clear()
        searchedEvents.addAll(filteredList)
    }

    private var eventsFilterByName = object: Filter(){
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