package com.ranuskin.ranloock.zibro.Objects.Tickets

enum class TicketStatus(var rawValue: Int){
    VALID(0),
    PENDING(1),
    REFUSED(2),
    FROZEN(3),
    CANCELED(4),
    LIMITED(5)

}