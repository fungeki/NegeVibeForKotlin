package com.ranuskin.ranloock.zibro.DB.Libraries

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.ranuskin.ranloock.zibro.DB.Constructors.CreateAnonUser
import com.ranuskin.ranloock.zibro.DB.Get.getUser
import com.ranuskin.ranloock.zibro.DB.Get.getUserFavorites
import com.ranuskin.ranloock.zibro.Objects.UserUtils.User
import com.ranuskin.ranloock.zibro.Objects.UserUtils.UserFavorites

object SignedInUser{

    private var currentUser: FirebaseUser? = null
    private var user: User? = null
    private var userFavorites: MutableList<UserFavorites>? = null

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
                    getUserFavorites { favorites ->
                        userFavorites = favorites
                        completion?.let { completion ->
                            completion(true)
                        }
                    }

                }

        } else {
            mAuth.signInAnonymously().addOnCompleteListener { task ->
                if (task.isSuccessful){
                    println("signed in successfully")
                    val user = mAuth.currentUser
                    this.currentUser = user
                    CreateAnonUser {
                        userFavorites = mutableListOf()
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

    fun dbUserRef(): DocumentReference{
        val db = FirebaseFirestore.getInstance()
        val uid = currentUser!!.uid
        val userRef = db.collection("users").document(uid)
        return userRef
    }
    fun dbUserReactionRef(): CollectionReference{
        val userRef = dbUserRef()
        return userRef.collection("reaction")
    }

    fun getFavorites(): MutableList<UserFavorites>{
        var mFavorites = mutableListOf<UserFavorites>()
        userFavorites?.let{ userFavorites ->
            mFavorites = userFavorites.toMutableList()
        }
        return mFavorites
    }
    fun removeFavorite(mFavorite: UserFavorites) {
        userFavorites?.let { userFavorites ->
            if (userFavorites.contains(mFavorite)) {
                userFavorites.remove(mFavorite)
            }
        }
    }

    fun addFavorite(mFavorite: UserFavorites){
        userFavorites?.let{ userFavorites ->
            if (!userFavorites.contains(mFavorite)){
                userFavorites.add(mFavorite)
            }
        }
    }
}

