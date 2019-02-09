package com.ranuskin.ranloock.zibro.Objects

import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.io.ObjectInput
import java.io.Serializable

class MyEvent(
    val id: Int,
    val images: List<EventImage>,
    val title: String,
    val ParticipantsNum: Int
) : Serializable{


}

class addMyEvent(images: RequestCreator, title: String, ParticipantsNum: Int){

}
var searchedEvents: MutableList<ZibroEvent> = EventsLibrary.getAllEvents()
val myEvent01 = addMyEvent(Picasso.get().load(searchedEvents[1].images[0].link),"קייטנת האגוז",57)
val myEvent02 = addMyEvent(Picasso.get().load(searchedEvents[2].images[0].link),"מרוץ הלילה הגדול",12)
val myEvent03 = addMyEvent(Picasso.get().load(searchedEvents[3].images[0].link),"שטויות במיץ",60)
val myEvent04 = addMyEvent(Picasso.get().load(searchedEvents[4].images[0].link),"צעירים לנצח",102)
val myEvent05 = addMyEvent(Picasso.get().load(searchedEvents[5].images[0].link),"רן קדימה רן",698)




var allMy = arrayListOf<addMyEvent>(myEvent01, myEvent02, myEvent03, myEvent04, myEvent05)


