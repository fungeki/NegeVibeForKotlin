package com.ranuskin.ranloock.zibro.Objects

import com.ranuskin.ranloock.zibro.R

enum class Category(var title: String,var number: Int,var res: Int)  {
    FESTIVAL("Festival",1, R.drawable.ic_category_sport),
    MOVIE("Movie",2, R.drawable.ic_category_movies),
    MUSIC("Music",3, R.drawable.ic_category_music),
    FOOD("Food",4, R.drawable.ic_category_foods),
    GENERAL_EVENTS("GeneralEvents",5, R.drawable.ic_category_events),
    CONCERTS("Concerts",6, R.drawable.ic_category_sport),
    SPORT("Sport",7, R.drawable.ic_category_sport),
    PUBS("Pubs",8, R.drawable.ic_category_pubs),
    PARTIES("Parties",9, R.drawable.ic_category_sport);
    companion object {
        fun getList(): ArrayList<Category>{
            return enumValues<Category>().toCollection(ArrayList())
        }
    }
    fun toHebrew(): String{
        return when(this){

            FESTIVAL -> "פסטיבל"
            MOVIE -> "סרטים"
            MUSIC -> "מוסיקה"
            FOOD -> "אוכל"
            GENERAL_EVENTS -> "אירועים"
            CONCERTS -> "הופעות"
            SPORT -> "ספורט"
            PUBS -> "פאבים"
            PARTIES -> "מסיבות"
        }
    }

}