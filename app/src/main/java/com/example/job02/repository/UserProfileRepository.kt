package com.example.job02.repository

import androidx.lifecycle.LiveData
import com.example.job02.Models.UserProfile
import com.example.job02.dao.UserProfileDao

class UserProfileRepository(private val userProfileDao: UserProfileDao)  {

        fun  getUserProfiles():LiveData<List<UserProfile>> {

            return userProfileDao.getAllProfiles()
        }
      suspend fun  insert(userProfile: UserProfile){
        return userProfileDao.insert(userProfile)

      }
    suspend fun  update(userProfile: UserProfile){
       return  userProfileDao.update(userProfile)

    }

    suspend fun  delete(userProfile: UserProfile){
        return  userProfileDao.delete(userProfile)

    }


}