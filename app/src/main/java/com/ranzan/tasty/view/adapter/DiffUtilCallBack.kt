package com.ranzan.tasty.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ranzan.tasty.model.remote.ResultsItem

class DiffUtilCallBack(
    private val oldList: List<ResultsItem>,
    private val newList: List<ResultsItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].minItems == newList[newItemPosition].minItems
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}