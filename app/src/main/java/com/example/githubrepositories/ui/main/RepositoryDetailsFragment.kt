package com.example.githubrepositories.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.MainFragmentBinding
import com.example.githubrepositories.databinding.RepositoryDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.repository_details_fragment.*

@AndroidEntryPoint
class RepositoryDetailsFragment : Fragment() {

    //TODO: Remove?
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewDataBinding: RepositoryDetailsFragmentBinding
    private val args: RepositoryDetailsFragmentArgs by navArgs()
//    private val viewModel: MainViewModel by viewModels()
//    private lateinit var listAdapter: RepositoryAdapter

    //TODO: Inject
//    @Inject
//    lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        viewDataBinding = RepositoryDetailsFragmentBinding.bind(root)/*.apply {
            this.viewmodel = viewModel
        }*/
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        viewModel.start()

        setupNavigation()

        textView.text = args.repositoryDetails.name

        //TODO: Ver se pode remover a primeira linha a seguir
//        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
//        setupSnackbar()
//        setupListAdapter()
//        setUpObservers()
//        setupRefreshLayout(viewDataBinding.refreshLayout, viewDataBinding.tasksList)
//        setupNavigation()
//        setupFab()
    }

    private fun setupNavigation() {
//        viewModel.deleteTaskEvent.observe(viewLifecycleOwner, EventObserver {
//            val action = TaskDetailFragmentDirections
//                .actionTaskDetailFragmentToTasksFragment(DELETE_RESULT_OK)
//            findNavController().navigate(action)
//        })
//        viewModel.editTaskEvent.observe(viewLifecycleOwner, EventObserver {
//            val action = TaskDetailFragmentDirections
//                .actionTaskDetailFragmentToAddEditTaskFragment(
//                    args.taskId,
//                    resources.getString(R.string.edit_task)
//                )
//            findNavController().navigate(action)
//        })
    }

//    private fun setupListAdapter() {
//        val viewModel = viewDataBinding.viewmodel
//        if (viewModel != null) {
//            listAdapter = RepositoryAdapter()
//            viewDataBinding.repositoriesList.adapter = listAdapter
//        } else {
//            //TODO: Change log
//            Log.d("nathan", "ViewModel not initialized when attempting to set up adapter.")
//        }
//    }

//    private fun setUpObservers() {
//        //TODO: Ver se pode alterar o this
//        viewModel.getRepositories().observe(this) { repositoriesList ->
//            repositoriesList?.let {
//                viewDataBinding.repositoriesList.apply {
//                    with(adapter as RepositoryAdapter) {
//                        repositories = it
//                        notifyDataSetChanged()
//                    }
//                }
//            }
//        }
//    }

}