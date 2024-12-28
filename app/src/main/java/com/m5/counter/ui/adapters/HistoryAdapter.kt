package com.m5.counter.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.m5.counter.data.model.LoveModel
import com.m5.counter.databinding.HistoryItemBinding

class HistoryAdapter : ListAdapter<LoveModel, HistoryAdapter.HistoryViewHolder>(DiffCallBack()) {
    class HistoryViewHolder (private val binding: HistoryItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: LoveModel) = with(binding) {
            firstName.text = item.firstName
            secondName.text = item.secondName
            percentage.text = item.percentage
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<LoveModel>(){
        override fun areItemsTheSame(oldItem: LoveModel, newItem: LoveModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LoveModel, newItem: LoveModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


