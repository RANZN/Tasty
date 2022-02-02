package com.ranzan.tasty.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ranzan.tasty.databinding.CarouselItemLayoutBinding
import com.ranzan.tasty.databinding.FeaturedItemLayoutBinding
import com.ranzan.tasty.databinding.ShoppableItemLayoutBinding
import com.ranzan.tasty.model.remote.ResultsItem
import com.ranzan.tasty.view.listners.OnItemClick

class MainRecyclerViewAdapter(private val onItemClick: OnItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = ArrayList<ResultsItem>()

    override fun getItemViewType(position: Int): Int {
        when (list[position].type) {
            "featured" -> return 1
            "shoppable_carousel" -> return 3
            "carousel" -> return 3
            "item" -> return 1
        }
        return 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            1 -> {
                val binding = FeaturedItemLayoutBinding.inflate(layoutInflater, parent, false)
                return FeaturedViewHolder(binding)
            }
            2 -> {
                val binding = ShoppableItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ShoppableCarouselViewHolder(binding)
            }
            3 -> {
                val binding = CarouselItemLayoutBinding.inflate(layoutInflater, parent, false)
                return CarouselViewHolder(binding)
            }
            else -> {
                val binding = CarouselItemLayoutBinding.inflate(layoutInflater, parent, false)
                return CarouselViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            1 -> {
                (holder as FeaturedViewHolder).bindData(list[position], onItemClick)
            }
            2 -> {
                (holder as ShoppableCarouselViewHolder).bindData(list[position], onItemClick)
            }
            3 -> {
                (holder as CarouselViewHolder).bindData(list[position], onItemClick)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun mainRecyclerViewData(newList: ArrayList<ResultsItem>) {
        val diffUtilCallBack = DiffUtilCallBack(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        list.clear()
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

}

class CarouselViewHolder(private val binding: CarouselItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(data: ResultsItem, onItemClick: OnItemClick) {
        binding.tvResultName.text = data.name
        binding.nestedRecyclerView.apply {
            layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = NestedRecyclerViewAdapter(data.items!!,onItemClick)
        }
    }
}

class FeaturedViewHolder(private val binding: FeaturedItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(resultsItem: ResultsItem, onItemClick: OnItemClick) {
        binding.apply {
            Glide.with(featuredImage).load(resultsItem.item?.thumbnailUrl).into(featuredImage)

            layout.setOnClickListener {
                onItemClick.onItemClick(resultsItem.item?.id!!)
            }
        }
    }
}

class ShoppableCarouselViewHolder(private val binding: ShoppableItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(resultsItem: ResultsItem, onItemClick: OnItemClick) {

    }
}


