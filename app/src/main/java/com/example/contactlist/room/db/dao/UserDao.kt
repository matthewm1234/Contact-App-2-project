package com.example.contactlist.room.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contactlist.room.db.entities.UserEntity

@Dao
interface UserDao {
    @Query ("SELECT * FROM user WHERE email = :mEmail AND password = :mPassword ")
    fun selectByEmailAndPassword (mEmail : String, mPassword:String) : UserEntity
    @Query ("SELECT * FROM user WHERE email = :mEmail")
    fun verifyEmailExist (mEmail : String) : UserEntity
    @Insert
    fun insertUser(userEntity : UserEntity)
    @Delete
    fun deleteUser (userEntity : UserEntity)
    @Update
    fun updateUser (userEntity : UserEntity)
}