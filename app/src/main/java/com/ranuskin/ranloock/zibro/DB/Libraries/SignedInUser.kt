package com.ranuskin.ranloock.zibro.DB.Libraries

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ranuskin.ranloock.zibro.DB.Constructors.CreateAnonUser
import com.ranuskin.ranloock.zibro.DB.Get.getUser
import com.ranuskin.ranloock.zibro.Objects.UserUtils.User

object SignedInUser{
    private var currentUser: FirebaseUser? = null
    private var user: User? = null

    fun isUserConnected(completion: ((Boolean)->Unit)?){
        currentUser?.let {  currentUser ->
            completion?.let { completion ->
                completion(true)
            }
            return
        }
        val mAuth = FirebaseAuth.getInstance()
        val mUser = mAuth.currentUser

        if (mUser != null){
                currentUser = mUser
                getUser { user ->
                    this.user = user
                    completion?.let { completion ->
                        completion(true)
                    }
                }

        } else {
            mAuth.signInAnonymously().addOnCompleteListener { task ->
                if (task.isSuccessful){
                    println("signed in successfully")
                    val user = mAuth.currentUser
                    this.currentUser = user
                    CreateAnonUser {
                        completion?.let {
                            completion(true)
                        }
                    }

                } else {
                    println("authentication failed")
                    completion?.let {
                            completion ->   completion(false)
                    }
                }
            }
        }



        }
    fun getUID(): String{
        currentUser?.let{
            user -> return user.uid
        }
        return ""
    }

    fun getUsername(): String?{
        return user?.userName
    }
}

