package com.ranuskin.ranloock.zibro.DB.Libraries

import com.ranuskin.ranloock.zibro.DB.Get.getEvents
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent


//declartion for singleton
object EventsLibrary{
    //initializes the var only on creation
    private lateinit var events: List<ZibroEvent>

    fun getMyEvents(completion: (List<ZibroEvent>)->Unit){
        //checks if events is initialized
        if (this::events.isInitialized) {
            completion(events)
        } else {
            // download events and returns
            getEvents { events ->
                println("finished creating events")
                this.events = events
                completion(events)
            }
        }

    }
    fun getMyEvents(): List<ZibroEvent>{
        return events
    }
}
