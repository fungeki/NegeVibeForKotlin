package com.ranuskin.ranloock.zibro.DB.Get

import com.google.firebase.firestore.FirebaseFirestore
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import com.ranuskin.ranloock.zibro.Objects.UserUtils.User

fun getUser(completion: (User) -> Unit){
    // Access a Cloud Firestore instance from your Activity
    val db = FirebaseFirestore.getInstance()
    val uid = SignedInUser.getUID()
    db.collection("users").document(uid).get()
        .addOnSuccessListener {
                document ->
            if (document != null) {
                val userName = document["chatName"] as String?
                completion(User(userName))
            } else {
                completion(User(null))
            }
        }
}