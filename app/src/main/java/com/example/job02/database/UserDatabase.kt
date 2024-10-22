package com.example.jon02.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Room.databaseBuilder
import com.example.job02.Models.UserProfile
import com.example.job02.dao.UserProfileDao
import kotlin.concurrent.Volatile


@Database(entities = [UserProfile::class], version = 1)
 abstract class UserDatabase : RoomDatabase(){
     abstract  fun _userProfileDao(): UserProfileDao

     companion object{
         @Volatile
         private var INSTANCE:UserDatabase?=null
            fun getDatabase(context: Context):UserDatabase{

                return  INSTANCE?: synchronized(this){
                    val instance=Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_profile_database"

                    ).build()
                   INSTANCE =instance
                    instance
                }
            }
     }


}