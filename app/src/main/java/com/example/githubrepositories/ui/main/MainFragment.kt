package com.example.githubrepositories.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    //TODO: Remove?
    companion object {
        fun newInstance() = MainFragment()
    }

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
//        return inflater.inflate(R.layout.main_fragment, container, false)

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

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
//        setupSnackbar()
        setupListAdapter()
        setUpObservers()
//        setupRefreshLayout(viewDataBinding.refreshLayout, viewDataBinding.tasksList)
//        setupNavigation()
//        setupFab()
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = RepositoryAdapter()
//            listAdapter = RepositoryAdapter(viewModel)
            //TODO: Add this
            viewDataBinding.repositoriesList.adapter = listAdapter
        } else {
            //TODO: Change log
            Log.d("nathan", "ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun setUpObservers() {
        viewModel.getRepositories().observe(this) { repositoriesList ->
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

}