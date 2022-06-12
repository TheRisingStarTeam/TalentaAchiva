package com.risingstar.talentaachiva.feature.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.risingstar.talentaachiva.databinding.RowInterestBinding
import com.risingstar.talentaachiva.domain.data.Interest

class InterestAdapter(
    private val interestList : ArrayList<Interest>
) : RecyclerView.Adapter<InterestViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestViewHolder {
        val binding = RowInterestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return InterestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InterestViewHolder, position: Int) {
        with(holder){
            with(interestList[position]){
                binding.tvInterest.text = this.name
                Glide.with(binding.imgPhoto).load(this.picture).into(binding.imgPhoto)
            }
        }
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(interestList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return interestList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Interest)
    }
}

class InterestViewHolder(val binding: RowInterestBinding): RecyclerView.ViewHolder(binding.root)