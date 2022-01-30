package com.example.githubrepositories.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepositories.model.Item
import com.example.githubrepositories.model.GitHubRepository
import com.example.githubrepositories.retrofit.connection.GitHubInterface
import com.example.githubrepositories.service.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository)  : ViewModel() {
//    lateinit var repositories: GitHubRepository
    private val repositoryItemsLiveData = MutableLiveData<List<Item>?>()

    fun getRepositories() = repositoryItemsLiveData

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

//    fun start() {
//        val gitHubInterface = GitHubInterface.create().getRepositories("kotlin", 20, 1)
//
//        gitHubInterface.enqueue(object : Callback<GitHubRepository> {
//            override fun onResponse(call: Call<GitHubRepository>?, response: Response<GitHubRepository>?) {
//
//                if(response?.body() != null) {
////                    recyclerAdapter.setMovieListItems(response.body()!!)
//                    Log.d("nathan", response.body().toString())
//                    repositories = response.body()!!
//                }
//            }
//
//            override fun onFailure(call: Call<GitHubRepository>?, t: Throwable?) {
//                Log.d("nathan", "Erro")
//            }
//        })
//    }
}