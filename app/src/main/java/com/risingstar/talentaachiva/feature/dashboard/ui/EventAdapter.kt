package com.risingstar.talentaachiva.feature.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.RowHomeBinding
import com.risingstar.talentaachiva.domain.data.Event

class EventAdapter(
    private val eventList : ArrayList<Event>
) : RecyclerView.Adapter<EventViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = RowHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        with(holder){
            with(eventList[position]){
                binding.tvTitle.text = this.name
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



class EventViewHolder(val binding: RowHomeBinding): RecyclerView.ViewHolder(binding.root)