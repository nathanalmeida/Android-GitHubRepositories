package com.example.githubrepositories.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.githubrepositories.model.Repository
import com.example.githubrepositories.retrofit.connection.GitHubInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class MainViewModel : ViewModel() {
    lateinit var repositories: Repository

    fun start() {
        val gitHubInterface = GitHubInterface.create().getRepositories("kotlin", 20, 1)

        gitHubInterface.enqueue(object : Callback<Repository> {
            override fun onResponse(call: Call<Repository>?, response: Response<Repository>?) {

                if(response?.body() != null) {
//                    recyclerAdapter.setMovieListItems(response.body()!!)
                    Log.d("nathan", response.body().toString())
                    repositories = response.body()!!
                }
            }

            override fun onFailure(call: Call<Repository>?, t: Throwable?) {
                Log.d("nathan", "Erro")
            }
        })
    }
}