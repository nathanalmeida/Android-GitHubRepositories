package com.example.githubrepositories

import com.example.githubrepositories.di.AppModule
import com.example.githubrepositories.model.GitHubRepository
import com.example.githubrepositories.model.Item
import com.example.githubrepositories.model.Owner
import com.example.githubrepositories.service.GitHubApiService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {

    val json = "{\n" +
            "  \"total_count\": 2,\n" +
            "  \"incomplete_results\": false,\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"id\": 3432266,\n" +
            "      \"node_id\": \"MDEwOlJlcG9zaXRvcnkzNDMyMjY2\",\n" +
            "      \"name\": \"kotlin\",\n" +
            "      \"full_name\": \"JetBrains/kotlin\",\n" +
            "      \"private\": false,\n" +
            "      \"owner\": {\n" +
            "        \"login\": \"JetBrains\",\n" +
            "        \"id\": 878437,\n" +
            "        \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjg3ODQzNw==\",\n" +
            "        \"avatar_url\": \"https://avatars.githubusercontent.com/u/878437?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/JetBrains\",\n" +
            "        \"html_url\": \"https://github.com/JetBrains\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/JetBrains/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/JetBrains/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/JetBrains/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/JetBrains/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/JetBrains/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/JetBrains/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/JetBrains/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/JetBrains/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/JetBrains/received_events\",\n" +
            "        \"type\": \"Organization\",\n" +
            "        \"site_admin\": false\n" +
            "      },\n" +
            "      \"html_url\": \"https://github.com/JetBrains/kotlin\",\n" +
            "      \"description\": \"The Kotlin Programming Language. \",\n" +
            "      \"fork\": false,\n" +
            "      \"url\": \"https://api.github.com/repos/JetBrains/kotlin\",\n" +
            "      \"forks_url\": \"https://api.github.com/repos/JetBrains/kotlin/forks\",\n" +
            "      \"keys_url\": \"https://api.github.com/repos/JetBrains/kotlin/keys{/key_id}\",\n" +
            "      \"collaborators_url\": \"https://api.github.com/repos/JetBrains/kotlin/collaborators{/collaborator}\",\n" +
            "      \"teams_url\": \"https://api.github.com/repos/JetBrains/kotlin/teams\",\n" +
            "      \"hooks_url\": \"https://api.github.com/repos/JetBrains/kotlin/hooks\",\n" +
            "      \"issue_events_url\": \"https://api.github.com/repos/JetBrains/kotlin/issues/events{/number}\",\n" +
            "      \"events_url\": \"https://api.github.com/repos/JetBrains/kotlin/events\",\n" +
            "      \"assignees_url\": \"https://api.github.com/repos/JetBrains/kotlin/assignees{/user}\",\n" +
            "      \"branches_url\": \"https://api.github.com/repos/JetBrains/kotlin/branches{/branch}\",\n" +
            "      \"tags_url\": \"https://api.github.com/repos/JetBrains/kotlin/tags\",\n" +
            "      \"blobs_url\": \"https://api.github.com/repos/JetBrains/kotlin/git/blobs{/sha}\",\n" +
            "      \"git_tags_url\": \"https://api.github.com/repos/JetBrains/kotlin/git/tags{/sha}\",\n" +
            "      \"git_refs_url\": \"https://api.github.com/repos/JetBrains/kotlin/git/refs{/sha}\",\n" +
            "      \"trees_url\": \"https://api.github.com/repos/JetBrains/kotlin/git/trees{/sha}\",\n" +
            "      \"statuses_url\": \"https://api.github.com/repos/JetBrains/kotlin/statuses/{sha}\",\n" +
            "      \"languages_url\": \"https://api.github.com/repos/JetBrains/kotlin/languages\",\n" +
            "      \"stargazers_url\": \"https://api.github.com/repos/JetBrains/kotlin/stargazers\",\n" +
            "      \"contributors_url\": \"https://api.github.com/repos/JetBrains/kotlin/contributors\",\n" +
            "      \"subscribers_url\": \"https://api.github.com/repos/JetBrains/kotlin/subscribers\",\n" +
            "      \"subscription_url\": \"https://api.github.com/repos/JetBrains/kotlin/subscription\",\n" +
            "      \"commits_url\": \"https://api.github.com/repos/JetBrains/kotlin/commits{/sha}\",\n" +
            "      \"git_commits_url\": \"https://api.github.com/repos/JetBrains/kotlin/git/commits{/sha}\",\n" +
            "      \"comments_url\": \"https://api.github.com/repos/JetBrains/kotlin/comments{/number}\",\n" +
            "      \"issue_comment_url\": \"https://api.github.com/repos/JetBrains/kotlin/issues/comments{/number}\",\n" +
            "      \"contents_url\": \"https://api.github.com/repos/JetBrains/kotlin/contents/{+path}\",\n" +
            "      \"compare_url\": \"https://api.github.com/repos/JetBrains/kotlin/compare/{base}...{head}\",\n" +
            "      \"merges_url\": \"https://api.github.com/repos/JetBrains/kotlin/merges\",\n" +
            "      \"archive_url\": \"https://api.github.com/repos/JetBrains/kotlin/{archive_format}{/ref}\",\n" +
            "      \"downloads_url\": \"https://api.github.com/repos/JetBrains/kotlin/downloads\",\n" +
            "      \"issues_url\": \"https://api.github.com/repos/JetBrains/kotlin/issues{/number}\",\n" +
            "      \"pulls_url\": \"https://api.github.com/repos/JetBrains/kotlin/pulls{/number}\",\n" +
            "      \"milestones_url\": \"https://api.github.com/repos/JetBrains/kotlin/milestones{/number}\",\n" +
            "      \"notifications_url\": \"https://api.github.com/repos/JetBrains/kotlin/notifications{?since,all,participating}\",\n" +
            "      \"labels_url\": \"https://api.github.com/repos/JetBrains/kotlin/labels{/name}\",\n" +
            "      \"releases_url\": \"https://api.github.com/repos/JetBrains/kotlin/releases{/id}\",\n" +
            "      \"deployments_url\": \"https://api.github.com/repos/JetBrains/kotlin/deployments\",\n" +
            "      \"created_at\": \"2012-02-13T17:29:58Z\",\n" +
            "      \"updated_at\": \"2022-01-29T15:21:17Z\",\n" +
            "      \"pushed_at\": \"2022-01-29T15:35:54Z\",\n" +
            "      \"git_url\": \"git://github.com/JetBrains/kotlin.git\",\n" +
            "      \"ssh_url\": \"git@github.com:JetBrains/kotlin.git\",\n" +
            "      \"clone_url\": \"https://github.com/JetBrains/kotlin.git\",\n" +
            "      \"svn_url\": \"https://github.com/JetBrains/kotlin\",\n" +
            "      \"homepage\": \"https://kotlinlang.org\",\n" +
            "      \"size\": 1376348,\n" +
            "      \"stargazers_count\": 39987,\n" +
            "      \"watchers_count\": 39987,\n" +
            "      \"language\": \"Kotlin\",\n" +
            "      \"has_issues\": false,\n" +
            "      \"has_projects\": false,\n" +
            "      \"has_downloads\": true,\n" +
            "      \"has_wiki\": false,\n" +
            "      \"has_pages\": false,\n" +
            "      \"forks_count\": 4926,\n" +
            "      \"mirror_url\": null,\n" +
            "      \"archived\": false,\n" +
            "      \"disabled\": false,\n" +
            "      \"open_issues_count\": 129,\n" +
            "      \"license\": null,\n" +
            "      \"allow_forking\": true,\n" +
            "      \"is_template\": false,\n" +
            "      \"topics\": [\n" +
            "        \"compiler\",\n" +
            "        \"gradle-plugin\",\n" +
            "        \"intellij-plugin\",\n" +
            "        \"kotlin\",\n" +
            "        \"kotlin-library\",\n" +
            "        \"maven-plugin\",\n" +
            "        \"programming-language\"\n" +
            "      ],\n" +
            "      \"visibility\": \"public\",\n" +
            "      \"forks\": 4926,\n" +
            "      \"open_issues\": 129,\n" +
            "      \"watchers\": 39987,\n" +
            "      \"default_branch\": \"master\",\n" +
            "      \"score\": 1.0\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 91829561,\n" +
            "      \"node_id\": \"MDEwOlJlcG9zaXRvcnk5MTgyOTU2MQ==\",\n" +
            "      \"name\": \"KotlinUdemy\",\n" +
            "      \"full_name\": \"hussien89aa/KotlinUdemy\",\n" +
            "      \"private\": false,\n" +
            "      \"owner\": {\n" +
            "        \"login\": \"hussien89aa\",\n" +
            "        \"id\": 7304399,\n" +
            "        \"node_id\": \"MDQ6VXNlcjczMDQzOTk=\",\n" +
            "        \"avatar_url\": \"https://avatars.githubusercontent.com/u/7304399?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/hussien89aa\",\n" +
            "        \"html_url\": \"https://github.com/hussien89aa\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/hussien89aa/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/hussien89aa/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/hussien89aa/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/hussien89aa/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/hussien89aa/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/hussien89aa/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/hussien89aa/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/hussien89aa/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/hussien89aa/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"site_admin\": false\n" +
            "      },\n" +
            "      \"html_url\": \"https://github.com/hussien89aa/KotlinUdemy\",\n" +
            "      \"description\": \"Learn how to make online games, and apps for Android O, like Pokémon , twitter,Tic Tac Toe, and notepad using Kotlin\",\n" +
            "      \"fork\": false,\n" +
            "      \"url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy\",\n" +
            "      \"forks_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/forks\",\n" +
            "      \"keys_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/keys{/key_id}\",\n" +
            "      \"collaborators_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/collaborators{/collaborator}\",\n" +
            "      \"teams_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/teams\",\n" +
            "      \"hooks_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/hooks\",\n" +
            "      \"issue_events_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/issues/events{/number}\",\n" +
            "      \"events_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/events\",\n" +
            "      \"assignees_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/assignees{/user}\",\n" +
            "      \"branches_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/branches{/branch}\",\n" +
            "      \"tags_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/tags\",\n" +
            "      \"blobs_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/git/blobs{/sha}\",\n" +
            "      \"git_tags_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/git/tags{/sha}\",\n" +
            "      \"git_refs_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/git/refs{/sha}\",\n" +
            "      \"trees_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/git/trees{/sha}\",\n" +
            "      \"statuses_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/statuses/{sha}\",\n" +
            "      \"languages_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/languages\",\n" +
            "      \"stargazers_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/stargazers\",\n" +
            "      \"contributors_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/contributors\",\n" +
            "      \"subscribers_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/subscribers\",\n" +
            "      \"subscription_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/subscription\",\n" +
            "      \"commits_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/commits{/sha}\",\n" +
            "      \"git_commits_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/git/commits{/sha}\",\n" +
            "      \"comments_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/comments{/number}\",\n" +
            "      \"issue_comment_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/issues/comments{/number}\",\n" +
            "      \"contents_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/contents/{+path}\",\n" +
            "      \"compare_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/compare/{base}...{head}\",\n" +
            "      \"merges_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/merges\",\n" +
            "      \"archive_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/{archive_format}{/ref}\",\n" +
            "      \"downloads_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/downloads\",\n" +
            "      \"issues_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/issues{/number}\",\n" +
            "      \"pulls_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/pulls{/number}\",\n" +
            "      \"milestones_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/milestones{/number}\",\n" +
            "      \"notifications_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/notifications{?since,all,participating}\",\n" +
            "      \"labels_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/labels{/name}\",\n" +
            "      \"releases_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/releases{/id}\",\n" +
            "      \"deployments_url\": \"https://api.github.com/repos/hussien89aa/KotlinUdemy/deployments\",\n" +
            "      \"created_at\": \"2017-05-19T17:24:22Z\",\n" +
            "      \"updated_at\": \"2022-01-28T12:55:22Z\",\n" +
            "      \"pushed_at\": \"2021-03-15T19:58:10Z\",\n" +
            "      \"git_url\": \"git://github.com/hussien89aa/KotlinUdemy.git\",\n" +
            "      \"ssh_url\": \"git@github.com:hussien89aa/KotlinUdemy.git\",\n" +
            "      \"clone_url\": \"https://github.com/hussien89aa/KotlinUdemy.git\",\n" +
            "      \"svn_url\": \"https://github.com/hussien89aa/KotlinUdemy\",\n" +
            "      \"homepage\": \"https://www.udemy.com/the-complete-kotlin-developer-course/?couponCode=UDMEYGITHUB\",\n" +
            "      \"size\": 2791,\n" +
            "      \"stargazers_count\": 1389,\n" +
            "      \"watchers_count\": 1389,\n" +
            "      \"language\": \"Kotlin\",\n" +
            "      \"has_issues\": true,\n" +
            "      \"has_projects\": true,\n" +
            "      \"has_downloads\": true,\n" +
            "      \"has_wiki\": true,\n" +
            "      \"has_pages\": false,\n" +
            "      \"forks_count\": 4854,\n" +
            "      \"mirror_url\": null,\n" +
            "      \"archived\": false,\n" +
            "      \"disabled\": false,\n" +
            "      \"open_issues_count\": 12,\n" +
            "      \"license\": {\n" +
            "        \"key\": \"mit\",\n" +
            "        \"name\": \"MIT License\",\n" +
            "        \"spdx_id\": \"MIT\",\n" +
            "        \"url\": \"https://api.github.com/licenses/mit\",\n" +
            "        \"node_id\": \"MDc6TGljZW5zZTEz\"\n" +
            "      },\n" +
            "      \"allow_forking\": true,\n" +
            "      \"is_template\": false,\n" +
            "      \"topics\": [\n" +
            "        \"kotlin\",\n" +
            "        \"kotlin-android\"\n" +
            "      ],\n" +
            "      \"visibility\": \"public\",\n" +
            "      \"forks\": 4854,\n" +
            "      \"open_issues\": 12,\n" +
            "      \"watchers\": 1389,\n" +
            "      \"default_branch\": \"master\",\n" +
            "      \"score\": 1.0\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    @Test
    fun shouldHitEndpoint() = runBlocking {
        val server = MockWebServer()
        server.start()
        server.enqueue(MockResponse().setBody(json))

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gitHubApiService = AppModule.provideGitHubApiService(retrofit)
        val result = gitHubApiService.getRepositories("kotlin", 20, 1)

        val request = server.takeRequest()


        Assert.assertEquals("/search/repositories?q=kotlin&per_page=20&page=1" ,request.path)

        server.shutdown()
    }

    @Test
    fun shouldParseToObject() = runBlocking {
        val server = MockWebServer()
        server.start()
        server.enqueue(MockResponse().setBody(json))

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gitHubApiService = AppModule.provideGitHubApiService(retrofit)
        val result = gitHubApiService.getRepositories("kotlin", 20, 1)

        val items = listOf(Item(3432266, "kotlin", "JetBrains/kotlin", false,
            Owner("JetBrains", 878437, "https://api.github.com/users/JetBrains", "https://github.com/JetBrains","Organization"),
            "https://github.com/JetBrains/kotlin", "The Kotlin Programming Language. ", false, "https://api.github.com/repos/JetBrains/kotlin",
            "2012-02-13T17:29:58Z", "git://github.com/JetBrains/kotlin.git", "git@github.com:JetBrains/kotlin.git", "https://kotlinlang.org",
            1376348, "Kotlin", listOf("compiler", "gradle-plugin", "intellij-plugin", "kotlin", "kotlin-library", "maven-plugin", "programming-language")))

        val gitHubRepository = GitHubRepository(2, items)

        val resultBody = result.body()
        Assert.assertNotNull(resultBody)
        Assert.assertTrue(resultBody is GitHubRepository)

        Assert.assertEquals(resultBody!!.total_count, gitHubRepository.total_count)
        Assert.assertEquals(resultBody.items[0].name, gitHubRepository.items[0].name)
        Assert.assertEquals(resultBody.items[0].full_name, gitHubRepository.items[0].full_name)
        Assert.assertEquals(resultBody.items[0].private, gitHubRepository.items[0].private)
        Assert.assertEquals(resultBody.items[0].owner.login, gitHubRepository.items[0].owner.login)
        Assert.assertEquals(resultBody.items[0].owner.id, gitHubRepository.items[0].owner.id)
        Assert.assertEquals(resultBody.items[0].owner.url, gitHubRepository.items[0].owner.url)
        Assert.assertEquals(resultBody.items[0].owner.html_url, gitHubRepository.items[0].owner.html_url)
        Assert.assertEquals(resultBody.items[0].owner.type, gitHubRepository.items[0].owner.type)
        Assert.assertEquals(resultBody.items[0].html_url, gitHubRepository.items[0].html_url)
        Assert.assertEquals(resultBody.items[0].description, gitHubRepository.items[0].description)
        Assert.assertEquals(resultBody.items[0].fork, gitHubRepository.items[0].fork)
        Assert.assertEquals(resultBody.items[0].url, gitHubRepository.items[0].url)
        Assert.assertEquals(resultBody.items[0].created_at, gitHubRepository.items[0].created_at)
        Assert.assertEquals(resultBody.items[0].git_url, gitHubRepository.items[0].git_url)
        Assert.assertEquals(resultBody.items[0].ssh_url, gitHubRepository.items[0].ssh_url)
        Assert.assertEquals(resultBody.items[0].homepage, gitHubRepository.items[0].homepage)
        Assert.assertEquals(resultBody.items[0].size, gitHubRepository.items[0].size)
        Assert.assertEquals(resultBody.items[0].language, gitHubRepository.items[0].language)
        Assert.assertEquals(resultBody.items[0].topics[0], gitHubRepository.items[0].topics[0])
        Assert.assertEquals(resultBody.items[0].topics[1], gitHubRepository.items[0].topics[1])
        Assert.assertEquals(resultBody.items[0].topics[2], gitHubRepository.items[0].topics[2])
        Assert.assertEquals(resultBody.items[0].topics[3], gitHubRepository.items[0].topics[3])
        Assert.assertEquals(resultBody.items[0].topics[4], gitHubRepository.items[0].topics[4])
        Assert.assertEquals(resultBody.items[0].topics[5], gitHubRepository.items[0].topics[5])
        Assert.assertEquals(resultBody.items[0].topics[6], gitHubRepository.items[0].topics[6])
        server.shutdown()
    }

}