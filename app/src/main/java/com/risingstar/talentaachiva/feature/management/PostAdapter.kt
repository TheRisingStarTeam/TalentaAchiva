package com.risingstar.talentaachiva.feature.management

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.PostRowBinding
import com.risingstar.talentaachiva.domain.data.Post

class PostAdapter(
    private val postLists : ArrayList<Post>
) : RecyclerView.Adapter<PostViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        with(holder){
            with(postLists[position]){
                binding.rowName.text = title
            }
        }
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(postLists[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return postLists.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Post)
    }

}

class PostViewHolder(val binding: PostRowBinding):RecyclerView.ViewHolder(binding.root)
