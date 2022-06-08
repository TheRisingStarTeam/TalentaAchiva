package com.risingstar.talentaachiva.feature.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.RowRuneventBinding
import com.risingstar.talentaachiva.domain.data.Event

class RunningEventAdapter (
    private val eventList : ArrayList<Event>
) : RecyclerView.Adapter<RunEventHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunEventHolder {
        val binding = RowRuneventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RunEventHolder(binding)
    }

    override fun onBindViewHolder(holder: RunEventHolder, position: Int) {
        with(holder){
            with(eventList[position]){
                binding.tvTitleEvent.text = this.name
                binding.tvDescriptionEvent.text = this.description
            }
            itemView.setOnClickListener{
                onItemClickCallback.onItemClicked(eventList[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Event)
    }
}



class RunEventHolder(val binding: RowRuneventBinding): RecyclerView.ViewHolder(binding.root)