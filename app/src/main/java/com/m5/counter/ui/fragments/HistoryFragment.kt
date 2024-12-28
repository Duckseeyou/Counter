package com.m5.counter.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.m5.counter.data.viewmodel.LoveViewModel
import com.m5.counter.databinding.FragmentHistoryBinding
import com.m5.counter.ui.adapters.HistoryAdapter
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private var adapter = HistoryAdapter()
    private val viewModel: LoveViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        initAdapter()
        binding.deleteBtn.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    private fun getData() {
        viewModel.getHistory().observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}