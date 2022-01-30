package com.example.githubrepositories.retrofit.connection

import com.example.githubrepositories.model.GitHubRepository
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubInterface {
    @GET("/search/repositories")
    fun getRepositories(
        @Query("q") language: String?,
        @Query("per_page") perPage: Int?,
        @Query("page") page: Int?
    ): Call<GitHubRepository>

    companion object {
        const val BASE_URL = "https://api.github.com"
        //TODO: ver se é Singleton de gitHubInterface

        fun create() : GitHubInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(GitHubInterface::class.java)
        }
    }
}
