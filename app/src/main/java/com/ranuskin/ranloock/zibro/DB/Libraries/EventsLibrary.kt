package com.ranuskin.ranloock.zibro.DB.Libraries

import com.ranuskin.ranloock.zibro.DB.Get.getEvents
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent

object EventsLibrary{
    private lateinit var events: List<ZibroEvent>
//    init {
//        getEvents { events->
//            this.events = events
//        }
//    }

    fun getMyEvents(completion: (List<ZibroEvent>)->Unit){
        if (this::events.isInitialized) {
            println("events were initialized")
            completion(events)
        } else {
            println("initializing events")
            getEvents { events ->
                println("finished creating events")
                this.events = events
                completion(events)
            }
        }

    }
}
