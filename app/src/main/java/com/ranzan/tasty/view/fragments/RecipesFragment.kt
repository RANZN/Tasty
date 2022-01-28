package com.ranzan.tasty.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ranzan.tasty.R
import com.ranzan.tasty.databinding.FragmentDiscoverBinding
import com.ranzan.tasty.viewmodel.TheViewModel

class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentDiscoverBinding
    private val viewModel by activityViewModels<TheViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_receipes, container, false)
        return binding.root
    }


}