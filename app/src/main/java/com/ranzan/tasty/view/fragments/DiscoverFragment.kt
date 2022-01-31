package com.ranzan.tasty.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ranzan.tasty.databinding.FragmentDiscoverBinding
import com.ranzan.tasty.view.adapter.MainRecyclerViewAdapter
import com.ranzan.tasty.viewmodel.TheViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DiscoverFragment : Fragment() {

    private lateinit var binding: FragmentDiscoverBinding
    private lateinit var layoutAdapter: MainRecyclerViewAdapter
    private val viewModel by activityViewModels<TheViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()

        viewModel.getErrors().observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        })

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            layoutAdapter.mainRecyclerViewData(it)
        })
    }

    private fun setRecyclerView() {
        layoutAdapter = MainRecyclerViewAdapter()
        binding.mainRecyclerView.apply {
            adapter = layoutAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }
}