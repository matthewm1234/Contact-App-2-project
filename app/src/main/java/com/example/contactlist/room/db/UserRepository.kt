package com.example.contactlist.room.db

import android.content.Context
import com.example.contactlist.room.db.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepository (context:Context){
    private val db = UserDatabase.invoke(context)

    fun verifyUser (email:String, password : String) : UserEntity =  db.userDao().selectByEmailAndPassword(email,password)

    fun checkIfAlreadyRegistered (email:String) : UserEntity = db.userDao().verifyEmailExist(email)

    fun insertUser(user : UserEntity){
        GlobalScope.launch {
            withContext(Dispatchers.IO){
            db.userDao().insertUser(user)
        } }
    }
}