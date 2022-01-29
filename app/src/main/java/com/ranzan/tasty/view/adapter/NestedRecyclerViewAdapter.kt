package com.ranzan.tasty.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ranzan.tasty.R
import com.ranzan.tasty.databinding.NestedItemLayoutBinding
import com.ranzan.tasty.model.remote.ItemsItem

class NestedRecyclerViewAdapter(private val list: List<ItemsItem?>) : RecyclerView.Adapter<NestedRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NestedItemLayoutBinding.inflate(layoutInflater, parent, false)
        return NestedRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NestedRecyclerViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int = list.size

}

class NestedRecyclerViewHolder(private val binding: NestedItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setData(item: ItemsItem?) {
        binding.apply {
            tvItemName.text = item?.name
            Glide.with(ivItemImage).load(item?.thumbnailUrl).placeholder(R.drawable.ic_broken).into(ivItemImage)
        }
    }
}