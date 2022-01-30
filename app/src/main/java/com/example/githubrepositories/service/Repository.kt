package com.example.githubrepositories.service

class Repository(private val gitHubApiService: GitHubApiService) {
    suspend fun getRepositories(language: String?, perPage: Int?, page: Int?) =
        gitHubApiService.getRepositories(language, perPage, page)
}