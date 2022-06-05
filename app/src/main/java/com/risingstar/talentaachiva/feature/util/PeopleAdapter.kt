package com.risingstar.talentaachiva.feature.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.RowPersonBinding
import com.risingstar.talentaachiva.domain.data.Identity

class PeopleAdapter(
    private val peopleList : ArrayList<Identity>
) : RecyclerView.Adapter<PeopleViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding = RowPersonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PeopleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        with(holder){
            with(peopleList[position]){
                binding.textView5.text = this.name
            }
        }
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(peopleList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Identity)
    }
}

class PeopleViewHolder(val binding: RowPersonBinding): RecyclerView.ViewHolder(binding.root)