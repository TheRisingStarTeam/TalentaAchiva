package com.risingstar.talentaachiva.feature.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.RowAssignmentBinding
import com.risingstar.talentaachiva.domain.data.Assignment

class AssignmentAdapter (
    private val assignmentList : ArrayList<Assignment>
) : RecyclerView.Adapter<AssignmentViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val binding = RowAssignmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return AssignmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        with(holder){
            with(assignmentList[position]){
                binding.tvTitleAssignment.text = this.title
            }
        }
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(assignmentList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return assignmentList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Assignment)
    }
}



class AssignmentViewHolder(val binding: RowAssignmentBinding): RecyclerView.ViewHolder(binding.root)