package com.ranuskin.ranloock.zibro.Objects.Tickets

import com.ranuskin.ranloock.zibro.Objects.Upsales.UpsalesObj

class TicketForEvent(val id: String, var ownerUID: String, var ownerName: String,
                     var date: String, var location: String, var upsales: MutableList<UpsalesObj>,
                     var status: TicketStatus){

}