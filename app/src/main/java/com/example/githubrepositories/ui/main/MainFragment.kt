package com.example.githubrepositories.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.githubrepositories.EventObserver
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.MainFragmentBinding
import com.example.githubrepositories.model.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var viewDataBinding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var listAdapter: RepositoryAdapter

    //TODO: Inject
//    @Inject
//    lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        viewDataBinding = MainFragmentBinding.bind(root).apply {
            this.viewmodel = viewModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        viewModel.start()

//        setupSnackbar()
        setupListAdapter()
        setUpObservers()
        setupNavigation()
//        setupRefreshLayout(viewDataBinding.refreshLayout, viewDataBinding.tasksList)
//        setupFab()
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = RepositoryAdapter(viewModel)
            viewDataBinding.repositoriesList.adapter = listAdapter
        } else {
            //TODO: Change log
            Log.d("nathan", "ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun setUpObservers() {
        viewModel.getRepositories().observe(viewLifecycleOwner) { repositoriesList ->
            repositoriesList?.let {
                viewDataBinding.repositoriesList.apply {
                    with(adapter as RepositoryAdapter) {
                        repositories = it
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun setupNavigation() {
        viewModel.openRepositoryDetailsEvent.observe(viewLifecycleOwner, EventObserver {
            openTaskDetails(it)
        })
    }

    private fun openTaskDetails(repositoryItem: Item) {
        val action = MainFragmentDirections.actionMainFragmentToRepositoryDetailsFragment(repositoryItem)
        findNavController().navigate(action)
    }


}