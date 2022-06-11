package com.risingstar.talentaachiva.feature.util

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.RowCriteriaGradingBinding
import com.risingstar.talentaachiva.domain.data.Criteria
import com.risingstar.talentaachiva.domain.data.Score


class GradingAdapter(
    private val scoreList: ArrayList<Score>,
) : RecyclerView.Adapter<GradingViewHolder>(){


    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradingViewHolder {
        val binding = RowCriteriaGradingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return GradingViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: GradingViewHolder, position: Int) {
        with(holder){
            with(scoreList[position]){
                binding.tvCriteria.text = this.criteria?.name.toString()
                binding.tvNumber.setAutofillHints(this.amount.toString())
                binding.tvNumber.addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                    override fun afterTextChanged(textbox: Editable?) {}
                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        amount = p0.toString().toInt()
                    }


                })
            }
        }
//        holder.itemView.setOnClickListener{
//            onItemClickCallback.onItemClicked(criteriaList[holder.adapterPosition])
//        }
    }
    fun getScore():List<Score>{

        return scoreList
    }

    override fun getItemCount(): Int {

        return scoreList.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Criteria)
    }
}

class GradingViewHolder(val binding: RowCriteriaGradingBinding): RecyclerView.ViewHolder(binding.root)