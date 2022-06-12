package com.risingstar.talentaachiva.feature.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.RowPostBinding
import com.risingstar.talentaachiva.domain.data.Post

class PostAdapter(
    private val postList : ArrayList<Post>
) : RecyclerView.Adapter<PostViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = RowPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        with(holder){
            with(postList[position]){
//                binding.tvUsernameStream.text = this.authorname
                binding.tvPostStream.text = this.title
                binding.tvPostContent.text = this.content
            }
        }
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(postList[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Post)
    }
}

class PostViewHolder(val binding: RowPostBinding): RecyclerView.ViewHolder(binding.root)