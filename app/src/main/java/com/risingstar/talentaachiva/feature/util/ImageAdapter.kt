package com.risingstar.talentaachiva.feature.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.risingstar.talentaachiva.databinding.RowImageBinding

class ImageAdapter (
    private val images : ArrayList<String>
) : RecyclerView.Adapter<ImageViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = RowImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        with(holder){
            with(images[position]){
                Glide.with(binding.ivBanner).load(this).into(binding.ivBanner)
            }
            itemView.setOnClickListener{
                onItemClickCallback.onItemClicked(images[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: String)
    }
}



class ImageViewHolder(val binding: RowImageBinding): RecyclerView.ViewHolder(binding.root)