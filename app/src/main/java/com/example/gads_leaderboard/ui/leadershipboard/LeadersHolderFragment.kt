package com.example.gads_leaderboard.ui.leadershipboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.gads_leaderboard.R
import com.example.gads_leaderboard.databinding.FragmentMainBinding
import com.example.gads_leaderboard.model.Learner
import com.example.gads_leaderboard.model.ResultWrapper
import com.google.android.material.snackbar.Snackbar

class LeadersHolderFragment : Fragment(){
    private lateinit var adapter: LeadershipAdapter

    private val pageViewModel: PageViewModel by activityViewModels()

    private val type by lazy {
        val sectionNumber = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        if (sectionNumber == 1) {
            LeadershipTypes.LearningLeaders
        } else {
            LeadershipTypes.SkillIqLeaders
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): LeadersHolderFragment {
            return LeadersHolderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val binding = FragmentMainBinding.inflate(inflater, container, false)
            val view = binding.root

            adapter = LeadershipAdapter(type)
            binding.recycler.adapter = adapter

            if (type == LeadershipTypes.LearningLeaders) {
                pageViewModel.learningLeadershipList.observe(viewLifecycleOwner, {
                    bindList(view, it)
                })
            } else if (type == LeadershipTypes.SkillIqLeaders) {
                pageViewModel.skillIqLeadershipList.observe(viewLifecycleOwner, {
                    bindList(view, it)
                })
            }
            return view
        }

        private fun bindList(view: View, list: ResultWrapper<List<Learner>>) {
            when (list) {
                is ResultWrapper.Success -> list.value?.let { adapter.submitList(it) }
                is ResultWrapper.GenericError -> showErrorSnackBar(
                    view,
                    R.string.something_went_wrong
                )
                is ResultWrapper.NetworkError -> showErrorSnackBar(view, R.string.no_internet)
            }
        }

    private fun showErrorSnackBar(view: View, msgId: Int) {
        Snackbar.make(view, msgId, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                pageViewModel.loadLeaders()
            }.show()
    }

}