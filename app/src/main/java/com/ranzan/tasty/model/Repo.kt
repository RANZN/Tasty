package com.ranzan.tasty.model

import androidx.lifecycle.MutableLiveData
import com.ranzan.tasty.model.remote.ApiClient
import com.ranzan.tasty.model.remote.ResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class Repo @Inject constructor(private val apiClient: ApiClient) {
    private val dataList = MutableLiveData<ArrayList<ResultsItem>>()
    private val liveError = MutableLiveData<String>()

    fun getData(): MutableLiveData<ArrayList<ResultsItem>> {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                dataList.postValue(apiClient.getData().results!! as ArrayList<ResultsItem>?)
            } catch (e: Exception) {
                liveError.postValue(e.toString())
            }
        }
        return dataList
    }

    fun getErrors() = liveError

}