package com.example.githubrepositories.retrofit.connection

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException


object ConnectionServiceOld {
    const val BASE_URL = "https://api.github.com"
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        // Create a very simple REST adapter which points the GitHub API.
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of our GitHub API interface.
        val github = retrofit.create(GitHub::class.java)

        val call = github.repositories("kotlin", 20, 1)

        // Fetch and print a list of the contributors to the library.
        val contributors = call.execute().body()!!
        for (contributor in contributors) {
            println(contributor.login + " (" + contributor.contributions + ")")
        }

//        // Create a call instance for looking up Retrofit contributors.
//        val call = github.contributors("square", "retrofit")
//
//        // Fetch and print a list of the contributors to the library.
//        val contributors = call.execute().body()!!
//        for (contributor in contributors) {
//            println(contributor.login + " (" + contributor.contributions + ")")
//        }
    }

    class Contributor(val login: String, val contributions: Int)
    interface GitHub {
        @GET("/search/repositories?q={language}&per_page={perPage}&page={page}")
        fun repositories(
            @Path("language") language: String?,
            @Path("perPage") perPage: Int?,
            @Path("page") page: Int?
        ): Call<List<Contributor>>

        @GET("/repos/{owner}/{repo}/contributors")
        fun contributors(
            @Path("owner") owner: String?,
            @Path("repo") repo: String?
        ): Call<List<Contributor>>
    }
}