package com.risingstar.talentaachiva.feature.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.RowSubmissionsBinding
import com.risingstar.talentaachiva.domain.data.Submissions

class SubmissionAdapter(
    private val postList : ArrayList<Submissions>
) : RecyclerView.Adapter<SubmissionViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubmissionViewHolder {
        val binding = RowSubmissionsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SubmissionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubmissionViewHolder, position: Int) {
        with(holder){
            with(postList[position]){
                binding.tvTitleSubmission.text = this.title
            }
        }
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(postList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Submissions)
    }
}

class SubmissionViewHolder(val binding: RowSubmissionsBinding): RecyclerView.ViewHolder(binding.root)