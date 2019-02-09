package com.ranuskin.ranloock.zibro.DB.Constructors

import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import java.sql.Timestamp

fun createLikesList(event: ZibroEvent, completion: (Boolean)->Unit){
    val userLikesRef = SignedInUser.dbUserReactionRef().document("likes")
    val id = event.id.toString()
    val like = HashMap<String, Any>()
    like[id] = Timestamp(System.currentTimeMillis()).toString()
    userLikesRef.set(like).addOnSuccessListener {
        completion(true)
    }.addOnFailureListener {
        completion(false)
    }
}
fun createLikesList(id: String, completion: (Boolean)->Unit){
    val userLikesRef = SignedInUser.dbUserReactionRef().document("likes")
    val like = HashMap<String, Any>()
    like[id] = Timestamp(System.currentTimeMillis()).toString()
    userLikesRef.set(like).addOnSuccessListener {
        completion(true)
    }.addOnFailureListener {
        completion(false)
    }
}