package com.example.githubrepositories.model

data class GitHubRepository(val id: Int, val items: List<Item>)
data class Item(val name: String, val owner: Owner)
data class Owner(val login: String)