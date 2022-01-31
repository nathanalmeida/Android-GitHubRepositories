package com.example.githubrepositories.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubRepository(val total_count: Int, val items: List<Item>): Parcelable

@Parcelize
data class Item(val id: Int,
                val name: String,
                val full_name: String,
                val private: Boolean,
                val owner: Owner,
                val html_url: String,
                val description: String,
                val fork: Boolean,
                val url: String,
                val created_at: String,
                val git_url: String,
                val ssh_url: String,
                val homepage: String,
                val size: Int,
                val language: String,
                val topics: List<String>
): Parcelable

@Parcelize
data class Owner(val login: String,
                 val id: Int,
                 val url: String,
                 val html_url: String,
                 val type: String
): Parcelable