package com.example.githubrepositories.service

import com.example.githubrepositories.model.GitHubRepository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {
    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") language: String?,
        @Query("per_page") perPage: Int?,
        @Query("page") page: Int?
    ): Response<GitHubRepository>
}