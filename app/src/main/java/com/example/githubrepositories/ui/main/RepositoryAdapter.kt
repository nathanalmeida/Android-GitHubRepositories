package com.example.githubrepositories.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepositories.databinding.MainFragmentBinding
import com.example.githubrepositories.model.Item
import javax.inject.Inject

class RepositoryAdapter @Inject constructor() : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {
    var repositories: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            //TODO: Check this!
            MainFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(repositories[position])

    override fun getItemCount(): Int = repositories.size

    //TODO: Check binding
    inner class ViewHolder(private val binding: MainFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: Item) {
            binding.apply {
                repository.also { item ->
                    //TODO: Add to view
                    val a = item
//                    nameTextview.text = item.owner.login
//                    capitalTextview.text = capital
                }
            }
        }
    }
}

//class RepositoryAdapter(private val viewModel: MainViewModel) :
//    ListAdapter<GitHubRepository, RepositoryAdapter.ViewHolder>(RepositoryDiffCallback()) {
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = getItem(position)
//
//        holder.bind(viewModel, item)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder.from(parent)
//    }
//
//    class ViewHolder private constructor(val binding: MainFragmentBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(viewModel: MainViewModel, item: GitHubRepository) {
//
//            binding.viewmodel = viewModel
//            //TODO: Add repository
////            binding.task = item
//            binding.executePendingBindings()
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = MainFragmentBinding.inflate(layoutInflater, parent, false)
//
//                return ViewHolder(binding)
//            }
//        }
//    }
//}
//
//class RepositoryDiffCallback : DiffUtil.ItemCallback<GitHubRepository>() {
//    override fun areItemsTheSame(oldItem: GitHubRepository, newItem: GitHubRepository): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: GitHubRepository, newItem: GitHubRepository): Boolean {
//        return oldItem == newItem
//    }
//}

