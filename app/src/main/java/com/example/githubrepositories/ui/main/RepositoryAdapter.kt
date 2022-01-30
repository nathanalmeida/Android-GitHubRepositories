package com.example.githubrepositories.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepositories.databinding.RepositoryItemBinding
import com.example.githubrepositories.model.Item
import javax.inject.Inject

class RepositoryAdapter @Inject constructor(private val viewModel: MainViewModel) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {
    var repositories: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RepositoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(viewModel, repositories[position])

    override fun getItemCount(): Int = repositories.size

    inner class ViewHolder(private val binding: RepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mainViewModel: MainViewModel, repository: Item) {
            binding.apply {
                this.viewmodel = mainViewModel
                repositoryItem = repository
                repository.also { item ->
                    nameTextview.text = item.name
                    authorTextview.text = item.owner.login
                }
            }
            binding.executePendingBindings()
        }
    }
}

