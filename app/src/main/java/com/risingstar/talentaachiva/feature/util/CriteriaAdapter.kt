package com.risingstar.talentaachiva.feature.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.RowCriteriaBinding
import com.risingstar.talentaachiva.domain.data.Criteria

class CriteriaAdapter(
    private val criteriaList : ArrayList<Criteria>
) : RecyclerView.Adapter<CriteriaViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriteriaViewHolder {
        val binding = RowCriteriaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CriteriaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CriteriaViewHolder, position: Int) {
        with(holder){
            with(criteriaList[position]){
                binding.tvCriteria.text = this.name
                binding.tvNumber.text = this.weight.toString()
            }
        }
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(criteriaList[holder.adapterPosition])
        }
    }

    fun addCriteria(newCriteria: Criteria){
        this.criteriaList.add(newCriteria)
    }
    fun getCriteria(): ArrayList<Criteria> {
        return this.criteriaList
    }

    override fun getItemCount(): Int {
        return criteriaList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Criteria)
    }
}



class CriteriaViewHolder(val binding: RowCriteriaBinding): RecyclerView.ViewHolder(binding.root)