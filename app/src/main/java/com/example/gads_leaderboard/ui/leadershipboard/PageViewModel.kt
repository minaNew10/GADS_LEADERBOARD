package com.example.gads_leaderboard.ui.leadershipboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gads_leaderboard.model.Learner
import com.example.gads_leaderboard.model.ResultWrapper
import com.example.gads_leaderboard.repository.leadershipRepository
import kotlinx.coroutines.launch

class PageViewModel :ViewModel(){
    private val _skillIqLeadershipList: MutableLiveData<ResultWrapper<List<Learner>>> =
        MutableLiveData()
    private val _learningLeadershipList: MutableLiveData<ResultWrapper<List<Learner>>> =
        MutableLiveData()

    val skillIqLeadershipList: LiveData<ResultWrapper<List<Learner>>> = _skillIqLeadershipList
    val learningLeadershipList: LiveData<ResultWrapper<List<Learner>>> = _learningLeadershipList

    init {
        loadLeaders()
    }
    fun loadLeaders() {
        viewModelScope.launch {
            _skillIqLeadershipList.postValue(leadershipRepository.getSkillIqLeadership())
            _learningLeadershipList.postValue(leadershipRepository.getLearningLeadership())
        }
    }
}

sealed class LeadershipTypes {
    object SkillIqLeaders : LeadershipTypes()
    object LearningLeaders : LeadershipTypes()
}