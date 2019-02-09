package com.ranuskin.ranloock.zibro.DB.Get

import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.Objects.UserUtils.UserReaction

fun getUserFavorites(completion: (MutableList<UserReaction>)->Unit) {
    var mFavorites = mutableListOf<UserReaction>()
    val userReactionRef = SignedInUser.dbUserReactionRef()
    val userFavoritesRef =  userReactionRef.document("favorites")
    userFavoritesRef.get().addOnSuccessListener {
        documentSnapshot ->
        if (documentSnapshot != null){
            val data = documentSnapshot.data
            data?.let{
                data ->
                for (field in data!!){
                    val fieldID = field.key
                    val date = field.value as String
                    val favorite = UserReaction(fieldID, date)
                    mFavorites.add(favorite)
                }
            }

        }
        completion(mFavorites)
    }
        .addOnFailureListener {
            completion(mFavorites)
        }
}