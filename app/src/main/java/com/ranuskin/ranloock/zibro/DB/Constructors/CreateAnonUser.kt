package com.ranuskin.ranloock.zibro.DB.Constructors

import com.google.firebase.firestore.FirebaseFirestore
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import java.text.SimpleDateFormat
import java.util.*

fun CreateAnonUser(completion: ()->Unit){
    val db = FirebaseFirestore.getInstance()
    val details = HashMap<String, Any>()
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val currentDate = sdf.format(Date())
    details["joined"] = currentDate
    val uid = SignedInUser.getUID()
    println(uid)
    db.collection("users").document(uid).set(details).addOnSuccessListener {
        println("user $uid has been added to db")
        completion()
    }

}
