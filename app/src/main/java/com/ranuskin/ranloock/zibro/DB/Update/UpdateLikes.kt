package com.ranuskin.ranloock.zibro.DB.Update

import com.google.firebase.firestore.FieldValue
import com.ranuskin.ranloock.zibro.DB.Constructors.createLikesList
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import java.sql.Timestamp

fun updateLikes(id: String,completion: (Boolean) -> Unit){
    val mList = SignedInUser.getLikes()
    val userLikesRef = SignedInUser.dbUserReactionRef().document("likes")
    if (mList.containsKey(id)){ //user already liked it, delete the like

        val updates = HashMap<String, Any>() //hashmap to delete field from document in Firestore
        updates[id] = FieldValue.delete() //FieldValue.delete() reflects deleting from documents
        userLikesRef.update(updates).addOnSuccessListener {
            incrementLikes(id.toInt(), false){ bool ->
                if (bool){//if successfull in decrimenting
                    SignedInUser.removeLike(id)
                    completion(true)
                }
            }
        }.addOnFailureListener {
            completion(false)
        }
    } else { //this isn't liked, add a like
        if (mList.size == 0){ //create a likes list if doesnt exist
            createLikesList(id){ bool ->
                if (bool) {//only update if successful
                    incrementLikes(id.toInt(), true) { bool2 ->
                        if (bool2) {//if successfull in decrimenting
                            SignedInUser.addLike(id)
                            completion(true)
                        }
                    }
                }
            }
        } else { //add a new like if likes exists
            val time = Timestamp(System.currentTimeMillis()).toString()
            userLikesRef.update(id, time)
                .addOnSuccessListener {
                    incrementLikes(id.toInt(), true){ bool ->
                        if (bool){//if successfull in decrimenting
                            SignedInUser.addLike(id)
                            completion(true)
                        }
                    }
                } .addOnFailureListener{
                    completion(false)
                }
        }

    }
}