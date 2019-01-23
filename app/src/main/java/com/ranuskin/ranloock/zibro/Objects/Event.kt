package com.ranuskin.ranloock.zibro.Objects

class ZibroEvent(var id: Int, var title: String, var description: String, var organizerid: Int,
            var type: Int, var price: Int, var date: String, var datedescription: String,
            var locationname: String, var status: Int, var logo: String) {

    override fun toString(): String {
        return "Event(id=$id, title='$title', description='$description', organizerid=$organizerid, type=$type, price=$price, date='$date', datedescription='$datedescription', locationname='$locationname', status=$status, logo='$logo')"
    }

}