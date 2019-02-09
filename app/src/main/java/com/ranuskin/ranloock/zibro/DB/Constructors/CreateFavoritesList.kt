package com.ranuskin.ranloock.zibro.DB.Constructors

import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.Objects.UserUtils.UserReaction

fun createUserFavoritesList(mFavorite: UserReaction, completion: (Boolean)->Unit){
    val userFavoritesRef = SignedInUser.dbUserReactionRef().document("favorites")
    val id = mFavorite.id
    val favorite = HashMap<String, Any>()
    favorite[id] = mFavorite.date
    userFavoritesRef.set(favorite).addOnSuccessListener {
        completion(true)
    } .addOnFailureListener {
        completion(false)
    }
}