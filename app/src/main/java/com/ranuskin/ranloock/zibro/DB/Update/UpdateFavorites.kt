package com.ranuskin.ranloock.zibro.DB.Update

import com.google.firebase.firestore.FieldValue
import com.ranuskin.ranloock.zibro.DB.Constructors.createUserFavoritesList
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.Objects.UserUtils.UserReaction





fun updateFavorites(mFavorite: UserReaction, completion: ((Boolean)->Unit)?){
    var userFavoritesList = SignedInUser.getFavorites()
    if (userFavoritesList.size == 0){ //creates a user favorites list if doesnt exist or is empty
        createUserFavoritesList(mFavorite){ bool ->
            SignedInUser.addFavorite(mFavorite)
            completion?.let{ comp ->
                comp(bool)
            }//end of let
        }//end of create favorites
    } else {
        val userReactionRef = SignedInUser.dbUserReactionRef()
        val userFavoritesRef =  userReactionRef.document("favorites") //refernece to user favorites
        val contains = userFavoritesList.any { it.id == mFavorite.id }
        if (contains){ // favorite exists, delete it
            val id = mFavorite.id
            val updates = HashMap<String, Any>() //hashmap to delete field from document in Firestore
            updates[id] = FieldValue.delete() //FieldValue.delete() reflects deleting from documents
            userFavoritesRef.update(updates).addOnCompleteListener {
                SignedInUser.removeFavorite(mFavorite)
                completion?.let{ completion ->
                    completion(true)
                }//end of success listener
            }.addOnFailureListener{
                completion?.let{ completion ->
                    completion(false)
                }
            }//end of failure listener
        } else { // favorite doesnt exist. update document. Meow!!!
            val id = mFavorite.id
            val date = mFavorite.date
            val updates = HashMap<String, Any>() //same as delete above
            updates[id] = date
            userFavoritesRef.update(updates).addOnSuccessListener { //same as above
                SignedInUser.addFavorite(mFavorite)
                completion?.let {
                    completion(true)
                }
            }.addOnFailureListener {
                completion?.let {
                    completion(false)
                }
            }
        }

    }
}
