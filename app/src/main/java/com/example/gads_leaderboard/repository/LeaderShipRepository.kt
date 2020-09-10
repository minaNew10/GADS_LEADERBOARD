package com.example.gads_leaderboard.repository

import com.example.gads_leaderboard.model.Learner
import com.example.gads_leaderboard.model.ResultWrapper
import com.example.gads_leaderboard.network.LeadershipAPIs
import com.example.gads_leaderboard.network.leadershipAPIs
import com.example.gads_leaderboard.network.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

val leadershipRepository by lazy {
    LeadershipRepository(leadershipAPIs)
}

class LeadershipRepository(
    private val webService: LeadershipAPIs,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getSkillIqLeadership(): ResultWrapper<List<Learner>> {
        return safeApiCall(dispatcher) { webService.getSkillIQLeadership() }
    }

    suspend fun getLearningLeadership(): ResultWrapper<List<Learner>> {
        return safeApiCall(dispatcher) { webService.getLearningLeadership() }
    }
}