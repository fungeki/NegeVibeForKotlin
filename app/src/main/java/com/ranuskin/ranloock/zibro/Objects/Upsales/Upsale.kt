package com.ranuskin.ranloock.zibro.Objects.Upsales

class UpsalesObj(var upsaleType: UpsaleType, var titleStr: String, var price: Double){
    override fun toString(): String {
        return "Upsale(upsaleType=$upsaleType, titleStr='$titleStr', price=$price)"
    }
    fun appearance(): String{
        return "$titleStr | $price ₪"
    }
}