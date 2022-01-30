package com.example.githubrepositories.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubRepository(val id: Int, val items: List<Item>): Parcelable

@Parcelize
data class Item(val name: String, val owner: Owner): Parcelable

@Parcelize
data class Owner(val login: String): Parcelable