package com.ranuskin.ranloock.zibro.DB.Update

import com.google.firebase.firestore.FieldValue
import com.ranuskin.ranloock.zibro.DB.Constructors.createFavoritesList
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.Objects.UserUtils.UserFavorites

fun UpdateFavorites(mFavorite: UserFavorites,completion: ((Boolean)->Unit)?){
    var userFavoritesList = SignedInUser.getFavorites()
    if (userFavoritesList.size == 0){ //creates a user favorites list if doesnt exist or is empty
        createFavoritesList(mFavorite){ bool ->
            completion?.let{ comp ->
                comp(bool)
            }//end of let
        }//end of create favorites

    } else {
        val userReactionRef = SignedInUser.dbUserReactionRef()
        val userFavoritesRef =  userReactionRef.document("favorites") //refernece to user favorites
        if (userFavoritesList.contains(mFavorite)){ // favorite exists, delete it
            val id = mFavorite.id
            val updates = HashMap<String, Any>() //hashmap to delete field from document in Firestore
            updates[id] = FieldValue.delete() //FieldValue.delete() reflects deleting from documents
            userFavoritesRef.update(updates).addOnCompleteListener {
                completion?.let{ completion ->
                    SignedInUser.removeFavorite(mFavorite)
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