package com.ranzan.tasty.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ranzan.tasty.model.remote.ApiClient
import com.ranzan.tasty.model.remote.ResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class Repo @Inject constructor(private val apiClient: ApiClient) {
    val dataList = MutableLiveData<ResponseModel>()

    fun getData(): MutableLiveData<ResponseModel> {
        CoroutineScope(Dispatchers.IO).launch {
            dataList.postValue(apiClient.getData())
        }
        return dataList
    }
}