package com.example.githubrepositories.model

data class Repository(val items: List<Item>)
data class Item(val owner: Owner)
data class Owner(val login: String)