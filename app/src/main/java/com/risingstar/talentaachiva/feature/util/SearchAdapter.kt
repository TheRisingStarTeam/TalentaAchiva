package com.risingstar.talentaachiva.feature.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.risingstar.talentaachiva.databinding.RowSearchBinding
import com.risingstar.talentaachiva.domain.data.Event

class SearchAdapter(
    private val eventList : ArrayList<Event>
) : RecyclerView.Adapter<SearchViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = RowSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        with(holder){
            with(eventList[position]){
                binding.tvTitle.text = this.name
                Glide.with(binding.imgPhoto).load(this.banner).into(binding.imgPhoto)
            }
        }
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(eventList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Event)
    }
}



class SearchViewHolder(val binding: RowSearchBinding): RecyclerView.ViewHolder(binding.root)