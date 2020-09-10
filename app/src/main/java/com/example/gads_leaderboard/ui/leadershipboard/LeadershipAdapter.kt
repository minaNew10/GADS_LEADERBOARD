package com.example.gads_leaderboard.ui.leadershipboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gads_leaderboard.R
import com.example.gads_leaderboard.databinding.ItemLearnerLayoutBinding
import com.example.gads_leaderboard.model.Learner

class LeadershipAdapter(private val type: LeadershipTypes) :
    RecyclerView.Adapter<LeadershipAdapter.LearnerViewHolder>(){


    override fun getItemCount(): Int = learners?.size ?: 0
    class LearnerViewHolder constructor(
        private val type: LeadershipTypes?,
        private val binding: ItemLearnerLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(item: Learner) {
            binding.learner = item
            binding.executePendingBindings()

            when (type) {
                is LeadershipTypes.SkillIqLeaders -> binding.descriptionTextView.setText(
                    context.getString(
                        R.string.score, item.number, item.country
                    )
                )

                is LeadershipTypes.LearningLeaders -> binding.descriptionTextView.setText(
                    context.getString(
                        R.string.hours, item.number, item.country
                    )
                )
            }
        }
    }

    private var learners: List<Learner>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnerViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLearnerLayoutBinding.inflate(layoutInflater, parent, false)

        return LearnerViewHolder(type, binding)
    }

    override fun onBindViewHolder(holder: LearnerViewHolder, position: Int) {
        learners?.get(position)?.let { holder.bind(it) }
    }

    fun submitList(learners: List<Learner>) {
        this.learners = learners
        notifyDataSetChanged()
    }
}