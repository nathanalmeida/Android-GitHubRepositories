package com.example.githubrepositories.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

//    lateinit var viewDataBinding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
//        val root = inflater.inflate(R.layout.main_fragment, container, false)
//        viewDataBinding = MainFragmentBinding.bind(root).apply {
//            this.viewmodel = viewModel
//        }
//        // Set the lifecycle owner to the lifecycle of the view
//        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
//        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.start()
    }

}