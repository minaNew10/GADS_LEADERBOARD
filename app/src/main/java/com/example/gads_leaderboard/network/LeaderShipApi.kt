package com.example.gads_leaderboard.network

import com.example.gads_leaderboard.model.Learner
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

val leadershipAPIs by lazy {
    retrofit.create(LeadershipAPIs::class.java)
}

interface LeadershipAPIs {

    @GET("/api/skilliq")
    fun getSkillIQLeadership(): Deferred<List<Learner>>


    @GET("/api/hours")
    fun getLearningLeadership(): Deferred<List<Learner>>
}