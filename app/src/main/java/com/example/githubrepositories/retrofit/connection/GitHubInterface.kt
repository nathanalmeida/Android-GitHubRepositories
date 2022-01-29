package com.example.githubrepositories.retrofit.connection

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubInterface {
    @GET("/search/repositories")
    fun getRepositories(
        @Query("q") language: String?,
        @Query("per_page") perPage: Int?,
        @Query("page") page: Int?
    ): Call<Repository>

    companion object {
        const val BASE_URL = "https://api.github.com"

        fun create() : GitHubInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(GitHubInterface::class.java)
        }
    }
}

data class Repository(val items: List<Item>)//, val owner: Owner)
data class Item(val owner: Owner)
data class Owner(val login: String)
