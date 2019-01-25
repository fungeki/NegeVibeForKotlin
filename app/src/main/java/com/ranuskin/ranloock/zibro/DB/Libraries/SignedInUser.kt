package com.ranuskin.ranloock.zibro.DB.Libraries

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object SignedInUser{
    private var currentUser: FirebaseUser? = null

    fun isUserConnected(completion: ((Boolean)->Unit)?){
        currentUser?.let {  currentUser ->
            completion?.let { completion ->
                completion(true)
            }
            return
        }
        val mAuth = FirebaseAuth.getInstance()
        val mUser = mAuth.currentUser

        mUser?.let { mUser ->
            currentUser = mUser
            completion?.let { completion ->
                completion(true)
            }
            return
        }

        mAuth.signInAnonymously().addOnCompleteListener { task ->
            if (task.isSuccessful){
                println("signed in successfully")
                val user = mAuth.currentUser
                this.currentUser = user
            } else {
                println("authentication failed")
                completion?.let {
                 completion ->   completion(false)
                }
                }
            }
        }

}

