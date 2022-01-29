package com.example.githubrepositories.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.githubrepositories.retrofit.connection.GitHubInterface
import com.example.githubrepositories.retrofit.connection.Repository
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {

    fun start() {
        val gitHubInterface = GitHubInterface.create().getRepositories("kotlin", 20, 1)

        gitHubInterface.enqueue(object : Callback<Repository> {
            override fun onResponse(call: Call<Repository>?, response: Response<Repository>?) {

                if(response?.body() != null)
                    Log.d("nathan", response.body().toString())
            }

            override fun onFailure(call: Call<Repository>?, t: Throwable?) {
                Log.d("nathan", "Erro")
            }
        })
    }
}