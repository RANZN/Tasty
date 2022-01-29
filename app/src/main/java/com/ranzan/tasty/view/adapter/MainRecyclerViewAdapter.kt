package com.ranzan.tasty.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ranzan.tasty.databinding.MainItemLayoutBinding
import com.ranzan.tasty.model.remote.ResultsItem

class MainRecyclerViewAdapter() : RecyclerView.Adapter<MainRecyclerViewHolder>() {

    private var list = ArrayList<ResultsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MainItemLayoutBinding.inflate(layoutInflater, parent, false)
        return MainRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.setData(list[position])
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

class MainRecyclerViewHolder(private val binding: MainItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setData(data: ResultsItem) {
        binding.tvResultName.text = data.name
        binding.nestedRecyclerView.apply {
            layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            if (data.items != null) {
                adapter = NestedRecyclerViewAdapter(data.items!!)
            }
        }
    }

}

