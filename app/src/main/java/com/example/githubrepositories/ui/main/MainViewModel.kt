package com.example.githubrepositories.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepositories.Event
import com.example.githubrepositories.model.Item
import com.example.githubrepositories.service.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository)  : ViewModel() {
    private val repositoryItemsLiveData = MutableLiveData<List<Item>?>()

    fun getRepositories() = repositoryItemsLiveData

    private val _openRepositoryDetailsEvent = MutableLiveData<Event<Item>>()
    val openRepositoryDetailsEvent: LiveData<Event<Item>> = _openRepositoryDetailsEvent

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        viewModelScope.launch {
            val repositories = repository.getRepositories("kotlin", 20, 1)
            when (repositories.isSuccessful) {
                true -> with(repositories.body()) {
                    this?.items?.let {
                        var itemList = listOf<Item>()
                        for (item in it) {
                            itemList = itemList + item
                        }
                        repositoryItemsLiveData.postValue(itemList)
                    }
                }
                else -> {
                    //TODO: Change log
                    Log.d("nathan", "erro!")
                }
            }
        }
    }

    fun openRepositoryDetails(repositoryItem: Item) {
        _openRepositoryDetailsEvent.value = Event(repositoryItem)
    }
}