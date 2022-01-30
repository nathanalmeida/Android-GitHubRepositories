package com.example.githubrepositories.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.RepositoryDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.repository_details_fragment.*

@AndroidEntryPoint
class RepositoryDetailsFragment : Fragment() {

    private lateinit var viewDataBinding: RepositoryDetailsFragmentBinding
    private val args: RepositoryDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.repository_details_fragment, container, false)
        viewDataBinding = RepositoryDetailsFragmentBinding.bind(root)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textView.text = args.repositoryDetails.name
    }

}