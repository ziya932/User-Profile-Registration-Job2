package com.example.job02.viewModel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.job02.Models.UserProfile
import com.example.job02.repository.UserProfileRepository
import com.example.jon02.database.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserProfileViewModel (application: Application): AndroidViewModel(application){

    private val repository: UserProfileRepository


    init{
         val userProfileDao = UserDatabase.getDatabase(application)._userProfileDao()

        repository= UserProfileRepository(userProfileDao)
    }

    fun getUserProfiles(): LiveData<List<UserProfile>> {
        return repository.getUserProfiles()
    }
    fun insertUserProfile(userProfile: UserProfile) {
       viewModelScope.launch (Dispatchers.IO){
           repository.insert(userProfile)
       }

    }
    fun updateUserProfile(userProfile: UserProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(userProfile)
        }
    }
    fun deleteUserProfile(userProfile: UserProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(userProfile)
        }
    }

}