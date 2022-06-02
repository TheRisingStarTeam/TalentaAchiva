package com.risingstar.talentaachiva.feature.management

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.AssignmentRowBinding
import com.risingstar.talentaachiva.domain.data.Assignment

class AssignmentAdapter(
    private val postLists : ArrayList<Assignment>
) : RecyclerView.Adapter<AssignmentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

class AssignmentViewHolder(val binding: AssignmentRowBinding):RecyclerView.ViewHolder(binding.root)
