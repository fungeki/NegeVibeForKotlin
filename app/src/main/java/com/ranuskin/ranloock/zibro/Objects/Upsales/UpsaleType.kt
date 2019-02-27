package com.ranuskin.ranloock.zibro.Objects.Upsales

enum class UpsaleType(val named: String, val value: Int){
    BEER("בירות", 0),
    MERCHANDISE("מרצ׳נדייז", 1),
    ALBUM("אלבומים", 2),
    FOOD("אוכל", 3),
    DRINK("שתיה", 4),
    SAVED_SEAT("מקום שמור", 5),
    PARKING("חניה", 6),
    OTHER("אחר",7);

    companion object {
        fun from(findValue: Int): UpsaleType = UpsaleType.values().first { it.value == findValue }
    }
}