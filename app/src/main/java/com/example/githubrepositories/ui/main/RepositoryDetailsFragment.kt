package com.example.githubrepositories.ui.main

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.RepositoryDetailsFragmentBinding
import com.example.githubrepositories.model.Item
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.repository_details_fragment.*
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField


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

        addRepositoryDetails()
        textView.text = args.repositoryDetails.name
    }

    private fun addRepositoryDetails() {
        for (property in Item::class.memberProperties) {
            val textView = TextView(this.context)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(60, 8, 0, 16)
            textView.layoutParams = params
            textView.textSize = 16f
            textView.typeface = Typeface.DEFAULT_BOLD
            textView.text = "${property.javaField?.name}: ${property.get(args.repositoryDetails)}"

            main.addView(textView)
        }
    }

}