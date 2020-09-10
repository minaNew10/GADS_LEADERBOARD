package com.example.gads_leaderboard.repository

import androidx.lifecycle.MutableLiveData
import com.example.gads_leaderboard.model.ResultWrapper
import com.example.gads_leaderboard.model.User
import com.example.gads_leaderboard.network.SubmitAPIs
import com.example.gads_leaderboard.network.submitAPI
import retrofit2.Call
import retrofit2.Response

val userRepository by lazy {
    UserRepository(submitAPI)
}

class UserRepository(
    private val webService: SubmitAPIs
) {
    fun submit(user: User, resultWrapper: MutableLiveData<ResultWrapper<Any>>) {

        webService.submit(
            user.email, user.firstName, user.lastName, user.projectLink
        ).enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful)
                    resultWrapper.postValue(ResultWrapper.Success(null))
                else
                    resultWrapper.postValue(ResultWrapper.GenericError(response.code(), null))
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                resultWrapper.postValue(ResultWrapper.NetworkError)
            }
        })

    }
}