package com.ranuskin.ranloock.zibro.Fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.DB.Update.updateFavorites
import com.ranuskin.ranloock.zibro.DB.Update.updateLikes
import com.ranuskin.ranloock.zibro.Objects.UserUtils.UserReaction
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent

import com.ranuskin.ranloock.zibro.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_event_details.*
import kotlinx.android.synthetic.main.row_general_event.view.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 *
 */
class EventDetailsFragment : Fragment() {
    private var events = EventsLibrary.getAllEvents()
    private var likes = SignedInUser.getLikes()
    var isFavorite = false
    var searchedEvents: MutableList<ZibroEvent> = EventsLibrary.getAllEvents()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        bundle?.let {
            bundle ->
            val event = bundle.getSerializable("event") as ZibroEvent
            eventDetailsTitleEvent.text = event.title
            eventDetailsLocation.text = event.locationname
          //  eventDetailsCategory.text = Category.values(Category)

            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("en_US"))
            val date = sdf.parse(event.date)

            val hebrewDate = SimpleDateFormat("EEEE d MMMM yyyy", Locale("he"))
            var hebrewDateStr = hebrewDate.format(date)
            eventDetailsEventDate.text = hebrewDateStr

            val time = SimpleDateFormat("HH:mm:ss",Locale("he"))
            val timeStr = time.format(date)
            eventDetailsTime.text = timeStr

            eventDetailsEventDescription.text = event.description
            eventDetailsPrice.text = " מחיר :  ${event.price} ₪"

            event_details_count_likes_text_view.text = searchedEvents[0].likes.toString()

            Picasso.get().load(event.images[0].link).placeholder(R.drawable.zebra)
                .into(eventDetailsImage)


            //likes
            eventDetailLikeImage.setOnClickListener {
                updateLikes(event.id.toString()){ bool->
                    val p1:Int = 0
                    likes = SignedInUser.getLikes()
                    if (likes.containsKey(event.id.toString())){
                        eventDetailLikeImage.setImageResource(R.drawable.ic_circle_llike_full)
                        val newLikes = (event.likes + 1)
                        activity!!.runOnUiThread {
                            EventsLibrary.updateLocalLikes(p1, true)
                            event.likes = newLikes
                            event.likes = newLikes
                            Toast.makeText(activity, "אהבת? גם אנחנו", Toast.LENGTH_LONG).show()
                            event_details_count_likes_text_view.text = "$newLikes"
                        }


                    } else {
                        eventDetailLikeImage.setImageResource(R.drawable.ic_circle_like_hollow)
                        val newLikes = (event.likes - 1)
                        activity!!.runOnUiThread {
                            EventsLibrary.updateLocalLikes(p1, false)
                            searchedEvents[p1].likes = newLikes
                            event.likes = newLikes
                            Toast.makeText(activity, "הורדנו את הלייק", Toast.LENGTH_LONG).show()
                            event_details_count_likes_text_view.text = "$newLikes"}

                    }
                }
            }

            //share button
            eventDetailsShareImage.setOnClickListener {
                var sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, "מנשמע זיברואים? יום אחד בא אלי חבר, אמר לי מוישה, בוא נלך ל" + "${event.title}")
                sendIntent.type = "text/plain"
                activity!!.startActivity(Intent.createChooser(sendIntent, "למי לשלוח?"))
            }


            //favorite button
            eventDetailsAddToFavoriteImage.setOnClickListener {
                eventDetailsAddToFavoriteImage.isEnabled = false
                val favorite = UserReaction(event.id.toString(), event.date)
                updateFavorites(favorite){ bool ->
                    println(bool)
                }
                if (isFavorite){
                    val fadeOut = AnimationUtils.loadAnimation(activity, com.ranuskin.ranloock.zibro.R.anim.fade_out)
                    eventDetailsAddToFavoriteImage.startAnimation(fadeOut)
                    fadeOut.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationRepeat(animation: Animation?) {
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            eventDetailsAddToFavoriteImage.setImageResource(com.ranuskin.ranloock.zibro.R.drawable.ic_circle_favorites_hollow)
                            var fadeIn = AnimationUtils.loadAnimation(activity, com.ranuskin.ranloock.zibro.R.anim.fade_in)
                            eventDetailsAddToFavoriteImage.startAnimation(fadeIn)
                            fadeIn.setAnimationListener(object: Animation.AnimationListener{
                                override fun onAnimationRepeat(animation: Animation?) {
                                }

                                override fun onAnimationEnd(animation: Animation?) {
                                    eventDetailsAddToFavoriteImage.isEnabled = true
                                }

                                override fun onAnimationStart(animation: Animation?) {
                                }

                            })

                        }

                        override fun onAnimationStart(animation: Animation?) {
                        }
                    })
                } else {
                    val fadeOut = AnimationUtils.loadAnimation(activity, com.ranuskin.ranloock.zibro.R.anim.fade_out)
                    eventDetailsAddToFavoriteImage.startAnimation(fadeOut)
                    fadeOut.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationRepeat(animation: Animation?) {
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            eventDetailsAddToFavoriteImage.setImageResource(com.ranuskin.ranloock.zibro.R.drawable.ic_circle_favorites_full)
                            var fadeIn = AnimationUtils.loadAnimation(activity, com.ranuskin.ranloock.zibro.R.anim.fade_in)
                            eventDetailsAddToFavoriteImage.startAnimation(fadeIn)
                            fadeIn.setAnimationListener(object: Animation.AnimationListener{
                                override fun onAnimationRepeat(animation: Animation?) {
                                }

                                override fun onAnimationEnd(animation: Animation?) {
                                    eventDetailsAddToFavoriteImage.isEnabled = true
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


}
