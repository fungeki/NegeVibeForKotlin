package com.ranuskin.ranloock.zibro.DB.Libraries

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.ranuskin.ranloock.zibro.DB.Constructors.CreateAnonUser
import com.ranuskin.ranloock.zibro.DB.Get.getUser
import com.ranuskin.ranloock.zibro.DB.Get.getUserFavorites
import com.ranuskin.ranloock.zibro.DB.Get.getUserLikes
import com.ranuskin.ranloock.zibro.Objects.UserUtils.User
import com.ranuskin.ranloock.zibro.Objects.UserUtils.UserReaction
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent

object SignedInUser{

    private var currentUser: FirebaseUser? = null
    private var user: User? = null
    private var userFavorites: MutableList<UserReaction>? = null
    private var userLikes: MutableMap<String, Any?>? = null

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
                        getUserLikes { mList ->
                            userLikes = mList
                            completion?.let { completion ->
                                completion(true)
                            }
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
                        userLikes = mutableMapOf()
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

    fun getFavorites(): MutableList<UserReaction>{
        var mFavorites = mutableListOf<UserReaction>()
        userFavorites?.let{ userFavorites ->
            mFavorites = userFavorites.toMutableList()
        }
        return mFavorites
    }
    fun removeFavorite(mFavorite: UserReaction) {
        userFavorites?.let { userFavorites ->
            this.userFavorites!!.removeAll { obj -> obj.id == mFavorite.id }
        }
    }

    fun addFavorite(mFavorite: UserReaction){
        userFavorites?.let{ userFavorites ->
            this.userFavorites!!.add(mFavorite)
        }
    }

    fun getFavoritesEventDetails(): MutableList<ZibroEvent>{

        //save favorite event details in userdefaults and download only incase it doesnt exist there.
        var mList = mutableListOf<ZibroEvent>()
        userFavorites?.let{
            for (event in EventsLibrary.getAllEvents()){
                val contains = userFavorites!!.any { obj -> obj.id == event.id.toString() }
                if (contains){
                    mList.add(event)
                }
            }
        }
        return mList
    }
    fun addLike(mLike: UserReaction){
        userLikes?.let {
            userLikes!!.put(mLike.id, null)
        }
    }
    fun removeLike(mLike: UserReaction){
        userLikes?.let {
            if (userLikes!!.contains(mLike.id)){
                userLikes!!.remove(mLike.id)
            }
        }
    }

    fun getLikes(): MutableMap<String, Any?>{
        var mList = mutableMapOf<String, Any?>()
        userLikes?.let {
            mList = userLikes!!.toMutableMap()
        }
        return mList
    }

    fun addLike(id: String){
        userLikes?.let{
            userLikes!!.put(id, null)
        }
    }

    fun removeLike(id: String){
        userLikes?.let{
            if (userLikes!!.containsKey(id)){
                userLikes!!.remove(id)
            }
        }
    }


}

