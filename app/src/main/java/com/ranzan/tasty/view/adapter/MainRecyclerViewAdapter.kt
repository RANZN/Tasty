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

class MainRecyclerViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = ArrayList<ResultsItem>()

    override fun getItemViewType(position: Int): Int {
        when (list[position].type) {
            "featured" -> return 1
            "shoppable_carousel" -> return 2
            "carousel" -> return 3
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
                (holder as FeaturedViewHolder).bindData(list[position])
            }
            2 -> {
                (holder as ShoppableCarouselViewHolder).bindData(list[position])
            }
            3 -> {
                (holder as CarouselViewHolder).bindData(list[position])
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
    fun bindData(data: ResultsItem) {
        binding.tvResultName.text = data.name
        binding.nestedRecyclerView.apply {
            layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = NestedRecyclerViewAdapter(data.items!!)

        }
    }
}

class FeaturedViewHolder(private val binding: FeaturedItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(resultsItem: ResultsItem) {
        binding.featuredImage.apply {
            Glide.with(this).load(resultsItem.item?.thumbnailUrl).into(this)
        }
    }
}

class ShoppableCarouselViewHolder(private val binding: ShoppableItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(resultsItem: ResultsItem) {

    }

}


