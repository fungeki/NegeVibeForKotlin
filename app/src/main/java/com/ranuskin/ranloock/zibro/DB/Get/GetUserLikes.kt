package com.ranuskin.ranloock.zibro.DB.Get

import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser

fun getUserLikes(completion: (MutableMap<String, Any?>)->Unit){
    var userLikes = mutableMapOf<String, Any?>()
    val userLikesRef = SignedInUser.dbUserReactionRef().document("likes")
    userLikesRef.get().addOnSuccessListener { documentSnapshot ->
        if (documentSnapshot != null){
            val data = documentSnapshot.data
            data?.let{ mData ->
                for (field in mData){
                    val fieldID = field.key
                    userLikes.put(fieldID, null)
                }
            }

        }
        completion(userLikes)
    }.addOnFailureListener {
        completion(userLikes)
    }
}