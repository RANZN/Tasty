package com.ranzan.tasty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranzan.tasty.model.Repo
import com.ranzan.tasty.model.remote.ResultsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TheViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    private var data = MutableLiveData<ArrayList<ResultsItem>>()
    fun getData() {
        data = repo.getData()
    }

    fun getLiveData() = data
    fun getErrors() = repo.getErrors()
}