package com.ranuskin.ranloock.zibro.DB.Update

import com.google.firebase.firestore.FirebaseFirestore
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser

fun updateUsername(named: String, completion: (() -> Unit)?){
    // Access a Cloud Firestore instance from your Activity
    val db = FirebaseFirestore.getInstance()
    val uid = SignedInUser.getUID()
    val userRef = db.collection("users").document(uid)
    userRef.update("chatName", named).addOnSuccessListener {
        completion?.let {
                completion-> completion()
        }
    }

}